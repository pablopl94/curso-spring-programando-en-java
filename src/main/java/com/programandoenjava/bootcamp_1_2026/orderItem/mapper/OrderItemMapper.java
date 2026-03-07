package com.programandoenjava.bootcamp_1_2026.orderItem.mapper;

import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemRequestDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemResponseDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface OrderItemMapper extends GenericMapper<OrderItem, OrderItemRequestDto, OrderItemResponseDto, OrderItemInputDto, OrderItemOutputDto> {

    // REQUEST → INPUT
    @Override
    public abstract OrderItemInputDto requestToInputDto(OrderItemRequestDto request);

    // INPUT → ENTITY
    @Override
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "order", ignore = true)
    public abstract OrderItem inputToEntity(OrderItemInputDto input);

    // ENTITY → OUTPUT
    @Override
    public abstract OrderItemOutputDto entityToOutputDto(OrderItem entity);

    // OUTPUT → RESPONSE
    @Override
    public abstract OrderItemResponseDto outputToResponseDto(OrderItemOutputDto output);

}