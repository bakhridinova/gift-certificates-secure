package com.epam.esm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class representing data transfer object for editing certificate price
 *
 * @author bakhridinova
 */

@Getter
@AllArgsConstructor
public class CertificatePriceDto {
    private Long id;
    private Double price;
}
