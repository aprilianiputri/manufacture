package com.restapi.manufacture.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NotNull
public class EmployeeDetails {

    @Size(min = 1, message = "can not be empty")
    private String id;

    @Size(min = 2, message = "must not be less than 2 characters")
    private String fullName;

    @Size(min = 9, max = 15, message = "must be equal or greater than 9 characters and less than 16 characters")
    private String phoneNumber;

    @Size(min = 2, message = "must not be less than 2 characters")
    private String address;

    @Size(min = 2, message = "must not be less than 2 characters")
    private String status;

    @Size(min = 1, message = "can not be empty")
    private String idJob;
}
