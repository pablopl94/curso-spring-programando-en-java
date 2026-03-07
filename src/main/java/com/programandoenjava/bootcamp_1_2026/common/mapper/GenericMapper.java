package com.programandoenjava.bootcamp_1_2026.common.mapper;

/**
 * @param <E> Entidad original
 * @param <Q> DTO con parámetros de entrada del controlador
 * @param <R> DTO con parámetros de salida del controlador
 * @param <I> DTO con parámetros de entrada del servicio
 * @param <O> DTO con parámetros de salida del servicio
 */
public interface GenericMapper<E, Q, R, I, O> {

    // REQUEST → INPUT
    I requestToInputDto(Q request);

    // INPUT → ENTITY
    E inputToEntity(I input);

    // ENTITY → OUTPUT
    O entityToOutputDto(E entity);

    // OUTPUT → RESPONSE
    R outputToResponseDto(O output);
}
