package com.toyoda.product.usecase.brand;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.brand.BrandService;
import com.toyoda.product.usecase.brand.mapper.BrandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class GetBrandsUseCaseTest {

    @Mock
    private BrandService service;

    @Spy
    private BrandMapper mapper = Mappers.getMapper(BrandMapper.class);

    @InjectMocks
    private GetBrandsUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Mockito.when(service.listAll()).thenReturn(List.of(ObjectTestHelper.mockBrand()));

        List<BrandDto> list = useCase.execute();

        assertThat(list).hasSize(1);
        Mockito.verify(service, Mockito.times(1)).listAll();
    }
}
