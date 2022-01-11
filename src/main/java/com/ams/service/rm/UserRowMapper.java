package com.ams.service.rm;

import com.ams.dao.EmployeeDAO;
import com.ams.dao.RoleDAO;
import com.ams.service.po.EmployeePO;
import com.ams.service.po.RolePO;
import com.ams.service.po.UserPO;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class UserRowMapper implements RowMapper<UserPO> {

    private final RoleDAO roleDAO;
    private final EmployeeDAO employeeDAO;

    public UserRowMapper(RoleDAO roleDAO, EmployeeDAO employeeDAO) {
        this.roleDAO = roleDAO;
        this.employeeDAO = employeeDAO;
    }

    @Override
    public UserPO mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserPO po = new UserPO();
        int id = rs.getInt(UserPO.FIELD_ID);
        po.setId(id);
        po.setUsername(rs.getString(UserPO.FIELD_USERNAME));
        po.setPassword(rs.getString(UserPO.FIELD_PASSWORD));
        List<RolePO> userRoles = roleDAO.getUserRoles(id);
        po.setRoles(userRoles);
        long employeeId = rs.getLong(UserPO.FIELD_EMPLOYEE_ID);
        if (employeeId != 0) {
            EmployeePO employee = employeeDAO.getEmployee(employeeId);
            po.setEmployee(employee);
        }

        return po;

    }
}
