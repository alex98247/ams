package com.ams.rest;

import com.ams.common.ErrorSuccessResponse;
import com.ams.rest.request.EmployeeSaveUpdateRequest;
import com.ams.security.dto.Employee;
import com.ams.security.service.EmployeeService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('ADMIN')")
public class EmployeeRest {

    private final EmployeeService employeeService;

    public EmployeeRest(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = employeeService.getEmployees();
        return ResponseEntity.ok().body(employees);
    }

    @PostMapping
    public ResponseEntity<ErrorSuccessResponse> saveEmployee(@RequestBody EmployeeSaveUpdateRequest request) {
        try {
            Employee employee = new Employee();
            employee.setId(request.getId());
            employee.setName(request.getName());
            employee.setPatronymic(request.getPatronymic());
            employee.setPosition(request.getPosition());
            employee.setSurname(request.getSurname());
            employeeService.saveEmployee(employee);
            return ResponseEntity.ok().body(ErrorSuccessResponse.success());
        } catch (Exception ex) {
            return ResponseEntity.ok().body(ErrorSuccessResponse.builder()
                    .success(false)
                    .message(ex.getMessage()).build());
        }
    }
}
