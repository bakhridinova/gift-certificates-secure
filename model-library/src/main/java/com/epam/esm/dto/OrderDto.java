package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * Class representing data transfer object for order
 *
 * @author bakhridinova
 */

@Data
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto extends RepresentationModel<OrderDto> implements Mappable {
    private Long id;

    private Double price;

    private String status;

    private LocalDateTime createdAt;

    private Long userId;

    private Long certificateId;
}
