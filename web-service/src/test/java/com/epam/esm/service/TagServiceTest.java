package com.epam.esm.service;

import com.epam.esm.entity.Tag;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.ValidationException;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.impl.TagServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.Random;

import static org.instancio.Select.field;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TagServiceTest {
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private TagServiceImpl tagService;
    private final Random random = new Random();

    private static final Class<Tag> TYPE_CLASS = Tag.class;

    @Test
    void contextLoads() {
        assertNotNull(tagService);
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsNegative() {
        assertEquals("page number should not be negative",
                assertThrows(ValidationException.class,
                        () -> tagService.findAllByPage(Integer.MIN_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsNegative() {
        assertEquals("page size should not be negative",
                assertThrows(ValidationException.class,
                        () -> tagService.findAllByPage(0, Integer.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsTooHigh() {
        assertEquals("page number must be between 0 and 10000",
                assertThrows(ValidationException.class,
                        () -> tagService.findAllByPage(Integer.MAX_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsTooHigh() {
        assertEquals("page size must be between 0 and 100",
                assertThrows(ValidationException.class,
                        () -> tagService.findAllByPage(0, Integer.MAX_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("tag id should not be null",
                assertThrows(ValidationException.class,
                        () -> tagService.findById(null))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("tag id must be positive",
                assertThrows(ValidationException.class,
                        () -> tagService.findById(0L))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("tag id must be positive",
                assertThrows(ValidationException.class,
                        () -> tagService.findById(Long.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowNotFoundException_IfOptionalIsEmpty() {
        when(tagRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        String message = String.format("failed to find %s by id %d",
                TYPE_CLASS.getSimpleName(), 1L);

        assertEquals(message,
                assertThrows(EntityNotFoundException.class,
                        () -> tagService.findById(1L))
                        .getMessage());
    }

    @Test
    void findById_ShouldNotThrowException_IfOptionalIsPresent() {
        when(tagRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> tagService.findById(1L));
    }

    @Test
    void create_ShouldThrowValidationException_IfNameIsNull() {
        assertEquals("tag name should not be null",
                assertThrows(ValidationException.class,
                        () -> tagService.create(Instancio.of(TYPE_CLASS)
                                .set(field(Tag::getName), null)
                                .create()))
                        .getMessage());
    }

    @Test
    void create_ShouldThrowValidationException_IfNameIsBlank() {
        assertEquals("tag name should not be empty or blank",
                assertThrows(ValidationException.class,
                        () -> tagService.create(Instancio.of(TYPE_CLASS)
                                .set(field(Tag::getName),  " ".repeat(random.nextInt(4, 31)))
                                .create()))
                        .getMessage());
    }

    @Test
    void create_ShouldThrowValidationException_IfNameIsShort() {
        assertEquals("tag name must be between 3 and 30 characters",
                assertThrows(ValidationException.class,
                        () -> tagService.create(Instancio.of(TYPE_CLASS)
                                .set(field(Tag::getName), "a".repeat(2))
                                .create()))
                        .getMessage());
    }

    @Test
    void create_ShouldThrowValidationException_IfNameIsLong() {
        assertEquals("tag name must be between 3 and 30 characters",
                assertThrows(ValidationException.class,
                        () ->tagService.create(Instancio.of(TYPE_CLASS)
                                .set(field(Tag::getName), "a".repeat(31))
                                .create()))
                        .getMessage());
    }

    @Test
    void create_ShouldThrowValidationException_IfNameContainsSpecial() {
        assertEquals("tag name must include only letters",
                assertThrows(ValidationException.class,
                        () -> tagService.create(Instancio.of(TYPE_CLASS)
                                .set(field(Tag::getName), "%".repeat(random.nextInt(4, 31)))
                                .create()))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("tag id should not be null",
                assertThrows(ValidationException.class,
                        () -> tagService.deleteById(null))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("tag id must be positive",
                assertThrows(ValidationException.class,
                        () -> tagService.deleteById(0L))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("tag id must be positive",
                assertThrows(ValidationException.class,
                        () -> tagService.deleteById(Long.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldNotThrowException_IfIdIsValid() {
        when(tagRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> tagService.deleteById(1L));
    }
}