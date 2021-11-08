package com.ams.service.dao;

import com.ams.service.po.EmployeePO;

import java.util.List;

/**
 * Responsible for db operations with Employee.
 *
 * @author Alexey Mironov
 */
public interface EmployeeDAO {

    /**
     * Save employee.
     *
     * @param employee - employee to save
     */
    void save(EmployeePO employee);

    /**
     * Get all employees.
     **/
    List<EmployeePO> getAll();

    /**
     * Get employee by id.
     *
     * @param id - identifier of employee
     * @return employee with the id.
     */
    EmployeePO getEmployee(long id);

    /**
     * Delete employee.
     *
     * @param id - identifier of employee
     */
    void delete(long id);
}
