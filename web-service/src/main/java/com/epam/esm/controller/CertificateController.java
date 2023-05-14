package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.service.CertificateService;
import com.epam.esm.util.filter.SearchFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/certificates")
public class CertificateController {
    private final CertificateService certificateService;

    /**
     * GET endpoint to retrieve list of certificates
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return page of certificates
     */
    @GetMapping
    public Page<CertificateDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {
        return certificateService.findAllByPage(page, size);
    }

    /**
     * GET endpoint to retrieve specific certificate by its ID
     *
     * @param id of required certificate
     * @return specified certificate
     */
    @GetMapping("/{id}")
    public CustomResponse<CertificateDto> getById(@PathVariable Long id) {
        return new CustomResponse<>(certificateService.findById(id));
    }

    /**
     * GET endpoint to search for certificates based on search parameters
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @param searchFilter holding search parameters
     * @return List of certificates based on provided search parameters
     */
    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<CertificateDto> search(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestBody(required = false) SearchFilter searchFilter) {
        return certificateService.findByFilterAndPage(searchFilter, page, size);
    }

    /**
     * handles POST requests for creating new certificate
     *
     * @param certificate representing new certificate to be created
     * @return certificate that was created
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<CertificateDto> create(@RequestBody CertificateDto certificate) {
        return new CustomResponse<>(certificateService.create(certificate));
    }

    /**
     * handles PATCH requests for updating price of specific certificate
     *
     * @param id ID of certificate to update
     * @param certificate with new price
     * @return updated certificate
     */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<CertificateDto> updatePriceById(@PathVariable Long id,
                                                          @RequestBody CertificateDto certificate) {
        return new CustomResponse<>(certificateService.updatePriceById(id, certificate));
    }

    /**
     * handles DELETE requests for deleting specific certificate
     *
     * @param id of certificate to delete
     * @return message expressing that certificate was successfully deleted
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<?> deleteById(@PathVariable Long id) {
        return new CustomResponse<>(HttpStatus.OK, certificateService.deleteById(id));
    }
}
