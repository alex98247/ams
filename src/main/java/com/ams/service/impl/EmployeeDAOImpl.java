package com.ams.service.impl;

import com.ams.service.dao.EmployeeDAO;
import com.ams.service.po.EmployeePO;
import com.ams.service.rm.EmployeeRowMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Responsible for db operations with Employee.
 *
 * @author Alexey Mironov
 */
@Component
public class EmployeeDAOImpl implements EmployeeDAO {

    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String saveEmployee;
    private final String getEmployeeById;
    private final String deleteEmployeeById;
    private final String getAllEmployees;
    private final String updateEmployee;

    public EmployeeDAOImpl(JdbcTemplate jdbcTemplate,
                           NamedParameterJdbcTemplate namedParameterJdbcTemplate,
                           @Qualifier("security-sql") final Properties sql) {
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        this.saveEmployee = sql.getProperty("saveEmployee");
        this.getEmployeeById = sql.getProperty("getEmployeeById");
        this.deleteEmployeeById = sql.getProperty("deleteEmployeeById");
        this.getAllEmployees = sql.getProperty("getAllEmployees");
        this.updateEmployee = sql.getProperty("updateEmployee");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void save(EmployeePO employee) {
        Map<String, Object> params = new HashMap<>();
        processParams(params, employee);
        namedParameterJdbcTemplate.update(saveEmployee, params);
    }

    @Override
    public void update(EmployeePO employee) {
        Map<String, Object> params = new HashMap<>();
        params.put(EmployeePO.FIELD_ID, employee.getId());
        processParams(params, employee);
        namedParameterJdbcTemplate.update(updateEmployee, params);
    }

    @Override
    public List<EmployeePO> getAll() {
        return jdbcTemplate.query(getAllEmployees, EmployeeRowMapper.DEFAULT_ROW_MAPPER);
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

    private void processParams(Map<String, Object> params, EmployeePO employee) {
        params.put(EmployeePO.FIELD_NAME, employee.getName());
        params.put(EmployeePO.FIELD_SURNAME, employee.getSurname());
        params.put(EmployeePO.FIELD_PATRONYMIC, employee.getPatronymic());
        params.put(EmployeePO.FIELD_PHONE, employee.getPhone());
        params.put(EmployeePO.FIELD_POSITION, employee.getPosition());
    }
}
