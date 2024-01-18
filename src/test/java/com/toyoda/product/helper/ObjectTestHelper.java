package com.toyoda.product.helper;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.dto.product.ProductCreateDto;
import com.toyoda.product.dto.product.ProductUpdateDto;
import com.toyoda.product.entity.Brand;
import com.toyoda.product.entity.Category;
import com.toyoda.product.entity.Product;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.api.Randomizer;

import java.util.Random;
import java.util.UUID;

import static org.jeasy.random.FieldPredicates.named;

public class ObjectTestHelper {

    public static Brand mockBrand() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(Brand.class);
    }

    public static Category mockCategory() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(Category.class);
    }

    public static Product mockProduct() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(Product.class);
    }

    public static BrandDto mockBrandDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(BrandDto.class);
    }

    public static CategoryDto mockCategoryDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(CategoryDto.class);
    }

    public static ProductUpdateDto mockProductUpdateDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        return new EasyRandom(parameters).nextObject(ProductUpdateDto.class);
    }

    public static ProductCreateDto mockProductCreateDto() {
        EasyRandomParameters parameters = new EasyRandomParameters();
        parameters.randomize(named("quantity"), randomizerQuantity());
        return new EasyRandom(parameters).nextObject(ProductCreateDto.class);
    }

    private static Randomizer<Integer> randomizerQuantity() {
        return () -> 100;
    }
}
