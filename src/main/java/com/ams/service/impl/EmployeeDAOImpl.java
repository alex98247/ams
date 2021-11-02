package com.ams.service.impl;

import com.ams.service.dao.EmployeeDAO;
import com.ams.service.po.EmployeePO;
import com.ams.service.rm.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Responsible for db operations with Employee.
 *
 * @author Alexey Mironov
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;
    private final String saveEmployee;
    private final String getEmployeeById;
    private final String deleteEmployeeById;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate, @Qualifier("security-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        this.saveEmployee = sql.getProperty("saveEmployee");
        this.getEmployeeById = sql.getProperty("getEmployeeById");
        this.deleteEmployeeById = sql.getProperty("deleteEmployeeById");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(EmployeePO employee) {
        Map<String, Object> params = new HashMap<>();
        params.put(EmployeePO.FIELD_NAME, employee.getName());
        params.put(EmployeePO.FIELD_SURNAME, employee.getSurname());
        params.put(EmployeePO.FIELD_PATRONYMIC, employee.getPatronymic());
        params.put(EmployeePO.FIELD_PHONE, employee.getPhone());
        params.put(EmployeePO.FIELD_POSITION, employee.getPosition());
        jdbcTemplate.update(saveEmployee, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public EmployeePO getEmployee(long id) {
        return jdbcTemplate.queryForObject(getEmployeeById, EmployeeRowMapper.DEFAULT_ROW_MAPPER, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void delete(long id) {
        jdbcTemplate.update(deleteEmployeeById, id);
    }
}