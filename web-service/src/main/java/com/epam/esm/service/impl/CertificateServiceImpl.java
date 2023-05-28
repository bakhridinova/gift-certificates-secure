package com.epam.esm.service.impl;

import com.epam.esm.entity.Certificate;
import com.epam.esm.exception.EntityNotFoundException;
import com.epam.esm.repository.CertificateRepository;
import com.epam.esm.repository.TagRepository;
import com.epam.esm.service.CertificateService;
import com.epam.esm.util.enums.field.CertificateField;
import com.epam.esm.util.filter.SearchFilter;
import com.epam.esm.util.validator.CustomCertificateValidator;
import com.epam.esm.util.validator.CustomPageValidator;
import com.epam.esm.util.validator.CustomSortValidator;
import com.epam.esm.util.validator.CustomTagValidator;
import com.epam.esm.util.validator.CustomValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CertificateServiceImpl implements CertificateService {
    private final CertificateRepository certificateRepository;
    private final TagRepository tagRepository;

    @Override
    public Page<Certificate> findAllByPage(int page, int size) {
        CustomPageValidator.validate(page, size);

        return certificateRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Certificate findById(Long id) {
        CustomValidator.validateId(CertificateField.ID, id);

        return certificateRepository
                .findById(id).orElseThrow(() -> new EntityNotFoundException(Certificate.class, id));
    }

    @Override
    public List<Certificate> findByFilterAndPage(SearchFilter searchFilter, int page, int size) {
        CustomPageValidator.validate(page, size);
        CustomSortValidator.validate(searchFilter.sortProperty(), searchFilter.sortOrder());
        CustomTagValidator.validate(searchFilter.tags().stream().toList(), true);

        return certificateRepository.findByFilter(searchFilter);
    }

    @Override
    @Transactional
    public Certificate updatePrice(Certificate certificate) {
        Long id = certificate.getId();
        Double price = certificate.getPrice();

        CustomValidator.validateId(CertificateField.ID, id);
        CustomCertificateValidator.validatePrice(price);

        certificate = certificateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Certificate.class, id));
        certificate.setPrice(price);
        return certificate;
    }

    @Override
    @Transactional
    public Certificate create(Certificate certificate) {
        CustomCertificateValidator.validate(certificate, false);

        certificate.getTags().stream().filter(tag -> tag.getId() == null)
                .forEach(tag -> tag.setId(tagRepository.save(tag).getId()));

        return certificateRepository.save(certificate);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        CustomValidator.validateId(CertificateField.ID, id);

        certificateRepository.deleteById(id);
    }
}
