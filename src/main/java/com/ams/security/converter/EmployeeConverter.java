package com.ams.security.converter;

import com.ams.security.dto.Employee;
import com.ams.service.po.EmployeePO;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public static EmployeePO convertEmployeeDtoToPo(Employee source) {

        if (Objects.isNull(source)) {
            return null;
        }

        EmployeePO target = new EmployeePO();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setPhone(source.getPhone());
        target.setPatronymic(source.getPatronymic());
        target.setPosition(source.getPosition());
        target.setSurname(source.getSurname());
        return target;
    }
}
