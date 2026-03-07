package com.programandoenjava.bootcamp_1_2026.orderItem.model.api;

public record OrderItemRequestDto(
        /**
         * @Ricardo -> Aquí tengo dudas, porque si quieres actualizar una orden,
         * entiendo que se actualizaría desde el formulario del padre (ORDER),
         * es decir saldría una tabla dentro del formulario con los orderItem
         * entonces en este caso aquí en el request sí que debería llevar Id
         * y hacer algo en el mapper como que si lleva id se deja y si lleva id a null se crea.
         * Pero claro entiendo que en un ecommerce las órdenes no se podrían modificar, si no vale
         * se haría un sof-delete y se crearía otra entiendo. Me he liado un poco con esto
         */
        Integer quantity,
        Double unitPrice,
        Long idProduct // En el request solo necesitamos el id del producto
) { }