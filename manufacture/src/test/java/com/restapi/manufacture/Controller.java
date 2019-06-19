package com.restapi.manufacture;

import com.restapi.manufacture.controller.BudgetController;
import com.restapi.manufacture.controller.EmployeeController;
import com.restapi.manufacture.controller.SalaryController;
import com.restapi.manufacture.exception.DataNotFoundException;
import com.restapi.manufacture.model.*;
import com.restapi.manufacture.service.BudgetService;
import com.restapi.manufacture.service.EmployeeService;
import com.restapi.manufacture.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Controller {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    SalaryService salaryService;

    @Autowired
    BudgetService budgetService;

    @Autowired
    EmployeeController employeeController;

    @Autowired
    SalaryController salaryController;

    @Autowired
    BudgetController budgetController;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void addSalaryTest() {
        SalaryDetails salary = new SalaryDetails();
        salary.setId("1");
        salary.setJob("Engineer");
        salary.setSalary(8000000.00);
        double salary1 = salary.getSalary();


        log.info("-------- Parameter that we POST --------");
        log.info("id=" + salary.getId());
        log.info("job=" + salary.getJob());
        log.info("salary=" + salary.getSalary());


        SalaryRest add = salaryService.addSalary(salary);
        double grossSalary = salary1 - 0.02 * salary1;

        assertThat(add.getId()).isEqualTo(salary.getId());
        assertThat(add.getJob()).isEqualTo(salary.getJob());
        assertThat(add.getSalary()).isEqualTo(salary.getSalary());
        assertThat(add.getGrossSalary()).isEqualTo(grossSalary);

        ResponseEntity responseEntity = salaryController.addSalary(salary);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("----------- Posted Parameter -----------");
        log.info(add.toString());
    }

    @Test
    public void addEmployeeTest() {

        SalaryDetails salaryDetails = new SalaryDetails();

        EmployeeDetails employee = new EmployeeDetails();
        employee.setId("1");
        employee.setFullName("Apriliani Putri");
        employee.setPhoneNumber("08129834783");
        employee.setAddress("Jakarta Barat");
        employee.setStatus("Active");
        employee.setIdJob("1");
        String idJob = employee.getIdJob();

        SalaryRest salaryResp = salaryService.getSalaryById(idJob);

        salaryDetails.setJob(salaryResp.getJob());
        salaryDetails.setSalary(salaryResp.getSalary());
        double grossSalary = salaryDetails.getSalary() - 0.02 * salaryDetails.getSalary();

        log.info("-------- Parameter that we POST --------");
        log.info("id=" + employee.getId());
        log.info("fullName=" + employee.getFullName());
        log.info("phoneNumber=" + employee.getPhoneNumber());
        log.info("address=" + employee.getAddress());
        log.info("status=" + employee.getStatus());
        log.info("idJob=" + employee.getIdJob());


        EmployeeRest add = employeeService.addEmployee(employee);

        assertThat(add.getId()).isEqualTo(employee.getId());
        assertThat(add.getFullName()).isEqualTo(employee.getFullName());
        assertThat(add.getPhoneNumber()).isEqualTo(employee.getPhoneNumber());
        assertThat(add.getAddress()).isEqualTo(employee.getAddress());
        assertThat(add.getStatus()).isEqualTo(employee.getStatus());
        if (add.getStatus().contains("Active")) {
            assertThat(add.getIdJob()).isEqualTo(employee.getIdJob());
            assertThat(add.getJob()).isEqualTo(salaryDetails.getJob());
            assertThat(add.getSalary()).isEqualTo(salaryDetails.getSalary());
            assertThat(add.getGrossSalary()).isEqualTo(grossSalary);
        } else {
            assertThat(add.getIdJob()).isNotEqualTo(employee.getIdJob());
            assertThat(add.getJob()).isNotEqualTo(salaryDetails.getJob());
            assertThat(add.getSalary()).isNotEqualTo(salaryDetails.getSalary());
            assertThat(add.getGrossSalary()).isNotEqualTo(grossSalary);
        }

        ResponseEntity responseEntity = employeeController.addEmployee(employee);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("----------- Posted Parameter -----------");
        log.info(add.toString());
    }

    @Test
    public void addEmployeeInactiveTest() {
        SalaryDetails salaryDetails = new SalaryDetails();

        EmployeeDetails employee = new EmployeeDetails();
        employee.setId("1");
        employee.setFullName("Apriliani Putri");
        employee.setPhoneNumber("08129834783");
        employee.setAddress("Jakarta Barat");
        employee.setStatus("Inactive");
        employee.setIdJob("1");
        String idJob = employee.getIdJob();

        SalaryRest salaryResp = salaryService.getSalaryById(idJob);

        salaryDetails.setJob(salaryResp.getJob());
        salaryDetails.setSalary(salaryResp.getSalary());
        double grossSalary = salaryDetails.getSalary() - 0.02 * salaryDetails.getSalary();

        log.info("-------- Parameter that we POST --------");
        log.info("id=" + employee.getId());
        log.info("fullName=" + employee.getFullName());
        log.info("phoneNumber=" + employee.getPhoneNumber());
        log.info("address=" + employee.getAddress());
        log.info("status=" + employee.getStatus());
        log.info("idJob=" + employee.getIdJob());


        EmployeeRest add = employeeService.addEmployee(employee);

        assertThat(add.getId()).isEqualTo(employee.getId());
        assertThat(add.getFullName()).isEqualTo(employee.getFullName());
        assertThat(add.getPhoneNumber()).isEqualTo(employee.getPhoneNumber());
        assertThat(add.getAddress()).isEqualTo(employee.getAddress());
        assertThat(add.getStatus()).isEqualTo(employee.getStatus());
        if (add.getStatus().contains("Active")) {
            assertThat(add.getIdJob()).isEqualTo(employee.getIdJob());
            assertThat(add.getJob()).isEqualTo(salaryDetails.getJob());
            assertThat(add.getSalary()).isEqualTo(salaryDetails.getSalary());
            assertThat(add.getGrossSalary()).isEqualTo(grossSalary);
        } else {
            assertThat(add.getIdJob()).isEqualTo("1");
            assertThat(add.getJob()).isEqualTo("Engineer");
            assertThat(add.getSalary()).isEqualTo(0.0);
            assertThat(add.getGrossSalary()).isEqualTo(0.0);
        }
        ResponseEntity responseEntity = employeeController.addEmployee(employee);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("----------- Posted Parameter -----------");
        log.info(add.toString());

    }

    @Test
    public void getEmployeeByIdTest() {

        addEmployeeTest();
        EmployeeRest employee = new EmployeeRest();
        String idemployee = employee.getId();
        log.info(idemployee);

        String id = "1";
        log.info("GET id =" + id);

        EmployeeRest result = employeeService.getEmployeeById(id);
        assertThat(result.getId()).isEqualTo("1");
        log.info(result.getId());
        assertThat(result.getFullName()).isEqualTo("Apriliani Putri");
        assertThat(result.getPhoneNumber()).isEqualTo("08129834783");
        assertThat(result.getAddress()).isEqualTo("Jakarta Barat");
        assertThat(result.getStatus()).isEqualTo("Active");
        assertThat(result.getIdJob()).isEqualTo("1");
        assertThat(result.getJob()).isEqualTo("Engineer");
        assertThat(result.getSalary()).isEqualTo(8000000.00);
        assertThat(result.getGrossSalary()).isEqualTo(7840000.00);

        ResponseEntity responseEntity = employeeController.getEmployeeById(id);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("-------- Parameter that we GET --------");
        log.info(result.toString());
    }

    @Test
    public void getAllEmployeeTest() {

        addEmployeeTest();

        Collection getAddEmployee = employeeService.getAllEmployee();
        assertThat(getAddEmployee.size()).isEqualTo(1);

        ResponseEntity responseEntity = employeeController.getAllEmployee();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("-------- Get All Employee --------");
    }

    @Test
    public void getAllSalaryTest() {

        addSalaryTest();

        Collection getAddSalary = salaryService.getAllSalary();
        assertThat(getAddSalary.size()).isEqualTo(1);

        ResponseEntity responseEntity = salaryController.getAllSalary();
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("-------- Get All Salary --------");
    }

    @Test
    public void updateJobTest() {

        addEmployeeTest();
        addSalaryTest();

        String id = "1";
        log.info("GET id =" + id);

        EmployeeRest add = employeeService.getEmployeeById(id);
        EmployeeRest updateJob = employeeService.updateJob(id, add);

        updateJob.setJob("Manager");
        String job = add.getJob();

        updateJob.setSalary(10000000.00);
        log.info(updateJob.getSalary().toString());
        Double salary = add.getSalary();

        double grossSalary = salary - (0.02 * salary);
        String grosSa = Double.toString(grossSalary);
        log.info(grosSa);
        updateJob.setGrossSalary(grossSalary);

        log.info("-------- Posted Parameter --------");
        log.info(updateJob.toString());

        log.info("-------- Compared Parameter --------");

        assertThat(updateJob.getJob()).isEqualTo(job);
        assertThat(updateJob.getSalary()).isEqualTo(salary);
        assertThat(updateJob.getGrossSalary()).isEqualTo(grossSalary);

        ResponseEntity responseEntity = employeeController.updateJob(id, updateJob);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void totalBudgetTest() {

        addEmployeeTest();

        Double salary = 8000000.00;
        Double totalSalary = (salary * 12) + (salary * 0.5) + salary;
        Double totalBudget = totalSalary;
        int totalEmployee = employeeService.getAllEmployee().size();


        BudgetDetails budgetDetails = new BudgetDetails();
        budgetDetails.setTotalEmployee(Integer.toString(totalEmployee));
        budgetDetails.setTotalBudgetEmployeePerMonth(salary);

        log.info("-------- Parameter that we POST --------");
        log.info("job=" + budgetDetails.getTotalEmployee());
        log.info("salary=" + budgetDetails.getTotalBudgetEmployeePerMonth());

        BudgetRest add = new BudgetRest();
        add.setTotalEmployee(budgetDetails.getTotalEmployee());
        add.setTotalBudgetEmployeePerMonth(budgetDetails.getTotalBudgetEmployeePerMonth());
        add.setTotalBudgetPerYear(totalBudget);

        assertThat(add.getTotalEmployee()).isEqualTo("1");
        assertThat(add.getTotalBudgetEmployeePerMonth()).isEqualTo(8000000.00);
        assertThat(add.getTotalBudgetPerYear()).isEqualTo(108000000.00);

        ResponseEntity responseEntity = budgetController.totalBudgetPerMonth(budgetDetails);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

        log.info("----------- Posted Parameter -----------");
        log.info(add.toString());
    }

    @Test
    public void getEmployeeByIdDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addEmployeeTest();

        String id = "2";
        ResponseEntity add = employeeController.getEmployeeById(id);
        log.info(add.toString());
    }

    @Test
    public void addJobNotExist() {

        addSalaryTest();

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        EmployeeDetails employee = new EmployeeDetails();
        employee.setId("1");
        employee.setFullName("Apriliani Putri");
        employee.setPhoneNumber("08129834783");
        employee.setAddress("Jakarta Barat");
        employee.setStatus("Active");
        employee.setIdJob("2");

        log.info("-------- Add Job Not Exist --------");

        ResponseEntity add = employeeController.addEmployee(employee);
        log.info(add.toString());
    }

    @Test
    public void updateJobNotExist() {

        addSalaryTest();

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        String id = "1";
        log.info("GET id =" + id);

        EmployeeRest add = employeeService.getEmployeeById(id);
        EmployeeRest updateJob = employeeService.updateJob(id, add);

        updateJob.setIdJob("2");

        log.info("-------- Posted Parameter --------");
        log.info(updateJob.toString());

        log.info("-------- Compared Parameter --------");

        ResponseEntity update = employeeController.updateJob(id, updateJob);
        log.info(update.toString());
    }

    @Test
    public void getSalaryByIdDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addSalaryTest();

        String id = "2";
        ResponseEntity add = salaryController.getSalaryById(id);
        log.info(add.toString());
    }

    @Test
    public void updateSalaryDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addSalaryTest();

        SalaryDetails salaryDetails = new SalaryDetails();

        String id = "2";
        ResponseEntity add = salaryController.updateSalary(id, salaryDetails);
        log.info(add.toString());
    }

    @Test
    public void deleteJobDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addSalaryTest();

        String idJob = "2";
        ResponseEntity add = salaryController.deleteJob(idJob);
        log.info(add.toString());
    }

    @Test
    public void deleteEmployeeDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addEmployeeTest();

        String id = "2";
        ResponseEntity add = employeeController.deleteEmployee(id);
        log.info(add.toString());
    }

    @Test
    public void updateStatusDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addEmployeeTest();

        EmployeeRest employeeRest = new EmployeeRest();

        String id = "2";
        ResponseEntity add = employeeController.updateStatus(id, employeeRest);
        log.info(add.toString());
    }

    @Test
    public void updateAddressDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addEmployeeTest();

        EmployeeDetails employeeDetails = new EmployeeDetails();

        String id = "2";
        ResponseEntity add = employeeController.updateAddress(id, employeeDetails);
        log.info(add.toString());
    }

    @Test
    public void updatePhoneNumberDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addEmployeeTest();

        EmployeeDetails employeeDetails = new EmployeeDetails();

        String id = "2";
        ResponseEntity add = employeeController.updatePhoneNumber(id, employeeDetails);
        log.info(add.toString());
    }

    @Test
    public void updateJobDataNotFoundException() {

        exceptionRule.expect(DataNotFoundException.class);
        exceptionRule.equals(FailureResponse.builder()
                .timeStamp(new Date().getTime())
                .message("The parameter you sent is invalid / not match"));

        addEmployeeTest();

        EmployeeRest employeeRest = new EmployeeRest();

        String id = "2";
        ResponseEntity add = employeeController.updateJob(id, employeeRest);
        log.info(add.toString());
    }

    @Test
    public void updateStatusTest() {

        addEmployeeTest();

        log.info("UPDATE STATUS TEST");

        String id = "1";
        EmployeeRest update = employeeService.getEmployeeById(id);
        EmployeeRest updateStatus = employeeService.updateStatus(id, update);

        updateStatus.setStatus("Inactive");
        String status = update.getStatus();

        updateStatus.setSalary(0.0);
        Double salary = update.getSalary();

        double grossSalary = salary - (0.2*salary);
        updateStatus.setGrossSalary(grossSalary);

        assertThat(updateStatus.getStatus()).isEqualTo(status);
        assertThat(updateStatus.getSalary()).isEqualTo(salary);
        assertThat(updateStatus.getGrossSalary()).isEqualTo(grossSalary);

        ResponseEntity responseEntity = employeeController.updateStatus(id, updateStatus);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void updateAddressTest() {

        addEmployeeTest();

        log.info("UPDATE ADDRESS TEST");

        String id = "1";

        EmployeeDetails employeeDetails = new EmployeeDetails();

        employeeDetails.setAddress("Palmerah");

        ResponseEntity responseEntity = employeeController.updateAddress(id, employeeDetails);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void updatePhoneNumberTest() {

        addEmployeeTest();

        log.info("UPDATE PHONE NUMBER TEST");

        String id = "1";

        EmployeeDetails employeeDetails = new EmployeeDetails();

        employeeDetails.setPhoneNumber("082536283623");

        ResponseEntity responseEntity = employeeController.updatePhoneNumber(id, employeeDetails);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void updateSalaryTest() {

        addSalaryTest();

        log.info("UPDATE SALARY TEST");

        String idJob = "1";
        SalaryRest getSalaryRest = salaryService.getSalaryById(idJob);

        Double salary = getSalaryRest.getSalary();

        SalaryDetails salaryDetails = new SalaryDetails();

        salaryDetails.setSalary(8500000.00);

        SalaryRest updateSalary = salaryService.updateSalary(idJob, salaryDetails);
        assertThat(updateSalary.getSalary()).isNotEqualTo(salary);

        ResponseEntity responseEntity = salaryController.updateSalary(idJob, salaryDetails);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());

    }

    @Test
    public void getSalaryByIdTest() {

        addSalaryTest();

        log.info("GET SALARY BY ID TEST");

        SalaryRest salary = new SalaryRest();
        String idJob = salary.getId();
        log.info(idJob);

        String idJ = "1";
        log.info("GET id Job =" + idJ);

        SalaryRest result = salaryService.getSalaryById(idJ);
        assertThat(result.getId()).isEqualTo("1");
        log.info(result.getId());
        assertThat(result.getJob()).isEqualTo("Engineer");
        assertThat(result.getSalary()).isEqualTo(8000000.00);
        assertThat(result.getGrossSalary()).isEqualTo(7840000.00);

        ResponseEntity responseEntity = salaryController.getSalaryById(idJ);
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void deleteEmployeeTest(){

        addEmployeeTest();

        log.info("DELETE EMPLOYEE TEST");

        ResponseEntity responseEntity = employeeController.deleteEmployee("1");
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void deleteJobTest(){

        log.info("DELETE JOB TEST");

        SalaryDetails salary = new SalaryDetails();
        salary.setId("2");
        salary.setJob("Manager");
        salary.setSalary(10000000.00);
        double salary1 = salary.getSalary();


        log.info("-------- Parameter that we POST --------");
        log.info("id=" + salary.getId());
        log.info("job=" + salary.getJob());
        log.info("salary=" + salary.getSalary());


        SalaryRest add = salaryService.addSalary(salary);
        double grossSalary = salary1 - 0.02 * salary1;

        assertThat(add.getId()).isEqualTo(salary.getId());
        assertThat(add.getJob()).isEqualTo(salary.getJob());
        assertThat(add.getSalary()).isEqualTo(salary.getSalary());
        assertThat(add.getGrossSalary()).isEqualTo(grossSalary);

        log.info("----------- Posted Parameter -----------");
        log.info(add.toString());

        ResponseEntity responseEntity = salaryController.deleteJob("2");
        assertThat(responseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void mainTest() {
        ManufactureApplication.main(new String[]{});
    }

}
