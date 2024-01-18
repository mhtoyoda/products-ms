package com.toyoda.product.service.product;

import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Category;
import com.toyoda.product.entity.Product;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;

public class ProductServiceTest {

    @Mock
    private ProductRepository repository;
    
    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void createProduct() {
        Product product = ObjectTestHelper.mockProduct();
        productService.createProduct(product);
        Mockito.verify(repository, times(1)).save(product);
    }

    @Test
    public void listProducts() {
        Mockito.when(repository.findAll()).thenReturn(List.of(ObjectTestHelper.mockProduct()));
        List<Product> products = productService.listProducts();

        assertThat(products).hasSize(1);
        Mockito.verify(repository, times(1)).findAll();
    }

    @Test
    public void findProductById() {
        Integer id = 1;
        Product product = ObjectTestHelper.mockProduct();
        product.setEnabled(true);
        product.setId(id);

        Mockito.when(repository.findByIdAndEnabledTrue(id)).thenReturn(Optional.of(product));
        Optional<Product> optionalProduct = productService.findProductById(id);

        assertThat(optionalProduct.isPresent()).isEqualTo(true);
        Mockito.verify(repository, times(1)).findByIdAndEnabledTrue(id);
    }

    @Test
    public void updateProduct() {
        Product product = ObjectTestHelper.mockProduct();
        productService.updateProduct(product);
        Mockito.verify(repository, times(1)).save(product);
    }

    @Test
    public void updateProducts() {
        List<Product> products = List.of(ObjectTestHelper.mockProduct());
        productService.updateProducts(products);
        Mockito.verify(repository, times(1)).saveAll(products);
    }

    @Test
    public void findProductByCategory() {
        Integer categoryId = 1;
        Product product = ObjectTestHelper.mockProduct();
        Category category = ObjectTestHelper.mockCategory();
        category.setId(categoryId);

        product.setCategory(category);

        Mockito.when(repository.findProductByCategoryId(categoryId)).thenReturn(Optional.of(List.of(product)));
        Optional<List<Product>> optionalProductList = productService.findProductByCategory(categoryId);

        assertThat(optionalProductList.isPresent()).isEqualTo(true);
        Mockito.verify(repository, times(1)).findProductByCategoryId(categoryId);
    }

    @Test
    public void findProductByBrand() {

        Integer brandId = 1;
        Product product = ObjectTestHelper.mockProduct();
        Brand brand = ObjectTestHelper.mockBrand();
        brand.setId(brandId);

        product.setBrand(brand);

        Mockito.when(repository.findProductByBrandId(brandId)).thenReturn(Optional.of(List.of(product)));
        Optional<List<Product>> optionalProductList = productService.findProductByBrand(brandId);

        assertThat(optionalProductList.isPresent()).isEqualTo(true);
        Mockito.verify(repository, times(1)).findProductByBrandId(brandId);
    }
}
