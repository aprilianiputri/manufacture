package com.restapi.manufacture.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@NotNull
public class BudgetDetails {

    private String totalEmployee;
    private Double totalBudgetEmployeePerMonth;
}
