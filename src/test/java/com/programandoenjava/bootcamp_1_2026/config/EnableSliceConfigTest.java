package com.programandoenjava.bootcamp_1_2026.config;

import com.programandoenjava.bootcamp_1_2026.common.exceptions.ExceptionControllerAdvice;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.http.converter.autoconfigure.HttpMessageConvertersAutoConfiguration;
import org.springframework.boot.jackson.autoconfigure.JacksonAutoConfiguration;
import org.springframework.boot.webmvc.autoconfigure.WebMvcAutoConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableDatabaseTest
@AutoConfigureMockMvc
@ImportAutoConfiguration({
        WebMvcAutoConfiguration.class,
        JacksonAutoConfiguration.class,
        HttpMessageConvertersAutoConfiguration.class,
        ExceptionControllerAdvice.class
})
public @interface EnableSliceConfigTest {
}
