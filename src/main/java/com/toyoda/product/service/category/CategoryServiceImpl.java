package com.toyoda.product.service.category;

import com.toyoda.product.entity.Category;
import com.toyoda.product.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository repository;

    @Override
    public List<Category> listAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Category> findCategoryById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void updateCategory(Category category) {
        repository.save(category);
    }

}
