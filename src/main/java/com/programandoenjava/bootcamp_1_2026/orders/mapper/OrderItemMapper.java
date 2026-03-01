package com.programandoenjava.bootcamp_1_2026.orders.mapper;


import com.programandoenjava.bootcamp_1_2026.orders.model.api.request.OrderItemRequestDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.api.response.OrderItemResponseDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.input.OrderItemInputDto;
import com.programandoenjava.bootcamp_1_2026.orders.model.service.output.OrderItemOutputDto;
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