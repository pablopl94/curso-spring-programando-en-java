package com.programandoenjava.bootcamp_1_2026.order;

import com.programandoenjava.bootcamp_1_2026.order.mapper.OrderMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.entity.Order;
import com.programandoenjava.bootcamp_1_2026.order.repository.OrderRepository;
import com.programandoenjava.bootcamp_1_2026.order.service.OrderService;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import com.programandoenjava.bootcamp_1_2026.payment.model.application.PaymentInputDto;
import com.programandoenjava.bootcamp_1_2026.user.model.application.output.UserOutputDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.LinkedHashSet;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    OrderRepository orderRepository;

    @Mock(name = "mapper")
    OrderMapper mapper;

    @InjectMocks
    OrderService orderService;

    @Test
    public void test01_createSuccessful() {

        //Arrange
        String email = "pablo@test.com";
        OrderItemInputDto item = OrderItemInputDto.builder()
                .quantity(1)
                .unitPrice(50.00)
                .idProduct(1L)
                .build();
        Set<OrderItemInputDto> listItems = new LinkedHashSet<>();
        listItems.add(item);
        UserOutputDto user = UserOutputDto.builder()
                .email(email)
                .name("Pablo")
                .build();
        PaymentInputDto payment = PaymentInputDto.builder()
                .totalAmount(100.00)
                .customerEmail(email)
                .paymentProvider("paypal")
                .items(listItems)
                .build();
        Order order = new Order();
        given(mapper.inputToEntity(any())).willReturn(order);

        //Act
        orderService.createOrder(payment, user);

        //Asserts
        verify(orderRepository, times(1)).save(any(Order.class));

    }
}
