package com.toyoda.product.controller.product;

import com.toyoda.product.dto.product.ProductCreateDto;
import com.toyoda.product.dto.product.ProductDto;
import com.toyoda.product.dto.product.ProductUpdateDto;
import com.toyoda.product.usecase.product.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final PostCreateProductUseCase postCreateProductUseCase;
    private final GetListProductsUseCase getListProductsUseCase;
    private final GetFindProductByIdUseCase getFindProductByIdUseCase;
    private final GetFindProductByCategoryIdUseCase getFindProductByCategoryIdUseCase;
    private final GetFindProductByBrandIdUseCase getFindProductByBrandIdUseCase;
    private final PutUpdateProductUseCase putUpdateProductUseCase;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductCreateDto productDto) {
        postCreateProductUseCase.execute(productDto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<ProductDto> products = getListProductsUseCase.execute();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> findProductById(@PathVariable Integer id) {
        ProductDto productDto = getFindProductByIdUseCase.execute(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/category/{id}")
    public ResponseEntity<List<ProductDto>> findProductByCategoryId(@PathVariable Integer id) {
        List<ProductDto> productDtos = getFindProductByCategoryIdUseCase.execute(id);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/brand/{id}")
    public ResponseEntity<List<ProductDto>> findProductByBrandId(@PathVariable Integer id) {
        List<ProductDto> productDtos = getFindProductByBrandIdUseCase.execute(id);
        return ResponseEntity.ok(productDtos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Integer id, @Valid @RequestBody ProductUpdateDto productDto) {
        putUpdateProductUseCase.execute(id, productDto);
        return ResponseEntity.ok().build();
    }

}
