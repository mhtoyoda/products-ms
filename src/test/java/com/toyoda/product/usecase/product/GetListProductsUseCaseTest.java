package com.toyoda.product.usecase.product;

import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.entity.Product;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.product.ProductService;
import com.toyoda.product.usecase.product.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetListProductsUseCaseTest {

    @Mock
    private ProductService service;

    @Spy
    private ProductMapper mapper = Mappers.getMapper(ProductMapper.class);

    @InjectMocks
    private GetListProductsUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Product product = ObjectTestHelper.mockProduct();
        product.setEnabled(true);

        Product product2 = ObjectTestHelper.mockProduct();
        product2.setEnabled(false);

        List<Product> products = List.of(product, product2);

        Mockito.when(service.listProducts()).thenReturn(products);

        List<ProductDto> list = useCase.execute();

        assertThat(list).hasSize(1);

        Mockito.verify(service, Mockito.times(1)).listProducts();
    }
}
