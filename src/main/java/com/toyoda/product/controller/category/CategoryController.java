package com.toyoda.product.controller.category;

import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.usecase.category.GetCategoriesUseCase;
import com.toyoda.product.usecase.category.PutCategoryUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final GetCategoriesUseCase getCategoriesUseCase;
    private final PutCategoryUseCase putCategoryUseCase;

    @GetMapping
    public ResponseEntity<List<CategoryDto>> listCategories() {
        List<CategoryDto> categories = getCategoriesUseCase.execute();
        return ResponseEntity.ok(categories);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateCategory(@PathVariable Integer id, @Valid @RequestBody CategoryDto categoryDto) {
        putCategoryUseCase.execute(id, categoryDto);
        return ResponseEntity.ok().build();
    }

}
