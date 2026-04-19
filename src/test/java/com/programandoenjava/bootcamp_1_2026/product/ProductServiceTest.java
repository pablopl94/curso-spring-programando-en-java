package com.programandoenjava.bootcamp_1_2026.product;

import com.programandoenjava.bootcamp_1_2026.product.application.service.ProductService;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.ProductController;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductResponse;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.mapper.ProductApiMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class, excludeFilters = @ComponentScan.Filter)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
public class ProductServiceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductApiMapper mapper;

    @MockitoBean
    private ProductService productService;

    @Test
    public void test01_getAllProductSuccessful() throws Exception {

        // Arrange
        Product product = new Product(1L, "Laptop", 999.0, 10);
        ProductResponse response = new ProductResponse(1L, "Laptop", 999.0, 10);

        given(productService.getAllProducts()).willReturn(List.of(product));
        given(mapper.toResponse(any(Product.class))).willReturn(response);

        // Act + Asserts
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }

}
