package com.restapi.manufacture.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class SalaryRest {

    private String id;
    private String job;
    private Double salary;
    private Double grossSalary;
}
