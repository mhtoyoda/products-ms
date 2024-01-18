package com.toyoda.product.controller.category;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.toyoda.product.dto.category.CategoryDto;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.usecase.category.GetCategoriesUseCase;
import com.toyoda.product.usecase.category.PutCategoryUseCase;
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

public class CategoryControllerTest {

    @Mock
    private GetCategoriesUseCase getCategoriesUseCase;

    @Mock
    private PutCategoryUseCase putCategoryUseCase;

    private MockMvc mockMvc;

    @InjectMocks
    private CategoryController controller;

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
    public void getCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/category")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(getCategoriesUseCase, Mockito.times(1)).execute();
    }

    @Test
    public void updateCategory() throws Exception {
        Integer id = 1;

        final CategoryDto categoryDto = ObjectTestHelper.mockCategoryDto();

        this.mockMvc.perform(put("/category/{id}", id)
                        .content(objectMapper.writeValueAsBytes(categoryDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(putCategoryUseCase, Mockito.times(1)).execute(id, categoryDto);
    }
}
