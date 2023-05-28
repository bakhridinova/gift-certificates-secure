package com.epam.esm.util.mapper;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CertificateMapperTest {
    private final CertificateMapper certificateMapper;

    public CertificateMapperTest() {
        this.certificateMapper = new CertificateMapperImpl();
    }

    private static final Certificate NULL_CERTIFICATE =
            Certificate.builder().build();
    private static final CertificateDto NULL_CERTIFICATE_DTO =
            CertificateDto.builder().build();

    private static final Certificate NON_NULL_CERTIFICATE =
            Certificate.builder().id(0L).build();
    private static final CertificateDto NON_NULL_CERTIFICATE_DTO =
            CertificateDto.builder().id(0L).build();

    @Test
    void shouldMapToEntityCorrectlyTest() {
        assertNull(certificateMapper.toEntity((CertificateDto) null));
        assertEquals(NULL_CERTIFICATE, certificateMapper.toEntity(NULL_CERTIFICATE_DTO));
        assertEquals(NON_NULL_CERTIFICATE, certificateMapper.toEntity(NON_NULL_CERTIFICATE_DTO));
    }


    @Test
    void shouldMapToEntityDtoCorrectlyTest() {
        assertNull(certificateMapper.toEntityDto(null));
        assertEquals(NULL_CERTIFICATE_DTO, certificateMapper.toEntityDto(NULL_CERTIFICATE));
        assertEquals(NON_NULL_CERTIFICATE_DTO, certificateMapper.toEntityDto(NON_NULL_CERTIFICATE));
    }
}