package com.epam.esm.util.mapper;

import com.epam.esm.dto.Mappable;
import org.mapstruct.BeanMapping;
import org.mapstruct.ReportingPolicy;

/**
 * mapper to convert entity into entity dto and vice versa
 *
 * @author bakhridinova
 */

public interface BaseMapper<T1, T2 extends Mappable> {
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    T2 toEntityDto(T1 t1);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    T1 toEntity(T2 t2);
}
