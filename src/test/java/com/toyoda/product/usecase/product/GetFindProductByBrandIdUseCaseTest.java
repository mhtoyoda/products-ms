package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.entity.Product;
import com.toyoda.product.exception.ProductNotFoundException;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GetFindProductByBrandIdUseCaseTest {

    @Mock
    private ProductService service;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private GetFindProductByBrandIdUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Integer brandId = 1;
        List<Product> products = List.of(ObjectTestHelper.mockProduct());

        Mockito.when(service.findProductByBrand(brandId)).thenReturn(Optional.of(products));

        List<ProductDto> list = useCase.execute(brandId);

        assertThat(list).hasSize(1);

        Mockito.verify(service, Mockito.times(1)).findProductByBrand(brandId);
    }

    @Test
    public void executeWithException() {
        Integer brandId = 1;

        Mockito.when(service.findProductByBrand(brandId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> useCase.execute(brandId));

        Mockito.verify(service, Mockito.times(1)).findProductByBrand(brandId);
    }
}
