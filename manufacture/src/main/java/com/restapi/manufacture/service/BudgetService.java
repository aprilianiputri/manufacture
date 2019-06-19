package com.restapi.manufacture.service;

import com.restapi.manufacture.model.BudgetDetails;
import com.restapi.manufacture.model.BudgetRest;
import com.restapi.manufacture.model.EmployeeRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service("BudgetService")
public class BudgetService {

    HashMap<String, BudgetRest> budget;

    @Autowired
    EmployeeService employeeService;

    public BudgetRest totalBudgetPerMonth(BudgetDetails budgetDetails) {

        BudgetRest returnValue = new BudgetRest();
        int totalEmployee = employeeService.getAllEmployee().size();
        log.info(Integer.toString(totalEmployee));
        Double salaryPerMonth=0.0;
        Double totalSalary=0.0;
        Double budgetPerYear=0.0;

        for (int i = 1; i<=totalEmployee; i++) {
            String id = Integer.toString(i);
            EmployeeRest employeeRest = employeeService.getEmployeeById(id);
            log.info(employeeRest.toString());
            log.info(employeeRest.getSalary().toString());

            returnValue.setTotalBudgetEmployeePerMonth(employeeRest.getSalary());
            log.info(returnValue.getTotalBudgetEmployeePerMonth().toString());

            Double salary = returnValue.getTotalBudgetEmployeePerMonth();
            log.info(salary.toString());
            salaryPerMonth += salary;
            totalSalary = (salary*12) + salary*0.5 + salary;
            budgetPerYear += totalSalary;
            returnValue.setTotalBudgetEmployeePerMonth(salaryPerMonth);
            returnValue.setTotalBudgetPerYear(budgetPerYear);
            if (budget == null) {
                budget = new HashMap<>();
            }
            returnValue.setTotalEmployee(Integer.toString(totalEmployee));
            budget.put(Integer.toString(totalEmployee), returnValue);
        }
        return returnValue;
    }
}
