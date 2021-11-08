package com.ams.security.service.impl;

import com.ams.security.dto.Employee;
import com.ams.security.service.EmployeeService;
import com.ams.service.dao.EmployeeDAO;
import com.ams.security.converter.EmployeeConverter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDAO employeeDAO;

    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> getEmployees() {
        return EmployeeConverter.convertEmployeePoToDto(employeeDAO.getAll());
    }
}
