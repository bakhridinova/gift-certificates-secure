package com.epam.esm.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class representing data transfer object for user credentials
 *
 * @author bakhridinova
 */

@Getter
@AllArgsConstructor
public class UserCredentialsDto {
    private String username;
    private String password;
}
