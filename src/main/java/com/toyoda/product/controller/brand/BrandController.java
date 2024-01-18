package com.toyoda.product.controller.brand;

import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.usecase.brand.GetBrandsUseCase;
import com.toyoda.product.usecase.brand.PutBrandUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/brand")
@RequiredArgsConstructor
public class BrandController {

    private final GetBrandsUseCase getBrandsUseCase;
    private final PutBrandUseCase putBrandUseCase;

    @GetMapping
    public ResponseEntity<List<BrandDto>> listBrands() {
        List<BrandDto> brands = getBrandsUseCase.execute();
        return ResponseEntity.ok(brands);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateBrand(@PathVariable Integer id, @Valid @RequestBody BrandDto brandDto) {
        putBrandUseCase.execute(id, brandDto);
        return ResponseEntity.ok().build();
    }
}
