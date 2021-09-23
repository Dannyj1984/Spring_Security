package com.example.security_demo.security;

import com.example.security_demo.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) //needed for preauthorization to work
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     ApplicationUserService applicationUserService1) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService1;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
//                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())//Use this if sending form submission from a client to use csrf token then use .and()
//                .and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(  "/css/*", "/js/*").permitAll() // allow all requests to these endpoints
                .antMatchers("/api/**").hasAnyRole(ApplicationUserRole.MEMBER.name(), ApplicationUserRole.ADMIN.name(), ApplicationUserRole.EVENTADMIN.name(), ApplicationUserRole.HANDICAPADMIN.name()) // All roles can access the open api pages once logged in
                .antMatchers("/management/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.EVENTADMIN.name(), ApplicationUserRole.HANDICAPADMIN.name()) // only admin or eventAdmin handicapAdmin can access management pages
                //The below are replaced by preauthorisation annotation in the managementController classes.
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
                .formLogin() //use form based authentication
                    .loginPage("/login")
                    .permitAll() // set custom login page
                    .defaultSuccessUrl("/courses", true) //send to this page after login
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)) // allow extension of sessionID to (default) 2 weeks rather than expiring within 30 mins of inactivity this code extends to 21 days
                    .key("somethingverysecure")
                .and()
                .logout()
                    .logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")) // only to be used if csrf is disabled
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me") //remove cookies saved for session and remember me function
                    .logoutSuccessUrl("/login"); //send back to login page
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
