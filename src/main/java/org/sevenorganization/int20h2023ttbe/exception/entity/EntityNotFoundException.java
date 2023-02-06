package org.sevenorganization.int20h2023ttbe.exception.entity;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(String message) {
        super(message);
    }

    public static <T> EntityNotFoundException withId(Class<?> entityClass, T id) {
        return new EntityNotFoundException("%s with id '%s' not found".formatted(entityClass.getSimpleName(), id));
    }

    public static EntityNotFoundException withField(Class<?> entityClass, String fieldName, String fieldValue) {
        return new EntityNotFoundException(
                "%s with %s '%s' not found".formatted(entityClass.getSimpleName(), fieldName, fieldValue)
        );
    }
}
