package com.programandoenjava.bootcamp_1_2026.product.mapper;

import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.orderItem.mapper.OrderItemMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductRequestDto;
import com.programandoenjava.bootcamp_1_2026.product.model.api.ProductResponseDto;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductInputDto;
import com.programandoenjava.bootcamp_1_2026.product.model.application.ProductOutputDto;
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