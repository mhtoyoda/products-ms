package com.toyoda.product.controller.product;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.toyoda.product.dto.product.ProductCreateDto;
import com.toyoda.product.dto.product.ProductUpdateDto;
import com.toyoda.product.helper.ObjectTestHelper;
import com.toyoda.product.usecase.product.*;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerTest {

    @Mock
    private PostCreateProductUseCase postCreateProductUseCase;

    @Mock
    private GetListProductsUseCase getListProductsUseCase;

    @Mock
    private GetFindProductByIdUseCase getFindProductByIdUseCase;

    @Mock
    private GetFindProductByCategoryIdUseCase getFindProductByCategoryIdUseCase;

    @Mock
    private GetFindProductByBrandIdUseCase getFindProductByBrandIdUseCase;

    @Mock
    private PutUpdateProductUseCase putUpdateProductUseCase;
    
    private MockMvc mockMvc;

    @InjectMocks
    private ProductController controller;

    private ObjectMapper objectMapper = new ObjectMapper()
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
    public void createProduct() throws Exception {
        ProductCreateDto productCreateDto = ObjectTestHelper.mockProductCreateDto();

        this.mockMvc.perform(post("/product")
                        .content(objectMapper.writeValueAsBytes(productCreateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        Mockito.verify(postCreateProductUseCase, Mockito.times(1)).execute(productCreateDto);
    }

    @Test
    public void getProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(getListProductsUseCase, Mockito.times(1)).execute();
    }

    @Test
    public void getProductById() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(getFindProductByIdUseCase, Mockito.times(1)).execute(id);
    }

    @Test
    public void getProductByCategoryId() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/category/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(getFindProductByCategoryIdUseCase, Mockito.times(1)).execute(id);
    }

    @Test
    public void getProductByBrandId() throws Exception {
        Integer id = 1;

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/product/brand/{id}", id)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(getFindProductByBrandIdUseCase, Mockito.times(1)).execute(id);
    }

    @Test
    public void updateProduct() throws Exception {
        Integer id = 1;
        ProductUpdateDto productUpdateDto = ObjectTestHelper.mockProductUpdateDto();

        this.mockMvc.perform(put("/product/{id}", id)
                        .content(objectMapper.writeValueAsBytes(productUpdateDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        Mockito.verify(putUpdateProductUseCase, Mockito.times(1)).execute(id, productUpdateDto);
    }
}
