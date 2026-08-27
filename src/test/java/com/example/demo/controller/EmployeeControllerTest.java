package com.example.demo.controller;

import com.example.demo.dto.EmployeeResponse;
import com.example.demo.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class EmployeeControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    EmployeeService employeeService;

    private EmployeeResponse employeeResponse;

    @BeforeEach
    void setup() {
        employeeResponse = new EmployeeResponse();
        employeeResponse.setId(1);
        employeeResponse.setFirstName("Nguyễn Hữu");
        employeeResponse.setLastName("Thắng");
        employeeResponse.setBirthDate(Date.valueOf("2005-12-28"));
        employeeResponse.setSupervisorId(null);
    }

    @Test
    void createEmployee_shouldReturn201() throws Exception {
        // arrange - mock dependency
        when(employeeService.createEmployee(any())).thenReturn(employeeResponse);
        // act
        mockMvc.perform(post("/api/v1/employees")
                .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "id": 1,
                                    "firstName": "Nguyễn Hữu",
                                    "lastName": "Thắng",
                                    "birthDate": "2005-12-28T00:00:00.000Z",
                                    "supervisorId": null
                                }
                               """))
                // assert
                .andExpect(status().isCreated());
    }

//    @Test
//    void updateEmployee_shouldReturn200() throws Exception {
//        when(employeeService.updateEmployeeById(any(), any())).thenReturn(employeeResponse);
//    }
}
