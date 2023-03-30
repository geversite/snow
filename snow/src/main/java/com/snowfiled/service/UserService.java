package com.snowfiled.service;

import com.snowfiled.mapper.UserMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService implements UserDetailsService {

    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String userID) throws UsernameNotFoundException {
        String password = userMapper.getUserByUserID(Integer.parseInt(userID)).getPassword();
        if(password==null)
            throw new UsernameNotFoundException("用户不存在");
        return User.withUsername(userID).password(password).roles("user").build();
    }

    public int register(com.snowfiled.bean.User user) throws Exception {
        if(userMapper.getUserByUserID(user.getUserID())==null)
            throw new Exception("用户已注册！");
        return userMapper.addUser(user);
    }

    public com.snowfiled.bean.User getLoginInfo(int userID){
        return userMapper.getUserByUserID(userID);
    }

}
