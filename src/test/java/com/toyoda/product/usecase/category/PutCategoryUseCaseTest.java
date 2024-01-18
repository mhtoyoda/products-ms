package com.toyoda.product.usecase.category;

import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.entity.Category;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.CategoryNotFoundException;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.category.CategoryService;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.category.mapper.CategoryMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

public class PutCategoryUseCaseTest {

    @Mock
    private CategoryService service;

    @Mock
    private ProductService productService;
    
    @Spy
    private CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);
    
    @InjectMocks
    private PutCategoryUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Integer id = 1;
        Category category = ObjectTestHelper.mockCategory();
        CategoryDto categoryDto = ObjectTestHelper.mockCategoryDto();
        List<Product> products = List.of(ObjectTestHelper.mockProduct());

        Mockito.when(service.findCategoryById(id)).thenReturn(Optional.of(category));
        Mockito.when(productService.findProductByCategory(category.getId())).thenReturn(Optional.of(products));
        useCase.execute(id, categoryDto);

        Mockito.verify(service, Mockito.times(1)).findCategoryById(id);
        Mockito.verify(service, Mockito.times(1)).updateCategory(category);
        Mockito.verify(productService, Mockito.times(1)).updateProducts(products);
    }

    @Test
    public void executeWithExceptions() {
        Integer id = 1;
        Category category = ObjectTestHelper.mockCategory();
        CategoryDto categoryDto = ObjectTestHelper.mockCategoryDto();
        List<Product> products = List.of(ObjectTestHelper.mockProduct());

        Mockito.when(service.findCategoryById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(CategoryNotFoundException.class, () -> useCase.execute(id, categoryDto));

        Mockito.verify(service, Mockito.never()).updateCategory(category);
        Mockito.verify(productService, Mockito.never()).updateProducts(products);
    }
}
