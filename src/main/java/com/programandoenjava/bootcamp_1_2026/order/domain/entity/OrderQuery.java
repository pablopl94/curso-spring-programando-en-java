package com.programandoenjava.bootcamp_1_2026.order.domain.entity;

public record OrderQuery(String view, OrderFilter filter) {

    public boolean hasFilter() {
        return filter != null && filter.hasAnyField();
    }

    public boolean hasView() {
        return view != null && !view.isBlank();
    }
}
