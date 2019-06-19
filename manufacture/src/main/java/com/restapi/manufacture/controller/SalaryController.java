package com.restapi.manufacture.controller;

import com.restapi.manufacture.exception.DataNotFoundException;
import com.restapi.manufacture.model.*;
import com.restapi.manufacture.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/salary")
@Slf4j
public class SalaryController {

    @Autowired
    SalaryService salaryService;

    @PostMapping(value = "/add",
            produces = {
                    MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            consumes = {
                    MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }
    )
    public ResponseEntity addSalary(@Valid @RequestBody SalaryDetails salaryDetails) {
        SalaryRest returnValue = salaryService.addSalary(salaryDetails);
        return new ResponseEntity<>(returnValue, HttpStatus.OK);
    }

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Collection<SalaryRest>> getAllSalary() {
        return ResponseEntity.ok(salaryService.getAllSalary());
    }

    @GetMapping(value = "/{idJob}",
            produces = {
                    MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE}
    )
    public ResponseEntity<SalaryRest> getSalaryById(@PathVariable String idJob) {
        SalaryRest returnValue = salaryService.getSalaryById(idJob);
        if (returnValue != null) {
            log.info("GET SALARY BY JOB");
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Job Id Doesn't Exist");
        }
    }

    @PatchMapping(value = "/{job}",
            consumes = {
            MediaType.APPLICATION_XML_VALUE,
            MediaType.APPLICATION_JSON_VALUE
    },
            produces = {
                    MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE
            })
    public ResponseEntity<SalaryRest> updateSalary(@PathVariable String idJob, @Valid @RequestBody SalaryDetails salaryDetails) {
        SalaryRest returnValue = salaryService.updateSalary(idJob, salaryDetails);
        if (returnValue != null) {
            return new ResponseEntity<>(returnValue, HttpStatus.OK);
        } else {
            throw new DataNotFoundException("Can't find the job id");
        }
    }

    @DeleteMapping(value = "/{idJob}")
    public ResponseEntity<SalaryRest> deleteJob(@PathVariable String idJob) {
        SalaryRest returnValue = salaryService.deleteJob(idJob);
        if (returnValue != null) {
            log.info("DELETE JOB");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            throw new DataNotFoundException("The Job Id Doesn't Exist");
        }
    }
}
