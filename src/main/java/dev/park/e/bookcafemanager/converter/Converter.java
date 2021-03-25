package dev.park.e.bookcafemanager.converter;

import dev.park.e.bookcafemanager.dto.Convertible;

import java.util.List;
import java.util.stream.Collectors;

public interface Converter<T> {
    T toEntity(Convertible dto);

    default List<T> toEntities(List<? extends Convertible> dtos) {
        return dtos.stream().map(this::toEntity).collect(Collectors.toList());
    }
}
