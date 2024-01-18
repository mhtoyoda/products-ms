package com.toyoda.product.service.product;

import com.toyoda.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    void createProduct(Product Product);

    List<Product> listProducts();

    Optional<Product> findProductById(Integer id);

    void updateProduct(Product Product);

    void updateProducts(List<Product> products);

    Optional<List<Product>> findProductByCategory(Integer categoryId);

    Optional<List<Product>> findProductByBrand(Integer brandId);
}
