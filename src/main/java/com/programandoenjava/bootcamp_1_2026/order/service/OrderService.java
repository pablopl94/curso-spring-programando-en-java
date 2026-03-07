package com.programandoenjava.bootcamp_1_2026.order.service;

import com.programandoenjava.bootcamp_1_2026.order.model.api.request.RequestOrderFilter;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderSummaryOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.OrderViewOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderCrudService crudService;
    private final OrderFilterService filterService;
    private final OrderViewService viewService;

    //Delega a crudService
    public List<OrderOutputDto> getAll() {
        return crudService.getAll();
    }

    //Delega a filterService
    public List<OrderOutputDto> searchWithFilters(RequestOrderFilter filter) {
        return filterService.searchWithFilters(filter);
    }

    //Delega a viewService
    public List<OrderSummaryOutputDto> getSummary() {
        return viewService.getSummary();
    }

    public List<OrderViewOutputDto> getDashboardView() {
        return viewService.getDashboardView();
    }

}
