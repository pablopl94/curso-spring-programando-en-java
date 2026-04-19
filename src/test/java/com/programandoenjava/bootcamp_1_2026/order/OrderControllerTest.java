package com.programandoenjava.bootcamp_1_2026.order;

import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.domain.port.out.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.orderItem.domain.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.product.domain.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.domain.port.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@Transactional
public class OrderControllerTest extends TestContainerConfig {

    private static final String BASE_URL = "/api/orders";

    @Autowired
    private MockMvc mock;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

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

        OrderItem item1 = new OrderItem(null, 1, product1.price(), product1, 0L);
        OrderItem item2 = new OrderItem(null, 2, product2.price(), product2, 0L);
        OrderItem item3 = new OrderItem(null, 3, product3.price(), product3, 0L);

        order1 = Order.builder().customerName("Pablo").customerEmail("pablo@test.com")
                .processorName("Stripe").createdAt(LocalDateTime.now()).totalAmount(10.0)
                .items(Set.of(item1)).build();
        order2 = Order.builder().customerName("Juan").customerEmail("juan@test.com")
                .processorName("Stripe").createdAt(LocalDateTime.now()).totalAmount(40.0)
                .items(Set.of(item2)).build();
        order3 = Order.builder().customerName("Cristina").customerEmail("cristina@test.com")
                .processorName("Stripe").createdAt(LocalDateTime.now()).totalAmount(90.0)
                .items(Set.of(item3)).build();

        order1 = orderRepository.save(order1);
        order2 = orderRepository.save(order2);
        order3 = orderRepository.save(order3);
    }

    @Test
    @DisplayName("debería retornar las órdenes que contienen productos con el nombre buscado")
    void test01_shouldGetOrders_whenSearchByProductName() throws Exception {
        //Arrange
        String productName = product1.name();

        //Act + Asserts
        mock.perform(get(BASE_URL)
                        .param("productName", productName)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerName").value(order1.customerName()))
                .andExpect(jsonPath("$[0].items[0].product.name").value(productName));
    }

    @Test
    @DisplayName("debería devolver una lista de pedidos que tienen un precio total entre el mínimo y máximo especificado")
    void test02_shouldGetOrders_whenSearchByPriceMinAndMaxExists() throws Exception {
        //Arrange
        String totalAmountMin = "10.00";
        String totalAmountMax = "40.00";

        //Act + Asserts
        mock.perform(get(BASE_URL)
                        .param("totalAmountMin", totalAmountMin)
                        .param("totalAmountMax", totalAmountMax)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].totalAmount").value(order1.totalAmount()))
                .andExpect(jsonPath("$[1].totalAmount").value(order2.totalAmount()));
    }

    @Test
    @DisplayName("debería devolver una lista vacía si no existen pedidos entre el mínimo y máximo especificado")
    void test03_shouldGetOrders_whenSearchByPriceMinAndMaxNotExists() throws Exception {
        //Arrange
        String totalAmountMin = "100.00";
        String totalAmountMax = "200.00";

        //Act + Asserts
        mock.perform(get(BASE_URL)
                        .param("totalAmountMin", totalAmountMin)
                        .param("totalAmountMax", totalAmountMax)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

}
