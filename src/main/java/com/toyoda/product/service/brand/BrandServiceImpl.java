package com.toyoda.product.service.brand;

import com.toyoda.product.entity.Brand;
import com.toyoda.product.repository.BrandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService {

    private final BrandRepository repository;

    @Override
    public List<Brand> listAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Brand> findBrandById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void updateBrand(Brand brand) {
        repository.save(brand);
    }
}
