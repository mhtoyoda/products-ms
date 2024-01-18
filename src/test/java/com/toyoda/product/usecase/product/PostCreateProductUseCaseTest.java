package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductCreateDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Category;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.BrandNotFoundException;
import com.toyoda.product.exception.CategoryNotFoundException;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.brand.BrandService;
import com.toyoda.product.service.category.CategoryService;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

public class PostCreateProductUseCaseTest {

    @Mock
    private ProductService service;

    @Mock
    private CategoryService categoryService;

    @Mock
    private BrandService brandService;
    
    @Spy
    private ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);
    
    @InjectMocks
    private PostCreateProductUseCase useCase;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        ProductCreateDto productCreateDto = ObjectTestHelper.mockProductCreateDto();
        Brand brand = ObjectTestHelper.mockBrand();
        Category category = ObjectTestHelper.mockCategory();

        Mockito.when(brandService.findBrandById(anyInt())).thenReturn(Optional.of(brand));
        Mockito.when(categoryService.findCategoryById(anyInt())).thenReturn(Optional.of(category));

        useCase.execute(productCreateDto);

        Mockito.verify(brandService, Mockito.times(1)).findBrandById(anyInt());
        Mockito.verify(categoryService, Mockito.times(1)).findCategoryById(anyInt());
        Mockito.verify(service, Mockito.times(1)).createProduct(any(Product.class));
    }

    @Test
    public void executeWithBrandNotFoundException() {
        ProductCreateDto productCreateDto = ObjectTestHelper.mockProductCreateDto();
        Category category = ObjectTestHelper.mockCategory();

        Mockito.when(brandService.findBrandById(anyInt())).thenReturn(Optional.empty());
        Mockito.when(categoryService.findCategoryById(anyInt())).thenReturn(Optional.of(category));

        Assertions.assertThrows(BrandNotFoundException.class, () -> useCase.execute(productCreateDto));

        Mockito.verify(brandService, Mockito.times(1)).findBrandById(anyInt());
        Mockito.verify(categoryService, Mockito.times(1)).findCategoryById(anyInt());
        Mockito.verify(service, Mockito.never()).createProduct(any(Product.class));
    }

    @Test
    public void executeWithCategoryNotFoundException() {
        ProductCreateDto productCreateDto = ObjectTestHelper.mockProductCreateDto();
        Brand brand = ObjectTestHelper.mockBrand();

        Mockito.when(brandService.findBrandById(anyInt())).thenReturn(Optional.of(brand));
        Mockito.when(categoryService.findCategoryById(anyInt())).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class, () -> useCase.execute(productCreateDto));

        Mockito.verify(brandService, Mockito.never()).findBrandById(anyInt());
        Mockito.verify(categoryService, Mockito.times(1)).findCategoryById(anyInt());
        Mockito.verify(service, Mockito.never()).createProduct(any(Product.class));
    }
}
