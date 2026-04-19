package com.programandoenjava.bootcamp_1_2026.payment.infrastructure.adapter;

import com.programandoenjava.bootcamp_1_2026.payment.domain.port.out.UserLookupPort;
import com.programandoenjava.bootcamp_1_2026.user.domain.entity.User;
import com.programandoenjava.bootcamp_1_2026.user.domain.port.in.user.GetUserByEmailUseCase;
import org.springframework.stereotype.Component;

@Component
public class UserLookupAdapter implements UserLookupPort {

    private final GetUserByEmailUseCase getUserByEmailUseCase;

    public UserLookupAdapter(GetUserByEmailUseCase getUserByEmailUseCase) {
        this.getUserByEmailUseCase = getUserByEmailUseCase;
    }

    @Override
    public User findByEmail(String email) {
        return getUserByEmailUseCase.getOneUserByEmail(email);
    }
}
