package com.epam.esm.controller;

import com.epam.esm.controller.response.CustomResponse;
import com.epam.esm.dto.CertificateDto;
import com.epam.esm.dto.CertificatePriceDto;
import com.epam.esm.facade.CertificateFacade;
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
@RequestMapping("/api/v1/certificates")
public class CertificateController {
    private final CertificateFacade certificateFacade;

    /**
     * GET endpoint to retrieve page of certificates
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @return page of certificates
     */
    @GetMapping
    public Page<CertificateDto> getAllByPage(@RequestParam(defaultValue = "0") int page,
                                             @RequestParam(defaultValue = "5") int size) {
        return certificateFacade.findAllByPage(page, size);
    }

    /**
     * GET endpoint to retrieve specific certificate by its ID
     *
     * @param id of required certificate
     * @return specified certificate
     */
    @GetMapping("/{id}")
    public CustomResponse<CertificateDto> getById(@PathVariable Long id) {
        return new CustomResponse<>(certificateFacade.findById(id));
    }

    /**
     * GET endpoint to search for certificates based on search parameters
     *
     * @param page page number requested (default is 0)
     * @param size number of items per page (default is 5)
     * @param searchFilter holding search parameters
     * @return page of certificates based on provided search parameters
     */
    @PostMapping("/search")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public Page<CertificateDto> search(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "5") int size,
                                       @RequestBody(required = false) SearchFilter searchFilter) {
        return certificateFacade.findByFilterAndPage(searchFilter, page, size);
    }

    /**
     * POST endpoint for creating new certificate
     *
     * @param certificate representing new certificate to be created
     * @return certificate that was created
     */
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<CertificateDto> create(@RequestBody CertificateDto certificate) {
        return new CustomResponse<>(certificateFacade.create(certificate));
    }

    /**
     * PATCH endpoint for updating price of specific certificatePriceDto
     *
     * @param certificatePrice with certificate id and new price
     * @return updated certificatePriceDto
     */
    @PatchMapping
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<CertificateDto> updatePrice(@RequestBody CertificatePriceDto certificatePrice) {
        return new CustomResponse<>(certificateFacade.updatePrice(certificatePrice));
    }

    /**
     * DELETE for deleting specific certificate
     *
     * @param id of certificate to delete
     * @return message expressing that certificate was successfully deleted
     */
    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public CustomResponse<?> deleteById(@PathVariable Long id) {
        certificateFacade.deleteById(id);
        return new CustomResponse<>(HttpStatus.OK, "certificate was successfully deleted");
    }
}
