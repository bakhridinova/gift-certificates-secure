package com.epam.esm.facade;

import com.epam.esm.dto.TagDto;

public interface TagFacade extends BaseFacade<TagDto> {
    TagDto findSpecial();
    TagDto create(TagDto tag);
    void deleteById(Long id);
}
