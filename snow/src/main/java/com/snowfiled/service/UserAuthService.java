package com.snowfiled.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserAuthService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {


        // TODO: 2023/3/29 security service
        return User
                .withUsername(s)
                .password("password")   //直接从数据库取的密码
                .roles("user")   //用户角色
                .build();
    }
}
