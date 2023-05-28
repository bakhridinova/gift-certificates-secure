package com.epam.esm.util.mapper;

import com.epam.esm.dto.TagDto;
import com.epam.esm.entity.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TagMapperTest {
    private final TagMapper tagMapper;

    public TagMapperTest() {
        tagMapper = new TagMapperImpl();
    }

    private static final Tag NULL_TAG =
            Tag.builder().build();
    private static final TagDto NULL_TAG_DTO =
            TagDto.builder().build();

    private static final Tag NON_NULL_TAG =
            Tag.builder().id(0L).build();
    private static final TagDto NON_NULL_TAG_DTO =
            TagDto.builder().id(0L).build();

    @Test
    void shouldMapToEntityCorrectlyTest() {
        assertNull(tagMapper.toEntity(null));
        assertEquals(NULL_TAG, tagMapper.toEntity(NULL_TAG_DTO));
        assertEquals(NON_NULL_TAG, tagMapper.toEntity(NON_NULL_TAG_DTO));
    }


    @Test
    void shouldMapToEntityDtoCorrectlyTest() {
        assertNull(tagMapper.toEntityDto(null));
        assertEquals(NULL_TAG_DTO, tagMapper.toEntityDto(NULL_TAG));
        assertEquals(NON_NULL_TAG_DTO, tagMapper.toEntityDto(NON_NULL_TAG));
    }
}