package com.restapi.manufacture;

import com.restapi.manufacture.controller.EmployeeController;
import com.restapi.manufacture.service.EmployeeService;
import com.restapi.manufacture.service.SalaryService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
@Slf4j
public class AppException extends AbstractJUnit4SpringContextTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private Controller controller;

    @Test
    public void addEmployeeFailedTest() throws Exception {

        MvcResult result = this.mockMvc.perform(post("/employee/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        " \"id\": 1,\n" +
                        " \"fullName\": \"A\",\n" +
                        " \"phoneNumber\" : \"0\",\n" +
                        " \"address\" : \"J\",\n" +
                        " \"status\" : \"A\",\n" +
                        " \"idJob\": \"1\"\n" +
                        "}"))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

    @Test
    public void getEmployeeByIdTest() throws Exception {

        MvcResult result = this.mockMvc.perform(get("/employee/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

    @Test
    public void delEmployeeByIdTest() throws Exception {

        MvcResult result = this.mockMvc.perform(delete("/employee/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError())
                .andDo(print()).andReturn();

        String content = result.getResponse().getContentAsString();
        log.info(content);
    }

}
