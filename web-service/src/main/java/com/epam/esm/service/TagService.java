package com.epam.esm.service;


import com.epam.esm.dto.TagDto;

/**
 * interface holding business logic for tags
 *
 * @author bakhridinova
 */

public interface TagService extends BaseService<TagDto> {
    TagDto findSpecial();
}
