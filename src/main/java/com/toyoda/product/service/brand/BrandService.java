package com.toyoda.product.service.brand;

import com.toyoda.product.entity.Brand;
import com.toyoda.product.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> listAll();

    Optional<Brand> findBrandById(Integer id);

    void updateBrand(Brand brand);
}
