package com.bootcamp.ecommercepracticebackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true,proxyTargetClass=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password("{noop}password").roles("USER")
                .and()
                .withUser("admin").password("{noop}password").roles("USER", "ADMIN");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //HTTP Basic authentication
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/user/getAllUsers").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.GET, "/user/getUserById/{id}").hasAnyRole("USER", "ADMIN")
                .antMatchers(HttpMethod.POST, "/user/addUser").hasRole("USER")
//                .antMatchers(HttpMethod.PUT, "/students/updateById/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/students/deleteById/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/students/enroll/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/students/drop/{id}").hasRole("ADMIN")

//                .antMatchers(HttpMethod.GET, "/courses/getAllCourses").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.GET, "/courses/getCourseById/{id}").hasAnyRole("USER", "ADMIN")
//                .antMatchers(HttpMethod.POST, "/courses/addCourse").hasRole("ADMIN")
//                .antMatchers(HttpMethod.PUT, "/courses/updateCourseById/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.DELETE, "/courses/deleteCourseById/{id}").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }
}
