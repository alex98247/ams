package com.ams.service.balancer;

import com.ams.service.LoadBalancer;
import com.ams.service.UserService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * @author Alexey Mironov
 */
@Service
public class LoadBalancerImpl implements LoadBalancer {

    private final UserService userService;

    public LoadBalancerImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public String getUser(GrantedAuthority authority) {
        List<UserDetails> users = userService.getUsers().stream()
                .filter(x -> x.getAuthorities().contains(authority))
                .collect(Collectors.toList());
        int i = ThreadLocalRandom.current().nextInt(0, users.size());
        return users.get(i).getUsername();
    }
}
