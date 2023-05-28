package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDateTime;

/**
 * Class representing data transfer object for token
 *
 * @author bakhridinova
 */

@Data
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenDto extends RepresentationModel<TokenDto> implements Mappable {
    public Long id;
    public String accessToken;
    public String type;
    private LocalDateTime createdAt;
    public boolean expired;
    public Long userId;
}
