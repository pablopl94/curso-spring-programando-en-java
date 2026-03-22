package com.programandoenjava.bootcamp_1_2026.product;

import com.programandoenjava.bootcamp_1_2026.product.controller.ProductController;
import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductResponseDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class, excludeFilters = @ComponentScan.Filter)
@AutoConfigureMockMvc(addFilters = false)
public class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductMapper mapper;

    @MockitoBean
    private ProductService productService;

    @Test
    public void test01_getAllProductSuccessful() throws Exception {

        // Arrange
        ProductOutputDto outputDto = new ProductOutputDto(1L, "Laptop", 999.0, 10);
        ProductResponseDto responseDto = new ProductResponseDto(1L, "Laptop", 999.0, 10);

        given(productService.getAll()).willReturn(List.of(outputDto));
        given(mapper.outputToResponseDto(any(ProductOutputDto.class))).willReturn(responseDto);

        // Act + Asserts
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

}
