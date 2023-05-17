package com.epam.esm.service.impl;

import com.epam.esm.dto.CertificateDto;
import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.CustomEntityNotFoundException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.util.enums.field.CertificateField;
import com.epam.esm.util.filter.SearchFilter;
import com.epam.esm.util.hateoas.HateoasAdder;
import com.epam.esm.util.mapper.CertificateMapper;
import com.epam.esm.util.mapper.TagMapper;
import com.epam.esm.util.validator.CustomCertificateValidator;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomSortValidator;
import com.epam.esm.util.validator.CustomTagValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final CertificateMapper certificateMapper;
    private final HateoasAdder<CertificateDto> certificateHateoasAdder;
    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public Page<CertificateDto> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        Pageable pageable = PageRequest.of(page, size);
        Page<CertificateDto> certificates = certificateMapper.mapEntitiyPageToEntityDtoPage(certificateRepository
                .findAll(pageable), certificateMapper);

        certificateHateoasAdder.addLinksToEntityPage(certificates);
        return certificates;
    }

    @Override
    public CertificateDto findById(Long id) {
        CustomValidator.validateId(CertificateField.ID, id);

        CertificateDto certificate = certificateMapper.toEntityDto(certificateRepository
                .findById(id).orElseThrow(() -> new CustomEntityNotFoundException(Certificate.class, id)));

        certificateHateoasAdder.addLinksToEntity(certificate);
        return certificate;
    }

    @Override
    public Page<CertificateDto> findByFilterAndPage(SearchFilter searchFilter, int page, int size) {
        CustomPageValidator.validate(page, size);
        CustomSortValidator.validate(searchFilter.sortProperty(), searchFilter.sortOrder());
        CustomTagValidator.validate(searchFilter.tags().stream().map(tagMapper::toEntityDto).toList());

        Pageable pageable = PageRequest.of(page, size).withSort(Sort.by(Sort.Direction.valueOf(
                searchFilter.sortOrder().toUpperCase()), searchFilter.sortProperty()));
        Page<CertificateDto> certificates = certificateMapper.mapListToPage(certificateRepository
                .findByFilterAndPage(searchFilter), pageable).map(certificateMapper::toEntityDto);

        certificateHateoasAdder.addLinksToEntityPage(certificates);
        return certificates;

    }

    @Override
    @Transactional
    public CertificateDto updatePriceById(Long id, CertificateDto certificateDto) {
        CustomValidator.validateId(CertificateField.ID, id);
        CustomCertificateValidator.validatePrice(certificateDto.getPrice());

        Certificate certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new CustomEntityNotFoundException(Certificate.class, id));
        certificate.setPrice(certificateDto.getPrice());
        certificateDto = certificateMapper.toEntityDto(certificate);

        certificateHateoasAdder.addLinksToEntity(certificateDto);
        return certificateDto;
    }

    @Override
    @Transactional
    public CertificateDto create(CertificateDto certificateDto) {
        CustomCertificateValidator.validate(certificateDto, false);

        certificateDto.getTags().stream().filter(tag -> tag.getId() == null)
                .forEach(tag -> tag.setId(tagRepository.save(tagMapper.toEntity(tag)).getId()));

        certificateDto = certificateMapper.toEntityDto(certificateRepository
                .save(certificateMapper.toEntity(certificateDto)));

        certificateHateoasAdder.addLinksToEntity(certificateDto);
        return certificateDto;
    }

    @Override
    @Transactional
    public String deleteById(Long id) {
        CustomValidator.validateId(CertificateField.ID, id);

        certificateRepository.deleteById(id);
        return "certificate was successfully deleted";
    }
}
