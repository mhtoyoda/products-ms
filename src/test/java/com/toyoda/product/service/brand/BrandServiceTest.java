package com.toyoda.product.service.brand;

import com.toyoda.product.entity.Brand;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.repository.BrandRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BrandServiceTest {

    @Mock
    private BrandRepository repository;

    @InjectMocks
    private BrandServiceImpl brandService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void listAll() {
        Mockito.when(repository.findAll()).thenReturn(List.of(ObjectTestHelper.mockBrand()));
        List<Brand> brands = brandService.listAll();

        assertThat(brands).hasSize(1);

        Mockito.verify(repository, Mockito.times(1)).findAll();
    }

    @Test
    public void findBrandById() {
        Integer id = 1;
        Brand brand = ObjectTestHelper.mockBrand();
        brand.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(brand));
        Optional<Brand> brandOptional = brandService.findBrandById(id);

        assertThat(brandOptional.isPresent()).isEqualTo(true);

        Mockito.verify(repository, Mockito.times(1)).findById(id);
    }

    @Test
    public void updateBrand() {
        Integer id = 1;
        Brand brand = ObjectTestHelper.mockBrand();
        brand.setId(id);

        brandService.updateBrand(brand);

        Mockito.verify(repository, Mockito.times(1)).save(brand);
    }
}
