package com.restapi.manufacture.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class BudgetRest {

    private String totalEmployee;
    private Double totalBudgetEmployeePerMonth;
    private Double totalBudgetPerYear;
}
