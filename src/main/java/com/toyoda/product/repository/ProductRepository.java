package com.toyoda.product.repository;

import com.toyoda.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    Optional<List<Product>> findProductByCategoryId(Integer categoryId);

    Optional<List<Product>> findProductByBrandId(Integer brandId);

    Optional<Product> findByIdAndEnabledTrue(Integer id);
}
