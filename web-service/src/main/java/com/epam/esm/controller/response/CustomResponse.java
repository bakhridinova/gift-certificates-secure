package com.epam.esm.controller.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponse<T extends RepresentationModel<T>> {
    private RepresentationModel<T> body;
    private HttpStatus status;
    private String message;

    public CustomResponse(RepresentationModel<T> body) {
        this.body = body;
    }

    public CustomResponse(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
