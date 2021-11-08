package com.ams.security.converter;

import com.ams.security.dto.Employee;
import com.ams.service.po.EmployeePO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EmployeeConverter {

    public static List<Employee> convertEmployeePoToDto(List<EmployeePO> source) {

        if (CollectionUtils.isEmpty(source)) {
            return Collections.emptyList();
        }

        List<Employee> target = new ArrayList<>();
        for (var employeePo : source) {
            Employee employee = new Employee();
            employee.setId(employeePo.getId());
            employee.setName(employeePo.getName());
            employee.setPatronymic(employeePo.getPatronymic());
            employee.setPosition(employeePo.getPosition());
            employee.setSurname(employeePo.getSurname());
            target.add(employee);
        }
        return target;
    }
}
