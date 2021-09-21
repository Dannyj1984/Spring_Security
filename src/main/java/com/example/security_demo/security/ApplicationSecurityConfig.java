package com.example.security_demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //needed for preauthorization to work
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//Use this if sending form submission from a client to use csrf token then use .and()
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll() // allow all requests to these endpoints
                .antMatchers("/api/**").hasAnyRole(ApplicationUserRole.MEMBER.name(), ApplicationUserRole.ADMIN.name(), ApplicationUserRole.EVENTADMIN.name()) // All roles can access the open api pages once logged in
                .antMatchers("/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.EVENTADMIN.name()) // only admin or eventadmin can access management pages
                //The below are replaced by preauthorisation annotation in the managementcontroller classes.
//                .antMatchers(HttpMethod.DELETE, "/management/api/v1/courses/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/v1/courses/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management//courses/**").hasAuthority(ApplicationUserPermission.COURSE_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/management/api/v1/events/**").hasAuthority(ApplicationUserPermission.EVENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/v1/events/**").hasAuthority(ApplicationUserPermission.EVENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/v1/events/**").hasAuthority(ApplicationUserPermission.EVENT_WRITE.getPermission())
//                .antMatchers(HttpMethod.DELETE, "/management/api/v1/members/**").hasAuthority(ApplicationUserPermission.MEMBER_WRITE.getPermission())
//                .antMatchers(HttpMethod.POST, "/management/api/v1/members/**").hasAuthority(ApplicationUserPermission.MEMBER_WRITE.getPermission())
//                .antMatchers(HttpMethod.PUT, "/management/api/v1/members/**").hasAuthority(ApplicationUserPermission.MEMBER_WRITE.getPermission())
//                .antMatchers("/management/api/v1/members/**").hasAuthority(ApplicationUserPermission.MEMBER_READ.getPermission())
                .anyRequest().authenticated() //client must supply username and password
                .and()
                .httpBasic(); //use basic authentication
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails dannyjebb = User.builder()
                .username("dannyjebb")
                .password(passwordEncoder.encode("P4ssword"))
//                .roles(ApplicationUserRole.MEMBER.name()) // Use enum to set role
                .authorities(ApplicationUserRole.MEMBER.getGrantedAuthorities())
                .build();

        UserDetails katyjebb =  User.builder()
                .username("katyjebb")
                .password(passwordEncoder.encode("P4ssword"))
//                .roles(ApplicationUserRole.ADMIN.name())
                .authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
                .build();

        UserDetails emilyjebb =  User.builder()
                .username("emilyjebb")
                .password(passwordEncoder.encode("P4ssword"))
//                .roles(ApplicationUserRole.EVENTADMIN.name())
                .authorities(ApplicationUserRole.EVENTADMIN.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                dannyjebb,
                katyjebb,
                emilyjebb
        );
    }
}
