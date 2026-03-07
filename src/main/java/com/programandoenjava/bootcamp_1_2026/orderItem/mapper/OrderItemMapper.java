package com.programandoenjava.bootcamp_1_2026.orderItem.mapper;

import com.programandoenjava.bootcamp_1_2026.common.mapper.GenericMapper;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemRequestDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.api.OrderItemResponseDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.entity.OrderItem;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemInputDto;
import com.programandoenjava.bootcamp_1_2026.orderItem.model.application.OrderItemOutputDto;
import com.programandoenjava.bootcamp_1_2026.product.mapper.ProductMapper;
import com.programandoenjava.bootcamp_1_2026.product.model.entity.Product;
import com.programandoenjava.bootcamp_1_2026.product.repository.ProductRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public abstract class OrderItemMapper implements GenericMapper<OrderItem, OrderItemRequestDto, OrderItemResponseDto, OrderItemInputDto, OrderItemOutputDto> {

    @Autowired
    protected ProductRepository productRepository;

    @Override
    @Mapping(target = "order", ignore = true)
    @Mapping(source = "idProduct", target = "product", qualifiedByName = "mapProduct")
    public abstract OrderItem inputToEntity(OrderItemInputDto input);

    @Named("mapProduct")
    protected Product mapProduct(Long idProduct) {
        if (idProduct == null) return null;
        return productRepository.getReferenceById(idProduct);
    }
}