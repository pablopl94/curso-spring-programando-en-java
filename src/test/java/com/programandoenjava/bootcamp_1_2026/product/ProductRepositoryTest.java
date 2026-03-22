package com.programandoenjava.bootcamp_1_2026.product;

import com.programandoenjava.bootcamp_1_2026.config.EnableDatabaseTest;
import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.config.infrastucture.CriteriaBuilderConfig;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ProductRepository.class, CriteriaBuilderConfig.class})
@EnableDatabaseTest
@Transactional
public class ProductRepositoryTest extends TestContainerConfig {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private TestEntityManager entityManager;


    //Métodos privados para no repetir código
    private Product createProduct(String name, Double price, Integer stock) {
        return Product.builder()
                .name(name)
                .price(price)
                .stock(stock)
                .build();
    }

    @Test
    @DisplayName("debería guardar un producto válido")
    void test01_shouldPCreateValidProduct() {
        //Arrange
        String nameProduct = "Producto1";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        Product product = createProduct(nameProduct, priceProduct, stockProduct);

        //Act
        Product saved = productRepository.save(product);

        //Asserts
        assertThat(productRepository.findById(saved.getId())).isPresent();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getName()).isEqualTo(nameProduct);
        assertThat(saved.getPrice()).isEqualTo(priceProduct);
        assertThat(saved.getStock()).isEqualTo(stockProduct);
    }


    @Test
    @DisplayName("debería retornar el producto cuando existe")
    void test02_shouldReturnProductWhenExists() {
        //Arrange
        String nameProduct = "Producto1";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        Product product = entityManager.persistAndFlush(
                createProduct(nameProduct, priceProduct, stockProduct));

        //Act
        Optional<Product> result = productRepository.findById(product.getId());

        //Asserts
        assertThat(result).isPresent()
                .hasValueSatisfying(p -> {
                    assertThat(p.getName()).isEqualTo(nameProduct);
                    assertThat(p.getPrice()).isEqualByComparingTo(priceProduct);
                    assertThat(p.getPrice()).isEqualByComparingTo(priceProduct);
                });
    }

    @Test
    @DisplayName("debería retornar vacío cuando no existe")
    void test03_shouldReturnEmptyWhenNotFound() {
        //Act + Arrange
        Optional<Product> result = productRepository.findById(999L);

        //Assert
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Devuelve una lista de productos")
    void test03_shouldReturnListOfProductsWhenFound() {
        //Arrange
        String nameProduct1 = "Producto1";
        String nameProduct2 = "Producto2";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        Product product = entityManager.persistAndFlush(
                createProduct(nameProduct1, priceProduct, stockProduct));
        Product product2 = entityManager.persistAndFlush(
                createProduct(nameProduct2, priceProduct, stockProduct));

        //Act
        List<Product> products = productRepository.findAll();

        //Asserts
        assertThat(products.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("Debería devolver una lista vacía si no hay productos")
    void test03_shouldReturnEmptyListOfProductsWhenNotFound() {
        //Act
        List<Product> products = productRepository.findAll();

        //Asserts
        assertThat(products).isEmpty();
    }

    @Test
    @DisplayName("debería eliminar el producto")
    void shouldDeleteExistingProduct() {
        //Arrange
        String nameProduct = "Producto1";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        Product product = entityManager.persistAndFlush(
                createProduct(nameProduct, priceProduct, stockProduct));

        //Act
        productRepository.deleteById(product.getId());
        entityManager.flush();
        entityManager.clear();

        //Asserts
        Optional<Product> deleted = productRepository.findById(product.getId());
        assertThat(deleted).isEmpty();
    }
}
