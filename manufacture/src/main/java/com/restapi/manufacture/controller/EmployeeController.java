package com.restapi.manufacture.controller;

import com.restapi.manufacture.exception.DataNotFoundException;
import com.restapi.manufacture.model.EmployeeDetails;
import com.restapi.manufacture.model.EmployeeRest;
import com.restapi.manufacture.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping(value = "/add",
            produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
            )
    public ResponseEntity addEmployee(@Valid @RequestBody EmployeeDetails employeeDetails) {
        EmployeeRest returnValue = employeeService.addEmployee(employeeDetails);
        return new ResponseEntity<>(returnValue, HttpStatus.CREATED);
    }

    @GetMapping(value = "/{id}",
            produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<EmployeeRest> getEmployeeById(@PathVariable String id) {
        EmployeeRest returnValue = employeeService.getEmployeeById(id);
        if (returnValue != null) {
            log.info("GET EMPLOYEE BY ID");
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Employee Id Doesn't Exist");
        }
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<EmployeeRest>> getAllEmployee() {
        return ResponseEntity.ok(employeeService.getAllEmployee());
    }

    @PatchMapping(value = "/{id}/job", consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<EmployeeRest> updateJob(@PathVariable String id, @Valid @RequestBody EmployeeRest employeeRest) {
        EmployeeRest returnValue = employeeService.updateJob(id, employeeRest);
        if (returnValue != null) {
            log.info("UPDATE JOB EMPLOYEE");
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Employee Id Doesn't Exist");
        }
    }

    @PatchMapping(value = "/{id}/phone",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
            },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<EmployeeRest> updatePhoneNumber(@PathVariable String id, @Valid @RequestBody EmployeeDetails employeeDetails) {
        EmployeeRest returnValue = employeeService.updatePhoneNumber(id, employeeDetails);
        if (returnValue != null) {
            log.info("UPDATE PHONE NUMBER EMPLOYEE");
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Employee Id Doesn't Exist");
        }
    }

    @PatchMapping(value = "/{id}/address",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<EmployeeRest> updateAddress(@PathVariable String id, @Valid @RequestBody EmployeeDetails employeeDetails) {
        EmployeeRest returnValue = employeeService.updateAddress(id, employeeDetails);
        if (returnValue != null) {
            log.info("UPDATE ADDRESS EMPLOYEE");
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Employee Id Doesn't Exist");
        }
    }

    @PatchMapping(value = "/{id}/status",consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<EmployeeRest> updateStatus(@PathVariable String id, @Valid @RequestBody EmployeeRest employeeRest) {
        EmployeeRest returnValue = employeeService.updateStatus(id, employeeRest);
        if (returnValue != null) {
            log.info("UPDATE STATUS EMPLOYEE");
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Employee Id Doesn't Exist");
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<EmployeeRest> deleteEmployee(@PathVariable String id) {
        EmployeeRest returnValue = employeeService.deleteEmployee(id);
        if (returnValue != null) {
            log.info("DELETE EMPLOYEE");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Employee Id Doesn't Exist");
        }
    }
}
