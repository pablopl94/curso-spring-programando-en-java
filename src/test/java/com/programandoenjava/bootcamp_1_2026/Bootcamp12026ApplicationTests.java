package com.programandoenjava.bootcamp_1_2026;

import com.programandoenjava.bootcamp_1_2026.config.TestContainerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class Bootcamp12026ApplicationTests extends TestContainerConfig {

    @Test
    void contextLoads() {
    }

}
