package com.programandoenjava.bootcamp_1_2026.common.validator;

public interface GenericValidator<D> {

    void validateInsert(D dto);

    void validateUpdate(D dto);

    void validateDelete(D dto);
}
