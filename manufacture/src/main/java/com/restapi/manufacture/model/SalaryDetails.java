package com.restapi.manufacture.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
@NotNull
public class SalaryDetails {

    @Size(min = 1, message = "can not be empty")
    private String id;
    @Size(min = 2, message = "must not be less than 2 characters")
    private String job;

    private Double salary;
}
