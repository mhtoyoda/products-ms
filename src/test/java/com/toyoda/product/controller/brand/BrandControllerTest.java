package com.toyoda.product.controller.brand;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.toyoda.product.dto.brand.BrandDto;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.usecase.brand.GetBrandsUseCase;
import com.toyoda.product.usecase.brand.PutBrandUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class BrandControllerTest {

    @Mock
    private GetBrandsUseCase getBrandsUseCase;

    @Mock
    private PutBrandUseCase putBrandUseCase;

    private MockMvc mockMvc;

    @InjectMocks
    private BrandController controller;

    private final ObjectMapper objectMapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
            .registerModule(new JavaTimeModule());


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }

    @Test
    public void getBrands() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/brand")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(getBrandsUseCase, Mockito.times(1)).execute();
    }

    @Test
    public void updateBrand() throws Exception {
        Integer id = 1;

        final BrandDto brandDto = ObjectTestHelper.mockBrandDto();

        this.mockMvc.perform(put("/brand/{id}", id)
                        .content(objectMapper.writeValueAsBytes(brandDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(putBrandUseCase, Mockito.times(1)).execute(id, brandDto);
    }
}
