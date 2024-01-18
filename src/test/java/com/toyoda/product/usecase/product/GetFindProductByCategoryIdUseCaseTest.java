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

public class GetFindProductByCategoryIdUseCaseTest {

    @Mock
    private ProductService service;

    @Spy
    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private GetFindProductByCategoryIdUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Integer categoryId = 1;
        List<Product> products = List.of(ObjectTestHelper.mockProduct());

        Mockito.when(service.findProductByCategory(categoryId)).thenReturn(Optional.of(products));

        List<ProductDto> list = useCase.execute(categoryId);

        assertThat(list).hasSize(1);

        Mockito.verify(service, Mockito.times(1)).findProductByCategory(categoryId);
    }

    @Test
    public void executeWithException() {
        Integer categoryId = 1;

        Mockito.when(service.findProductByCategory(categoryId)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> useCase.execute(categoryId));

        Mockito.verify(service, Mockito.times(1)).findProductByCategory(categoryId);
    }
}
