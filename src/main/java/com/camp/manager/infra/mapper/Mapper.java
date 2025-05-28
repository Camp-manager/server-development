package com.camp.manager.infra.mapper;

public interface Mapper <IN, OUT> {
    OUT toDomain(IN in);
    IN toEntity(OUT out);
}
