package com.toyoda.product.usecase.brand;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.BrandNotFoundException;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.brand.BrandService;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.brand.mapper.BrandMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

public class PutBrandUseCaseTest {

    @Mock
    private BrandService service;

    @Mock
    private ProductService productService;

    @Spy
    private BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @InjectMocks
    private PutBrandUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Integer id = 1;
        Brand brand = ObjectTestHelper.mockBrand();
        BrandDto brandDto = ObjectTestHelper.mockBrandDto();
        List<Product> products = List.of(ObjectTestHelper.mockProduct());

        Mockito.when(service.findBrandById(id)).thenReturn(Optional.of(brand));
        Mockito.when(productService.findProductByBrand(brand.getId())).thenReturn(Optional.of(products));
        useCase.execute(id, brandDto);

        Mockito.verify(service, Mockito.times(1)).findBrandById(id);
        Mockito.verify(service, Mockito.times(1)).updateBrand(brand);
        Mockito.verify(productService, Mockito.times(1)).updateProducts(products);
    }

    @Test
    public void executeWithExceptions() {
        Integer id = 1;
        Brand brand = ObjectTestHelper.mockBrand();
        BrandDto brandDto = ObjectTestHelper.mockBrandDto();
        List<Product> products = List.of(ObjectTestHelper.mockProduct());

        Mockito.when(service.findBrandById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(BrandNotFoundException.class, () -> useCase.execute(id, brandDto));

        Mockito.verify(service, Mockito.never()).updateBrand(brand);
        Mockito.verify(productService, Mockito.never()).updateProducts(products);
    }
}
