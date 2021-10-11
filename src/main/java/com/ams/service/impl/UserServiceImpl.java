package com.ams.service.impl;

import com.ams.model.User;
import com.ams.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = new User();
        user.setUsername("admin");
        user.setPassword("$2a$10$XVzOAEYH.JS6ZatmNKUVzuGog.D00g1W73Aqiif4rf5AnSNRVCDC2");
        return user;
    }
}
