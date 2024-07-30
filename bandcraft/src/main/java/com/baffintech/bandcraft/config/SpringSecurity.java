package com.baffintech.bandcraft.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;        // noted very early in component scan
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurity {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // boiler plate code to protect against a common hack
        http.csrf(csrf -> csrf.disable());

        // this section says allow all pages EXCEPT the ones that are in the AntPathRequestMatcher
        // anything in AntPathRequestMatcher will require the user to be authenticated
        http.authorizeRequests()
                .requestMatchers(
                        new AntPathRequestMatcher("/admin/**"),
                        new AntPathRequestMatcher("/user/**")).authenticated()
                .anyRequest().permitAll();

        // the loginPage parameter is the actual URL of the login page
        // the loginProcessingUrl is the URL that the form will submit to
        http.formLogin(formLogin -> formLogin
                .loginPage("/account/login")    // this indicates the controller method!
                // this URL is part of spring security and we do not need to implement it in our controller
                // we just need to make user submit to this URL
                .loginProcessingUrl("/account/loginProcessingURL"));  // magic Spring: does not exist in MY controller methods


        // this is the URL that will log a user out
        http.logout(formLogout -> formLogout
                .invalidateHttpSession(true)        // this is another URL that is included with Spring security - we do not have a controller method for this
                .logoutUrl("/account/logout")       //  logout page URL
                .logoutSuccessUrl("/"));            //  after Spring logs the user out then it will go to this URL

        return http.build();        // makes security filter chain
    }

    @Bean(name="passwordEncoder")                   // makes passwordEncoder(should name this way) available in the context; in the dependency injection engine, so I can get a reference to this whenever I need it
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
