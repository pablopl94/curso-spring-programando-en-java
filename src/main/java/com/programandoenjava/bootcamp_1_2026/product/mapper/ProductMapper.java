package com.programandoenjava.bootcamp_1_2026.product.mapper;

import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductRequestDto;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductResponseDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductInputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface ProductMapper extends GenericMapper<Product, ProductRequestDto, ProductResponseDto, ProductInputDto, ProductOutputDto> {

    // OUTPUT → ENTITY
    Product outputToEntity(ProductOutputDto output);

}