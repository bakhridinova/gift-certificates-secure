package com.epam.esm.util.mapper;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring")
public interface CertificateMapper extends BaseMapper<Certificate, CertificateDto> {
    @Override
    @BeanMapping(unmappedTargetPolicy = ReportingPolicy.IGNORE)
    CertificateDto toEntityDto(Certificate certificate);
}
