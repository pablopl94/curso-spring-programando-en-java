package com.programandoenjava.bootcamp_1_2026.product;

import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.config.infrastucture.CriteriaBuilderConfig;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.DBProductAdapter;
import com.programandoenjava.bootcamp_1_2026.product.infraestructure.database.mapper.ProductRepositoryMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({CriteriaBuilderConfig.class, DBProductAdapter.class, ProductRepositoryMapper.class})
@ActiveProfiles("test")
public class ProductRepositoryTest extends TestContainerConfig {

    @Autowired
    private ProductRepository productRepository;

    //Métodos privados para no repetir código
    private Product createProduct(String name, Double price, Integer stock) {
        return new Product(null, name, price, stock);
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
        assertThat(productRepository.findById(saved.id())).isPresent();
        assertThat(saved.id()).isNotNull();
        assertThat(saved.name()).isEqualTo(nameProduct);
        assertThat(saved.price()).isEqualTo(priceProduct);
        assertThat(saved.stock()).isEqualTo(stockProduct);
    }


    @Test
    @DisplayName("debería retornar el producto cuando existe")
    void test02_shouldReturnProductWhenExists() {
        //Arrange
        String nameProduct = "Producto1";
        Double priceProduct = 999.00;
        Integer stockProduct = 100;
        Product product = productRepository.save(createProduct(nameProduct, priceProduct, stockProduct));

        //Act
        Optional<Product> result = productRepository.findById(product.id());

        //Asserts
        assertThat(result).isPresent()
                .hasValueSatisfying(p -> {
                    assertThat(p.name()).isEqualTo(nameProduct);
                    assertThat(p.price()).isEqualByComparingTo(priceProduct);
                    assertThat(p.price()).isEqualByComparingTo(priceProduct);
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
        productRepository.save(createProduct(nameProduct1, priceProduct, stockProduct));
        productRepository.save(createProduct(nameProduct2, priceProduct, stockProduct));

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
        Product product = productRepository.save(createProduct(nameProduct, priceProduct, stockProduct));

        //Act
        productRepository.deleteById(product.id());

        //Asserts
        Optional<Product> deleted = productRepository.findById(product.id());
        assertThat(deleted).isEmpty();
    }
}
