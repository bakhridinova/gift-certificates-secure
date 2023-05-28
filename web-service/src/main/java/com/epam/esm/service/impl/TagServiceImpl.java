package com.epam.esm.service.impl;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.TagService;
import com.epam.esm.util.enums.field.TagField;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomTagValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {
    private final TagRepository tagRepository;

    @Override
    public Page<Tag> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        return tagRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Tag findById(Long id) {
        CustomValidator.validateId(TagField.ID, id);

        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Tag.class, id));
    }

    @Override
    public Tag findSpecial() {
        return tagRepository.findSpecial();
    }

    @Override
    @Transactional
    public Tag create(Tag tag) {
        CustomTagValidator.validate(tag, false);

        if (tagRepository.findByName(tag.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("tag with such name already exists");
        }

        return tagRepository.save(tag);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CustomValidator.validateId(TagField.ID, id);

        tagRepository.deleteById(id);
    }
}
