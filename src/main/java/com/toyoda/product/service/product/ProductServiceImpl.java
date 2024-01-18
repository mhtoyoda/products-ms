package com.toyoda.product.service.product;

import com.toyoda.product.entity.Product;
import com.toyoda.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Override
    public void createProduct(Product product) {
        repository.save(product);
    }

    @Override
    public List<Product> listProducts() {
        return repository.findAll();
    }

    @Override
    public Optional<Product> findProductById(Integer id) {
        return repository.findByIdAndEnabledTrue(id);
    }

    @Override
    public void updateProduct(Product product) {
        repository.save(product);
    }

    @Override
    public void updateProducts(List<Product> products) {
        repository.saveAll(products);
    }

    @Override
    public Optional<List<Product>> findProductByCategory(Integer categoryId) {
        return repository.findProductByCategoryId(categoryId);
    }

    @Override
    public Optional<List<Product>> findProductByBrand(Integer brandId) {
        return repository.findProductByBrandId(brandId);
    }
}
