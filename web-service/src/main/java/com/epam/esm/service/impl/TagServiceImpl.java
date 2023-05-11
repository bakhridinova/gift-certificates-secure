package com.epam.esm.service.impl;

import com.epam.esm.dto.TagDto;
import com.epam.esm.exception.CustomEntityAlreadyExistsException;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.util.hateoas.HateoasAdder;
import com.epam.esm.util.mapper.entity.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;
    private final HateoasAdder<TagDto> tagHateoasAdder;

    @Override
    public Page<TagDto> findAllByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TagDto> tags = tagMapper.mapEntitiyPageToEntityDtoPage(tagRepository
                .findAll(pageable), tagMapper);

        tagHateoasAdder.addLinksToEntityPage(tags);
        return tags;
    }

    @Override
    public TagDto findById(Long id) {
        TagDto tag = tagMapper.toEntityDto(tagRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(
                        "failed to find tag by orderId " + id)));

        tagHateoasAdder.addLinksToEntity(tag);
        return tag;
    }

    @Override
    public TagDto findSpecial() {
        TagDto tag = tagMapper.toEntityDto(tagRepository.findSpecial());
        tagHateoasAdder.addLinksToEntity(tag);
        return tag;
    }

    @Override
    @Transactional
    public TagDto create(TagDto tag) {
        if (tagRepository.findByName(tag.getName()).isPresent()) {
            throw new CustomEntityAlreadyExistsException("tag with such name already exists");
        }
        tag = tagMapper.toEntityDto(tagRepository
                .save(tagMapper.toEntity(tag)));

        tagHateoasAdder.addLinksToEntity(tag);
        return tag;
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        tagRepository.deleteById(id);
       return "tag was successfully deleted";
    }
}
