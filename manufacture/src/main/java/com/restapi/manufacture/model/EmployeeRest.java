package com.restapi.manufacture.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmployeeRest {

    private String id;
    private String fullName;
    private String phoneNumber;
    private String address;
    private String status;
    private String idJob;
    private String job;
    private Double salary;
    private Double grossSalary;
}
