package com.epam.esm.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

/**
 * Class representing data transfer object for user
 *
 * @author bakhridinova
 */


@Data
@Getter
@Builder
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto extends RepresentationModel<UserDto> implements Mappable {
    private Long id;

    private String role;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String emailAddress;

    private LocalDate birthDate;
}
