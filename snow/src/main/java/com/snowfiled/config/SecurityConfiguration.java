package com.snowfiled.config;

import com.snowfiled.service.UserAuthService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    UserAuthService service;

    @Resource
    DataSource dataSource;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO: 2023/3/29 this should be modified
        http
                .authorizeRequests()
                .antMatchers("/static/**","/","/").permitAll() //here add the api needn't login can be access.
                .anyRequest().hasAnyRole("user","admin")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/doLogin")
                .permitAll()
                .defaultSuccessUrl("/index",true)
                .permitAll()
                .and()
                .rememberMe()
                .tokenRepository(new JdbcTokenRepositoryImpl(){{setDataSource(dataSource);}}) //this is dataSource，service 那边还没有写好具体什么样认证
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and()
                .csrf().disable();



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(service)
                .passwordEncoder(new BCryptPasswordEncoder());
    }

}
