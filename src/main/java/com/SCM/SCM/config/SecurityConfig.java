package com.SCM.SCM.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.SCM.SCM.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
    // user create and login using java code with help memory service
    @Autowired
    private SecurityCustomUserDetailService userDetailService;
    // @Bean
    // public UserDetailsService userDetailsService(){

    //     UserDetails user =  User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN" , "USER").build();
    //     UserDetails user1 = User.withUsername("user123").password("user123").build();
    //     var InMemoryUserDetailsManager = new InMemoryUserDetailsManager(user , user1);
    //     return  InMemoryUserDetailsManager;
    // }
 

    //Configuration of aouthentication provider 
    @Bean
    public DaoAuthenticationProvider authenticationprovider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // user detatil service object
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // password object encoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return daoAuthenticationProvider;


    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        // configuration
        // urls configuration for public and admin
        httpSecurity.authorizeHttpRequests(authorize -> {

            // authorize.requestMatchers("/home","/register","/services").permitAll();
            authorize.requestMatchers("/user/**").authenticated();
            authorize.anyRequest().permitAll();
        });
        // form default login
        // we can change it later
        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
