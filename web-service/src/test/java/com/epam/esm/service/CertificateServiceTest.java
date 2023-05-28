package com.epam.esm.service;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.exception.ValidationException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.impl.CertificateServiceImpl;
import com.epam.esm.util.filter.SearchFilter;
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
class CertificateServiceTest {
    @Mock
    private CertificateRepository certificateRepository;
    @Mock
    private TagRepository tagRepository;

    @InjectMocks
    private CertificateServiceImpl certificateService;

    private final Random random = new Random();
    private static final Class<Certificate> TYPE_CLASS =
            Certificate.class;

    @Test
    void contextLoads() {
        assertNotNull(certificateService);
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsNegative() {
        assertEquals("page number should not be negative",
                assertThrows(ValidationException.class,
                        () -> certificateService.findAllByPage(Integer.MIN_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsNegative() {
        assertEquals("page size should not be negative",
                assertThrows(ValidationException.class,
                () -> certificateService.findAllByPage(0, Integer.MIN_VALUE))
                .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfPageIsTooHigh() {
        assertEquals("page number must be between 0 and 10000",
                assertThrows(ValidationException.class,
                        () -> certificateService.findAllByPage(Integer.MAX_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findAll_ShouldThrowValidationException_IfSizeIsTooHigh() {
        assertEquals("page size must be between 0 and 100",
                assertThrows(ValidationException.class,
                        () -> certificateService.findAllByPage(0, Integer.MAX_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("certificate id should not be null",
                assertThrows(ValidationException.class,
                        () -> certificateService.findById(null))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("certificate id must be positive",
                assertThrows(ValidationException.class,
                        () -> certificateService.findById(0L))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("certificate id must be positive",
                assertThrows(ValidationException.class,
                        () -> certificateService.findById(Long.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findById_ShouldThrowNotFoundException_IfOptionalIsEmpty() {
        when(certificateRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        String message = String.format("failed to find %s by id %d",
                TYPE_CLASS.getSimpleName(), 1L);

        assertEquals(message,
                assertThrows(EntityNotFoundException.class,
                        () -> certificateService.findById(1L))
                        .getMessage());
    }

    @Test
    void findById_ShouldNotThrowException_IfOptionalIsPresent() {
        when(certificateRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> certificateService.findById(1L));
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfPageIsNegative() {
        assertEquals("page number should not be negative",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.create(SearchFilter.class), Integer.MIN_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfSizeIsNegative() {
        assertEquals("page size should not be negative",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.create(SearchFilter.class), 0, Integer.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfPageIsTooHigh() {
        assertEquals("page number must be between 0 and 10000",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.create(SearchFilter.class), Integer.MAX_VALUE, 0))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfSizeIsTooHigh() {
        assertEquals("page size must be between 0 and 100",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.create(SearchFilter.class), 0, Integer.MAX_VALUE))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfSortPropertyIsBlank() {
        assertEquals("sort property should not be empty or blank",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.of(SearchFilter.class)
                                        .set(field(SearchFilter::sortProperty), "")
                                        .create(), 0, 0))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfSortOrderIsBlank() {
        assertEquals("sort order should not be empty or blank",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.of(SearchFilter.class)
                                        .set(field(SearchFilter::sortProperty), "id")
                                        .set(field(SearchFilter::sortOrder), "")
                                        .create(), 0, 0))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfSortPropertyIsRandomString() {
        assertEquals("sort property should not be empty or blank",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.of(SearchFilter.class)
                                        .set(field(SearchFilter::sortProperty), "")
                                        .create(), 0, 0))
                        .getMessage());
    }

    @Test
    void findByFilter_ShouldThrowValidationException_IfSortOrderIsRandomString() {
        assertEquals("sort order should not be empty or blank",
                assertThrows(ValidationException.class,
                        () -> certificateService.findByFilterAndPage(
                                Instancio.of(SearchFilter.class)
                                        .set(field(SearchFilter::sortProperty), "id")
                                        .set(field(SearchFilter::sortOrder), "")
                                        .create(), 0, 0))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("certificate id should not be null",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), null)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("certificate id must be positive",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), 0L)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("certificate id must be positive",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                        Instancio.of(TYPE_CLASS)
                                                .set(field(Certificate::getId), Long.MIN_VALUE)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfPriceIsNull() {
        assertEquals("certificate price should not be null",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), 1L)
                                        .set(field(Certificate::getPrice), null)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfPriceIsNegative() {
        assertEquals("certificate price should not be negative",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), 1L)
                                        .set(field(Certificate::getPrice), -1d)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfPriceIsLow() {
        assertEquals("certificate price must be between 10 and 100",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), 1L)
                                        .set(field(Certificate::getPrice), 9d)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowValidationException_IfPriceIsHigh() {
        assertEquals("certificate price must be between 10 and 100",
                assertThrows(ValidationException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), 1L)
                                        .set(field(Certificate::getPrice), 101d)
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldThrowNotFoundException_IfOptionalIsEmpty() {
        when(certificateRepository.findById(anyLong()))
                .thenReturn(Optional.empty());

        String message = String.format("failed to find %s by id %d",
                TYPE_CLASS.getSimpleName(), 1L);

        assertEquals(message,
                assertThrows(EntityNotFoundException.class,
                        () -> certificateService.updatePrice(
                                Instancio.of(TYPE_CLASS)
                                        .set(field(Certificate::getId), 1L)
                                        .set(field(Certificate::getPrice),
                                                random.nextDouble(10, 101))
                                        .create()))
                        .getMessage());
    }

    @Test
    void updatePrice_ShouldNotThrowException_IfOptionalIsPresent() {
        when(certificateRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> certificateService.findById(1L));
    }

    @Test
    void deleteById_ShouldThrowValidationException_IfIdIsNull() {
        assertEquals("certificate id should not be null",
                assertThrows(ValidationException.class,
                        () -> certificateService.deleteById(null))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldThrowValidationException_IfIdIsZero() {
        assertEquals("certificate id must be positive",
                assertThrows(ValidationException.class,
                        () -> certificateService.deleteById(0L))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldThrowValidationException_IfIdIsNegative() {
        assertEquals("certificate id must be positive",
                assertThrows(ValidationException.class,
                        () -> certificateService.deleteById(Long.MIN_VALUE))
                        .getMessage());
    }

    @Test
    void deleteById_ShouldNotThrowException_IdIsValid() {
        when(certificateRepository.findById(anyLong()))
                .thenReturn(Optional.of(Instancio.create(TYPE_CLASS)));

        assertDoesNotThrow(() -> certificateService.deleteById(1L));
    }
}