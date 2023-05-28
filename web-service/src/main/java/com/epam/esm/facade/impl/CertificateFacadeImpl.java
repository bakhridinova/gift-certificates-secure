package com.epam.esm.facade.impl;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatePriceDto;
import com.epam.esm.facade.CertificateFacade;
import com.epam.esm.service.CertificateService;
import com.epam.esm.util.filter.SearchFilter;
import com.epam.esm.util.hateoas.CertificateHateoasAdder;
import com.epam.esm.util.mapper.CertificateMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CertificateFacadeImpl implements CertificateFacade {
    private final CertificateHateoasAdder certificateHateoasAdder;
    private final CertificateService certificateService;
    private final CertificateMapper certificateMapper;

    @Override
    public Page<CertificateDto> findAllByPage(int page, int size) {
        Page<CertificateDto> certificates = certificateMapper.mapEntitiyPageToEntityDtoPage(
                certificateService.findAllByPage(page, size), certificateMapper);

        certificateHateoasAdder.addLinksToEntityPage(certificates);
        return certificates;
    }

    @Override
    public CertificateDto findById(Long id) {
        CertificateDto certificate = certificateMapper
                .toEntityDto(certificateService.findById(id));

        certificateHateoasAdder.addLinksToEntity(certificate);
        return certificate;
    }

    @Override
    public Page<CertificateDto> findByFilterAndPage(SearchFilter searchFilter, int page, int size) {
        Page<CertificateDto> certificates = certificateMapper.mapListToPage(
                certificateService.findByFilterAndPage(searchFilter, page, size).stream()
                        .map(certificateMapper::toEntityDto).toList(), searchFilter.pageableWithSort(page, size));

        certificateHateoasAdder.addLinksToEntityPage(certificates);
        return certificates;
    }

    @Override
    public CertificateDto updatePrice(CertificatePriceDto certificatePrice) {
        CertificateDto certificate = certificateMapper.toEntityDto(
                certificateService.updatePrice(certificateMapper.toEntity(certificatePrice)));

        certificateHateoasAdder.addLinksToEntity(certificate);
        return certificate;
    }

    @Override
    public CertificateDto create(CertificateDto certificate) {
        certificate = certificateMapper.toEntityDto(certificateService
                .create(certificateMapper.toEntity(certificate)));

        certificateHateoasAdder.addLinksToEntity(certificate);
        return certificate;
    }

    @Override
    public void deleteById(Long id) {
        certificateService.deleteById(id);
    }
}
