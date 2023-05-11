package com.epam.esm.util.mapper;

import com.epam.esm.dto.Mappable;
import lombok.experimental.UtilityClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

@UtilityClass
public class PageMapper {
    public <T1, T2 extends Mappable> Page<T2> mapEntitiyPageToEntityDtoPage(
            Page<T1> entities, BaseMapper<T1, T2> mapper) {
        return entities.map(mapper::toEntityDto);
    }

    public <T> Page<T> mapListToPage(
            List<T> entities, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), entities.size());
        return new PageImpl<>(entities.subList(start, end), pageable, entities.size());
    }
}
