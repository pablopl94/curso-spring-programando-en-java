package com.programandoenjava.bootcamp_1_2026.order;

import com.programandoenjava.bootcamp_1_2026.config.EnableDatabaseTest;
import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@EnableDatabaseTest
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
    private Order savedOrder;


    @BeforeEach
    void setUp() {
        product1 = productRepository.save(Product.builder().name("Producto1").price(10.00).stock(10).build());
        product2 = productRepository.save(Product.builder().name("Producto2").price(20.00).stock(20).build());
        product3 = productRepository.save(Product.builder().name("Producto3").price(30.00).stock(30).build());

        Double totalAmount = product1.getPrice() * 1 + product2.getPrice() * 2 + product3.getPrice() * 3;

        Order order = Order.builder()
                .customerName("Pablo Prieto")
                .customerEmail("pablo@test.com")
                .processorName("Stripe")
                .createdAt(LocalDateTime.now())
                .totalAmount(totalAmount)
                .build();

        OrderItem item1 = OrderItem.builder().product(product1).quantity(1).unitPrice(product1.getPrice()).order(order).build();
        OrderItem item2 = OrderItem.builder().product(product2).quantity(2).unitPrice(product2.getPrice()).order(order).build();
        OrderItem item3 = OrderItem.builder().product(product3).quantity(3).unitPrice(product3.getPrice()).order(order).build();

        order.setItems(Set.of(item1, item2, item3));

        savedOrder = orderRepository.save(order);
    }

    @Test
    @DisplayName("debería retornar las órdenes que contienen productos con el nombre buscado")
    void test01_shouldGetOrders_whenSearchByProductName() throws Exception {
        //Arrange
        RequestOrderFilter request = new RequestOrderFilter();
        request.setProductName(product1.getName());
        String json = objectMapper.writeValueAsString(request);

        //Act + Asserts
        mock.perform(post(BASE_URL + "/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerName").value(savedOrder.getCustomerName()));
    }

    @Test
    @DisplayName("debería devolver una lista de pedidos que tienen un precio total entre el mínimo y máximo especificado")
    void test02_shouldGetOrders_whenSearchByPriceMinAndMaxExists() throws Exception {
        //Arrange
        RequestOrderFilter request = new RequestOrderFilter();
        request.setTotalAmountMin(100.0);
        request.setTotalAmountMax(200.0);
        String json = objectMapper.writeValueAsString(request);

        //Act + Asserts
        mock.perform(post(BASE_URL + "/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].customerName").value(savedOrder.getCustomerName()))
                .andExpect(jsonPath("$[0].totalAmount").value(savedOrder.getTotalAmount()));
    }

    @Test
    @DisplayName("debería devolver una lista vacía si no existen pedidos entre el mínimo y máximo especificado")
    void test03_shouldGetOrders_whenSearchByPriceMinAndMaxNotExists() throws Exception {
        //Arrange
        RequestOrderFilter request = new RequestOrderFilter();
        request.setTotalAmountMin(5.0);
        request.setTotalAmountMax(9.0);
        String json = objectMapper.writeValueAsString(request);

        //Act + Asserts
        mock.perform(post(BASE_URL + "/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

}
