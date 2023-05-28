package com.epam.esm.service;

import com.epam.esm.entity.Token;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.ValidationException;
import com.epam.esm.repository.TokenRepository;
import com.epam.esm.service.impl.TokenServiceImpl;
import org.instancio.Instancio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class TokenServiceTest {
    @Mock
    private TokenRepository tokenRepository;

    @InjectMocks
    private TokenServiceImpl tokenService;

    private static final Class<Token> TYPE_CLASS = Token.class;

    @Test
    void contextLoads() {
        assertNotNull(tokenService);
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsNegative() {
        assertEquals("page number should not be negative",
                assertThrows(ValidationException.class,
                        () -> tokenService.findAllByPage(Integer.MIN_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsNegative() {
        assertEquals("page size should not be negative",
                assertThrows(ValidationException.class,
                        () -> tokenService.findAllByPage(0, Integer.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsTooHigh() {
        assertEquals("page number must be between 0 and 10000",
                assertThrows(ValidationException.class,
                        () -> tokenService.findAllByPage(Integer.MAX_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsTooHigh() {
        assertEquals("page size must be between 0 and 100",
                assertThrows(ValidationException.class,
                        () -> tokenService.findAllByPage(0, Integer.MAX_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("token id should not be null",
                assertThrows(ValidationException.class,
                        () -> tokenService.findById(null))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("token id must be positive",
                assertThrows(ValidationException.class,
                        () -> tokenService.findById(0L))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("token id must be positive",
                assertThrows(ValidationException.class,
                        () -> tokenService.findById(Long.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowNotFoundException_IfOptionalIsEmpty() {
        when(tokenRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        String message = String.format("failed to find %s by id %d",
                TYPE_CLASS.getSimpleName(), 1L);

        assertEquals(message,
                assertThrows(EntityNotFoundException.class,
                        () -> tokenService.findById(1L))
                        .getMessage());
    }

    @Test
    void findById_ShouldNotThrowException_IfOptionalIsPresent() {
        when(tokenRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> tokenService.findById(1L));
    }

    @Test
    void findByUserId_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("user id should not be null",
                assertThrows(ValidationException.class,
                        () -> tokenService.findByUserIdAndPage(null, 0, 1))
                        .getMessage());
    }

    @Test
    void findByUserId_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("user id must be positive",
                assertThrows(ValidationException.class,
                        () -> tokenService.findByUserIdAndPage(0L, 0, 1))
                        .getMessage());
    }

    @Test
    void findByUserId_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("user id must be positive",
                assertThrows(ValidationException.class,
                        () -> tokenService.findByUserIdAndPage(Long.MIN_VALUE, 0, 1))
                        .getMessage());
    }
}