package com.snowfiled.config;

import com.snowfiled.service.UserAuthService;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Resource
    UserAuthService service;

    @Component
    public static class CustomizeAuthenticationEntryPoint implements org.springframework.security.web.AuthenticationEntryPoint {
        @Override
        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new com.snowfiled.responsedata.ResponseData.Builder<String>().status(401).message("未登录").build().toString());
            log.info("未登录的访问");
        }
    }

    @Component
    public static class CustomizeAuthenticationSuccessHandler implements org.springframework.security.web.authentication.AuthenticationSuccessHandler {
        @Override
        public void onAuthenticationSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws java.io.IOException, javax.servlet.ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new com.snowfiled.responsedata.ResponseData.Builder<String>().status(200).message("登录成功").build().toString());
            log.info(authentication.getName()+"登录成功");
        }
    }

    @Component

    public class CustomizeAuthenticationFailureHandler implements org.springframework.security.web.authentication.AuthenticationFailureHandler {
        @Override
        public void onAuthenticationFailure(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.AuthenticationException exception) throws java.io.IOException, javax.servlet.ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new com.snowfiled.responsedata.ResponseData.Builder<String>().status(401).message(exception.getMessage()).build().toString());
            log.info("登录失败");
        }
    }

    @Component
    public class CustomizeLogoutSuccessHandler implements org.springframework.security.web.authentication.logout.LogoutSuccessHandler {
        @Override
        public void onLogoutSuccess(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws java.io.IOException, javax.servlet.ServletException {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(new com.snowfiled.responsedata.ResponseData.Builder<String>().status(200).message("登出成功").build().toString());
            log.info(authentication.getName()+"登出成功");
        }
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {



        // TODO: 2023/4/1 这里要不要对security 框架进行进一步完善
        http
                .authorizeRequests()
                .antMatchers("/static/**","/api/**").permitAll() //here add the api needn't login can be access.
                .anyRequest().hasAnyRole("user","admin")
                .and()
                .formLogin()
                .permitAll()
                .successHandler(new CustomizeAuthenticationSuccessHandler())
                .failureHandler(new CustomizeAuthenticationFailureHandler())
                //.and()
               // .formLogin()
                //.loginPage("/login")
                //.loginProcessingUrl("/doLogin")
                //.permitAll()
                //.permitAll()
                //.and()
                //.rememberMe()
               // .tokenRepository(new JdbcTokenRepositoryImpl(){{setDataSource(dataSource);}}) //this is dataSource，service 那边还没有写好具体什么样认证
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .logoutSuccessHandler(new CustomizeLogoutSuccessHandler())
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new CustomizeAuthenticationEntryPoint())
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
