package com.programandoenjava.bootcamp_1_2026.order.controller;

import com.programandoenjava.bootcamp_1_2026.order.mapper.CheckoutMapper;
import com.programandoenjava.bootcamp_1_2026.order.model.api.request.CheckoutRequestDto;
import com.programandoenjava.bootcamp_1_2026.order.model.api.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.input.CheckoutInputDto;
import com.programandoenjava.bootcamp_1_2026.order.model.application.output.CheckoutOutputDto;
import com.programandoenjava.bootcamp_1_2026.order.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutService service;
    private final CheckoutMapper mapper;

    // Emula el  checkout en base a un cart ficticio
    @PostMapping()
    public ResponseEntity<CheckoutResponseDto> checkout(@RequestBody CheckoutRequestDto request, Authentication authentication) {
        String username = authentication.getName();
        CheckoutInputDto checkoutInput = mapper.requestToInputDto(request);
        CheckoutOutputDto checkoutOutput = service.calculateCheckout(checkoutInput, username);
        CheckoutResponseDto response= mapper.outputToResponseDto(checkoutOutput);
        return ResponseEntity.ok().body(response);
    }
}

