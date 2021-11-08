package com.ams.service.impl;

import com.ams.model.Role;
import com.ams.model.User;
import com.ams.service.UserService;
import org.hibernate.mapping.Collection;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        ArrayList<Role> roles = new ArrayList<>();
        Role role = new Role();
        role.setAuthorities(Collections.singletonList(new SimpleGrantedAuthority("ADMIN")));
        roles.add(role);
        user.setRoles(roles);
        user.setUsername("admin");
        user.setPassword("$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2");
        return user;
    }
}
