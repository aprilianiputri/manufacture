package com.restapi.manufacture.service;

import com.restapi.manufacture.exception.DataNotFoundException;
import com.restapi.manufacture.model.EmployeeDetails;
import com.restapi.manufacture.model.EmployeeRest;
import com.restapi.manufacture.model.SalaryRest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;

@Slf4j
@Service("EmployeeService")
public class EmployeeService{

    EmployeeRest returnValue;

    HashMap<String, EmployeeRest> employee;

    @Autowired
    SalaryService salaryService;

    public EmployeeRest addEmployee(EmployeeDetails employeeDetails) {
        returnValue = new EmployeeRest();

        returnValue.setId(employeeDetails.getId());
        String id = returnValue.getId();

        returnValue.setFullName(employeeDetails.getFullName());
        returnValue.setPhoneNumber(employeeDetails.getPhoneNumber());
        returnValue.setAddress(employeeDetails.getAddress());
        returnValue.setStatus(employeeDetails.getStatus());
        returnValue.setIdJob(employeeDetails.getIdJob());
        String idJob = returnValue.getIdJob();
        SalaryRest idJobEmployee = salaryService.getSalaryById(idJob);
        if (idJobEmployee != null) {
            if (returnValue.getStatus().contains("Active")) {
                SalaryRest salaryRest = salaryService.getSalaryById(idJob);
                returnValue.setJob(salaryRest.getJob());
                returnValue.setSalary(salaryRest.getSalary());
                returnValue.setGrossSalary(salaryRest.getGrossSalary());
            } else {
                SalaryRest salaryRest = salaryService.getSalaryById(idJob);
                returnValue.setJob(salaryRest.getJob());
                returnValue.setSalary(0.0);
                returnValue.setGrossSalary(0.0);
            }
        } else {
            throw new DataNotFoundException("Job Doesn't Exist");
        }

        if (employee == null) {
            employee = new HashMap<>();
        }
        employee.put(id, returnValue);
        return returnValue;
    }

    public EmployeeRest getEmployeeById (String id) {

        return employee.get(id);
    }

    public Collection<EmployeeRest> getAllEmployee() {
        return employee.values();
    }

    public EmployeeRest updateJob(String id, EmployeeRest employeeRest) {
        if (employee.containsKey(id)) {
            EmployeeRest storedJob = employee.get(id);
            storedJob.setIdJob(employeeRest.getIdJob());
            String idJob = storedJob.getIdJob();
            SalaryRest salaryRest = salaryService.getSalaryById(idJob);
            if (salaryRest != null) {
                storedJob.setJob((employeeRest.getJob()));
//            SalaryRest salaryRest = salaryService.getSalaryById(idJob);
                EmployeeRest storedSalary = employee.get(id);
                storedSalary.setJob(salaryRest.getJob());
                storedSalary.setSalary(salaryRest.getSalary());
                storedSalary.setGrossSalary(salaryRest.getGrossSalary());

                employee.put(id, storedJob);
            } else {
                throw new DataNotFoundException("Can't find the idJob = " + idJob);
            }
        }
        return employee.get(id);
    }

    public EmployeeRest updatePhoneNumber(String id, EmployeeDetails employeeDetails) {
        if (employee.containsKey(id)) {
            EmployeeRest storedPhoneNumber = employee.get(id);
            storedPhoneNumber.setPhoneNumber(employeeDetails.getPhoneNumber());

            employee.put(id, storedPhoneNumber);
        }
        return employee.get(id);
    }

    public EmployeeRest updateAddress(String id, EmployeeDetails employeeDetails) {
        if (employee.containsKey(id)) {
            EmployeeRest storedAddress = employee.get(id);
            storedAddress.setAddress(employeeDetails.getAddress());

            employee.put(id, storedAddress);
        }
        return employee.get(id);
    }

    public EmployeeRest updateStatus(String id, EmployeeRest employeeRest) {
        if (employee.containsKey(id)) {
            EmployeeRest storedStatus = employee.get(id);
            storedStatus.setStatus(employeeRest.getStatus());
            if (storedStatus.getStatus().contains("Active")){
                EmployeeRest salaryRest = employee.get(id);
                storedStatus.setIdJob(salaryRest.getIdJob());
                String idJob = storedStatus.getIdJob();
                SalaryRest salaryRest1 = salaryService.getSalaryById(idJob);
                storedStatus.setJob(salaryRest1.getJob());
                storedStatus.setSalary(salaryRest1.getSalary());
                storedStatus.setGrossSalary(salaryRest1.getGrossSalary());
            } else {
                EmployeeRest salaryRest = employee.get(id);
                storedStatus.setIdJob(salaryRest.getIdJob());
                storedStatus.setJob(salaryRest.getJob());
                storedStatus.setSalary(0.0);
                storedStatus.setGrossSalary(0.0);
            }

            employee.put(id, storedStatus);
        }
        return employee.get(id);
    }

    public EmployeeRest deleteEmployee(String id) {
            return employee.remove(id);
    }

}
