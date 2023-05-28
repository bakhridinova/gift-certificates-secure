package com.epam.esm.util.mapper;

import com.epam.esm.dto.Mappable;
import com.epam.esm.entity.AbstractEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.ReportingPolicy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Mapper to convert entity into entity dto and vice versa
 *
 * @author bakhridinova
 */

public interface BaseMapper<T1 extends AbstractEntity, T2 extends Mappable> {
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    T2 toEntityDto(T1 t1);

    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    T1 toEntity(T2 t2);

    default Page<T2> mapEntitiyPageToEntityDtoPage(
            Page<T1> entities, BaseMapper<T1, T2> mapper) {
        return entities.map(mapper::toEntityDto);
    }

    default <T> Page<T> mapListToPage(
            List<T> entities, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), entities.size());
        return new PageImpl<>(entities.subList(start, end), pageable, entities.size());
    }
}
