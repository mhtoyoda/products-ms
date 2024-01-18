package com.toyoda.product.service.category;

import com.toyoda.product.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> listAll();

    Optional<Category> findCategoryById(Integer id);

    void updateCategory(Category category);
}
