package com.ams.service.impl;

import com.ams.dao.UserDAO;
import com.ams.model.Employee;
import com.ams.model.Role;
import com.ams.model.User;
import com.ams.service.UserService;
import com.ams.service.po.EmployeePO;
import com.ams.service.po.RolePO;
import com.ams.service.po.UserPO;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userDAO.get(username);

        /*ArrayList<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        roles.add(role);
        user.setRoles(roles);
        user.setUsername("admin");
        user.setPassword("$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2");*/
        return convert(userPO);
    }

    private User convert(UserPO po) {
        User user = new User();
        user.setUsername(po.getUsername());
        user.setPassword(po.getPassword());
        user.setId(po.getId());
        user.setExpired(false);
        user.setLocked(false);
        user.setPwdExpired(false);
        List<Role> roles = po.getRoles().stream().map(this::convert).collect(Collectors.toList());
        user.setRoles(roles);
        Employee employee = convert(po.getEmployee());
        user.setEmployee(employee);
        return user;
    }

    private Role convert(RolePO po) {
        Role result = new Role();
        result.setId(po.getId());
        result.setName(po.getName());
        List<SimpleGrantedAuthority> authorities = po.getRights().stream()
                .map(x -> new SimpleGrantedAuthority(x.getName()))
                .collect(Collectors.toList());
        result.setAuthorities(authorities);
        return result;
    }

    private Employee convert(EmployeePO po) {
        if (po == null) {
            return null;
        }

        Employee result = new Employee();
        result.setId(po.getId());
        result.setName(po.getName());
        result.setPatronymic(po.getPatronymic());
        result.setSurname(po.getPatronymic());
        result.setPosition(po.getPosition());
        return result;
    }

    @Override
    public List<UserDetails> getUsers() {
        return userDAO.getUsers().stream().map(this::convert).collect(Collectors.toList());
    }
}
