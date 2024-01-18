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

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class GetFindProductByIdUseCaseTest {

    @Mock
    private ProductService service;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private GetFindProductByIdUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Integer id = 1;
        Product product = ObjectTestHelper.mockProduct();

        Mockito.when(service.findProductById(id)).thenReturn(Optional.of(product));

        ProductDto productDto = useCase.execute(id);

        assertThat(productDto).isNotNull();

        Mockito.verify(service, Mockito.times(1)).findProductById(id);
    }

    @Test
    public void executeWithException() {
        Integer id = 1;

        Mockito.when(service.findProductById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ProductNotFoundException.class, () -> useCase.execute(id));

        Mockito.verify(service, Mockito.times(1)).findProductById(id);
    }
}
