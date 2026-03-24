package com.programandoenjava.bootcamp_1_2026.product;

import com.programandoenjava.bootcamp_1_2026.config.EnableSliceConfigTest;
import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.product.application.mapper.ProductApplicationMapper;
import com.programandoenjava.bootcamp_1_2026.product.application.service.ProductService;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.ProductController;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.api.dto.ProductRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(
        classes = {
                ProductController.class,
                ProductService.class,
                ProductRepository.class,
                ProductApplicationMapper.class
        })
@EnableSliceConfigTest
@Transactional
public class ProductControllerITest extends TestContainerConfig {

    @Autowired
    private MockMvc mock;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String BASE_URL = "/api/products";

    private Product createProduct(String name, Double price, Integer stock) {
        return new Product(null, name, price, stock);
    }

    @Test
    @DisplayName("debería retornar 200 y el producto cuando existe")
    void test01_shouldGetOneProduct() throws Exception {
        //Arrange
        String nameProduct = "Producto1";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        Product saved = productRepository.save(createProduct(nameProduct, priceProduct, stockProduct));

        //Act + Asserts
        mock.perform(get(BASE_URL + "/{id}", saved.id())
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(nameProduct))
                .andExpect(jsonPath("$.price").value(priceProduct))
                .andExpect(jsonPath("$.stock").value(stockProduct));
    }

    @Test
    @DisplayName("debería retornar 400 y la excepción ProductNotFoundException cuando el producto no existe")
    void test02_shouldGetException_WhenProductNotExists() throws Exception {
        //Arrange
        Long idNotExists = 900L;

        //Act + Asserts
        mock.perform(get(BASE_URL + "/{id}", idNotExists)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Producto no encontrado con id: " + idNotExists));
    }


    @Test
    @DisplayName("debería retornar 200 y devolver una lista de productos")
    void test03_shouldGetListProducts() throws Exception {
        //Arrange
        String nameProduct = "Producto1";
        String nameProduct2 = "Producto2";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        productRepository.save(createProduct(nameProduct, priceProduct, stockProduct));
        productRepository.save(createProduct(nameProduct2, priceProduct, stockProduct));

        //Act + Asserts
        mock.perform(get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
        assertThat(productRepository.count()).isEqualTo(2);
    }


    @Test
    @DisplayName("debería retornar 200 y devolver una lista de productos vacía")
    void test04_shouldGetEmpityList_whenNotFoundProduct() throws Exception {
        //Act + Asserts
        mock.perform(get(BASE_URL)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    @DisplayName("debería retornar 200 y devolver una lista de productos vacía")
    void test05_shouldSaveProduct() throws Exception {
        //Arrange
        String nameProduct = "Producto1";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        ProductRequest request = new ProductRequest(nameProduct, priceProduct, stockProduct);

        //Act + Asserts
        mock.perform(post(BASE_URL)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(header().exists("Location"));
        assertThat(productRepository.count()).isEqualTo(1);
    }


}
