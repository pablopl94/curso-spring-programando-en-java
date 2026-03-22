package com.programandoenjava.bootcamp_1_2026.order.model.api.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestOrderFilter {
    private LocalDateTime createdAtFrom;
    private LocalDateTime createdAtTo;
    private Double totalAmountMin;
    private Double totalAmountMax;
    private String productName;
}