package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.ShoppingCartRequest;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.OrderViewResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderCrudService crudService;
    private final OrderFilterService filterService;
    private final OrderViewService viewService;
    private final CheckoutService checkoutService;

    //Delega a crudService
    public List<OrderOutputDto> getAll() {
        return crudService.getAll();
    }

    //Delega a filterService
    public List<OrderOutputDto> searchWithFilters(RequestOrderFilter filter) {
        return filterService.searchWithFilters(filter);
    }

    //Delega a viewService
    public List<OrderOutputDto> getSummary() {
        return viewService.getSummary();
    }

    public List<OrderViewResponseDto> getDashboardView() {
        return viewService.getDashboardView();
    }

    //Delegar a CheckoutService
    public CheckoutResponseDto calculateCheckout(ShoppingCartRequest request, Authentication authentication) {
        return checkoutService.calculateCheckout(request, authentication);
    }

}
