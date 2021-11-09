package com.ams.security.service;

import com.ams.security.dto.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> getEmployees();

    void saveEmployee(Employee employee);
}
