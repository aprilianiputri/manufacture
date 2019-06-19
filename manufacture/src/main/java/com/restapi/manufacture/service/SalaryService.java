package com.restapi.manufacture.service;

import com.restapi.manufacture.model.*;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Service("SalaryService")
public class SalaryService {

    SalaryRest returnValue;

    HashMap<String, SalaryRest> salary;

    public SalaryRest addSalary(SalaryDetails salaryDetails) {
        returnValue = new SalaryRest();

        returnValue.setId(salaryDetails.getId());
        String idJob = returnValue.getId();

        returnValue.setJob(salaryDetails.getJob());
        String job = returnValue.getJob();
        returnValue.setSalary(salaryDetails.getSalary());
        double grossSalary = grossSalary(salaryDetails.getSalary());
        returnValue.setGrossSalary(grossSalary);
        if (salary == null) {
            salary = new HashMap<>();
        }
        salary.put(idJob, returnValue);
        return returnValue;
    }

    public double grossSalary(Double salary){
        Double amount;
        amount = salary - 0.02*salary;
        return amount;
    }

    public Collection<SalaryRest> getAllSalary() {

        return salary.values();
    }

    public SalaryRest getSalaryById(String idJob) {
        return salary.get(idJob);
    }

    public SalaryRest updateSalary(String idJob, SalaryDetails salaryDetails) {
            if (salary.containsKey(idJob)) {
                SalaryRest storedSalary = salary.get(idJob);
                storedSalary.setSalary(salaryDetails.getSalary());
                double grossSalary = grossSalary(salaryDetails.getSalary());
                storedSalary.setGrossSalary(grossSalary);

                salary.put(idJob, storedSalary);
            }
            return salary.get(idJob);
    }

    public SalaryRest deleteJob(String idJob) {
        return salary.remove(idJob);
    }
}
