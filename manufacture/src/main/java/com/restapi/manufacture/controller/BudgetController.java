package com.restapi.manufacture.controller;

import com.restapi.manufacture.model.BudgetDetails;
import com.restapi.manufacture.model.BudgetRest;
import com.restapi.manufacture.service.BudgetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/budget")
public class BudgetController {

    @Autowired
    BudgetService budgetService;

    @GetMapping(produces = {
            MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BudgetRest> totalBudgetPerMonth(BudgetDetails budgetDetails) {
        return ResponseEntity.ok(budgetService.totalBudgetPerMonth(budgetDetails));
    }
}
