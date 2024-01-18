package com.toyoda.product.usecase.category;

import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.service.category.CategoryService;
import com.toyoda.product.usecase.category.mapper.CategoryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GetCategoryUseCaseTest {

    @Mock
    private CategoryService service;

    @Spy
    private CategoryMapper mapper = Mappers.getMapper(CategoryMapper.class);

    @InjectMocks
    private GetCategoriesUseCase useCase;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void execute() {
        Mockito.when(service.listAll()).thenReturn(List.of(ObjectTestHelper.mockCategory()));

        List<CategoryDto> list = useCase.execute();

        assertThat(list).hasSize(1);
        Mockito.verify(service, Mockito.times(1)).listAll();
    }
}
