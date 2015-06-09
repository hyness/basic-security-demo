package org.hyness.basic.security.demo;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.context.request.async.WebAsyncManagerIntegrationFilter;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER) 
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String API_USER = "API";
    private static final String ADMIN_USER = "ADMIN";

    @NotNull
    @Value("${management.user.name}")
    private String managementUsername;
    
    @NotNull
    @Value("${management.user.password}")
    private String managementPassword;
    
    @NotNull
    @Value("${management.context-path}")
    private String managementContextPath;

    public ApplicationSecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // @formatter:off
        http.addFilter(new WebAsyncManagerIntegrationFilter())
                .exceptionHandling().and().headers().and().sessionManagement()
                .sessionCreationPolicy(STATELESS).and().securityContext().and()
                .requestCache().and().servletApi().and().authorizeRequests()
                .antMatchers(managementContextPath + "/**").hasRole(ADMIN_USER)
                .antMatchers("/**").hasRole(API_USER).and().httpBasic();
        // @formatter:on
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication().withUser("apiUsername")
                .password("apiPassword").roles(API_USER).and()
                .withUser(managementUsername).password(managementPassword)
                .roles(ADMIN_USER);
    }
}