package com.programandoenjava.bootcamp_1_2026.order;

import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Transactional
public class OrderControllerTest extends TestContainerConfig {

    private static final String BASE_URL = "/api/orders";

    @Autowired
    private MockMvc mock;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Product product1;
    private Product product2;
    private Product product3;
    private Order order1;
    private Order order2;
    private Order order3;


    @BeforeEach
    void setUp() {
        product1 = productRepository.save(Product.builder().name("Producto1").price(10.00).stock(10).build());
        product2 = productRepository.save(Product.builder().name("Producto2").price(20.00).stock(20).build());
        product3 = productRepository.save(Product.builder().name("Producto3").price(30.00).stock(30).build());

        order1 = Order.builder().customerName("Pablo").customerEmail("pablo@test.com")
                .processorName("Stripe").createdAt(LocalDateTime.now()).totalAmount(10.0).build();
        order2 = Order.builder().customerName("Juan").customerEmail("juan@test.com")
                .processorName("Stripe").createdAt(LocalDateTime.now()).totalAmount(40.0).build();
        order3 = Order.builder().customerName("Cristina").customerEmail("cristina@test.com")
                .processorName("Stripe").createdAt(LocalDateTime.now()).totalAmount(90.0).build();

        OrderItem item1 = OrderItem.builder().product(product1).quantity(1).unitPrice(product1.getPrice()).order(order1).build();
        OrderItem item2 = OrderItem.builder().product(product2).quantity(2).unitPrice(product2.getPrice()).order(order2).build();
        OrderItem item3 = OrderItem.builder().product(product3).quantity(3).unitPrice(product3.getPrice()).order(order3).build();

        order1.setItems(Set.of(item1));
        order2.setItems(Set.of(item2));
        order3.setItems(Set.of(item3));

        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);
    }

    @Test
    @DisplayName("debería retornar las órdenes que contienen productos con el nombre buscado")
    void test01_shouldGetOrders_whenSearchByProductName() throws Exception {
        //Arrange
        String productName = product1.getName();

        //Act + Asserts
        mock.perform(get(BASE_URL + "/search")
                        .param("productName", productName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerName").value(order1.getCustomerName()))
                .andExpect(jsonPath("$[0].items[0].product.name").value(productName));
    }

    @Test
    @DisplayName("debería devolver una lista de pedidos que tienen un precio total entre el mínimo y máximo especificado")
    void test02_shouldGetOrders_whenSearchByPriceMinAndMaxExists() throws Exception {
        //Arrange
        String totalAmountMin = "10.00";
        String totalAmountMax = "40.00";

        //Act + Asserts
        mock.perform(get(BASE_URL + "/search")
                        .param("totalAmountMin", totalAmountMin)
                        .param("totalAmountMax", totalAmountMax)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].totalAmount").value(order1.getTotalAmount()))
                .andExpect(jsonPath("$[1].totalAmount").value(order2.getTotalAmount()));
    }

    @Test
    @DisplayName("debería devolver una lista vacía si no existen pedidos entre el mínimo y máximo especificado")
    void test03_shouldGetOrders_whenSearchByPriceMinAndMaxNotExists() throws Exception {
        //Arrange
        String totalAmountMin = "100.00";
        String totalAmountMax = "200.00";

        //Act + Asserts
        mock.perform(get(BASE_URL + "/search")
                        .param("totalAmountMin", totalAmountMin)
                        .param("totalAmountMax", totalAmountMax)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

}
