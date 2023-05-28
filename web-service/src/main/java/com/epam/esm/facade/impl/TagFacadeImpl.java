package com.epam.esm.facade.impl;

import com.epam.esm.dto.TagDto;
import com.epam.esm.facade.TagFacade;
import com.epam.esm.service.TagService;
import com.epam.esm.util.hateoas.TagHateoasAdder;
import com.epam.esm.util.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagFacadeImpl implements TagFacade {
    private final TagHateoasAdder tagHateoasAdder;
    private final TagService tagService;
    private final TagMapper tagMapper;

    @Override
    public Page<TagDto> findAllByPage(int page, int size) {
        Page<TagDto> tags = tagMapper.mapEntitiyPageToEntityDtoPage(
                tagService.findAllByPage(page, size), tagMapper);

        tagHateoasAdder.addLinksToEntityPage(tags);
        return tags;
    }

    @Override
    public TagDto findById(Long id) {
        TagDto tag = tagMapper.toEntityDto(tagService.findById(id));

        tagHateoasAdder.addLinksToEntity(tag);
        return tag;
    }

    @Override
    public TagDto findSpecial() {
        TagDto tag = tagMapper.toEntityDto(tagService.findSpecial());
        tagHateoasAdder.addLinksToEntity(tag);
        return tag;
    }

    @Override
    public TagDto create(TagDto tag) {
        tag = tagMapper.toEntityDto(tagService
                .create(tagMapper.toEntity(tag)));

        tagHateoasAdder.addLinksToEntity(tag);
        return tag;
    }

    @Override
    public void deleteById(Long id) {
        tagService.deleteById(id);
    }
}
