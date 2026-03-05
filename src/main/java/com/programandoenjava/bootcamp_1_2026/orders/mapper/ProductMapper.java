package com.programandoenjava.bootcamp_1_2026.orders.mapper;

import com.programandoenjava.bootcamp_1_2026.orders.model.api.request.ProductRequestDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.ProductResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.input.ProductInputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.ProductOutputDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {OrderItemMapper.class})
public interface ProductMapper extends GenericMapper<Product, ProductRequestDto, ProductResponseDto, ProductInputDto, ProductOutputDto> {

    // REQUEST → INPUT
    @Override
    ProductInputDto requestToInputDto(ProductRequestDto request);

    // INPUT → ENTITY
    @Override
    @Mapping(target = "id", ignore = true)
    Product inputToEntity(ProductInputDto input);

    // ENTITY → OUTPUT
    @Override
    ProductOutputDto entityToOutputDto(Product entity);

    // OUTPUT → RESPONSE
    @Override
    ProductResponseDto outputToResponseDto(ProductOutputDto output);

}