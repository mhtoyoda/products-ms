package com.toyoda.product.service.category;

import com.toyoda.product.entity.Category;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class CategoryServiceTest {

    @Mock
    private CategoryRepository repository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(ObjectTestHelper.mockCategory()));
        List<Category> categories = categoryService.listAll();

        assertThat(categories).hasSize(1);

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void findCategoryById() {
        Integer id = 1;
        Category category = ObjectTestHelper.mockCategory();
        category.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(category));
        Optional<Category> categoryOptional = categoryService.findCategoryById(id);

        assertThat(categoryOptional.isPresent()).isEqualTo(true);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    public void updateCategory() {
        Integer id = 1;
        Category category = ObjectTestHelper.mockCategory();
        category.setId(id);

        categoryService.updateCategory(category);

        Mockito.verify(repository, Mockito.times(1)).save(category);
    }
}
