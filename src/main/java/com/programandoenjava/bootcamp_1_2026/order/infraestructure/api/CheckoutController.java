package com.programandoenjava.bootcamp_1_2026.order.infraestructure.api;

import com.programandoenjava.bootcamp_1_2026.order.application.service.CheckoutService;
import com.programandoenjava.bootcamp_1_2026.order.domain.entity.Checkout;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.request.CartRequestDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.dto.response.CheckoutResponseDto;
import com.programandoenjava.bootcamp_1_2026.order.infraestructure.api.mapper.CheckoutApiMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkout")
public class CheckoutController {

    private final CheckoutApiMapper checkoutApiMapper;
    private final CheckoutService service;

    // Emula el  checkout en base a un cart ficticio
    @PostMapping()
    public ResponseEntity<CheckoutResponseDto> checkout(@RequestBody CartRequestDto request, Authentication authentication) {
        String username = authentication.getName();
        Checkout checkout = checkoutApiMapper.toDomain(request);
        Checkout checkoutResolved = service.calculateCheckout(checkout, username);
        CheckoutResponseDto response = checkoutApiMapper.toResponse(checkoutResolved);
        return ResponseEntity.ok().body(response);
    }
}

