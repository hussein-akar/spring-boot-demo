package com.example.demo.config.security;

import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.CACHE;
import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.COOKIES;
import static org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter.Directive.STORAGE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

@Configuration
public class SecurityConfig {

    @Value("${application.dev-mode:true}")
    private Boolean isDevMode;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        this.enableUnsecuredAccessToH2Console(http);

        http
            .formLogin()
            .defaultSuccessUrl("/swagger-ui/index.html")
            .and()
            .logout()
            .logoutUrl("/logout")
            .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(CACHE, COOKIES, STORAGE)))
            .and()
            .authorizeHttpRequests()
            .requestMatchers("/h2/**").permitAll()
            .anyRequest().authenticated();

        return http.build();
    }

    private void enableUnsecuredAccessToH2Console(HttpSecurity http) throws Exception {
        if (isDevMode) {
            http.csrf().disable()
                .headers().frameOptions().disable();
        }
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin = User.withUsername("admin").password(passwordEncoder().encode("1234")).roles("ADMIN").build();
        UserDetails user = User.withUsername("user").password(passwordEncoder().encode("1234")).roles("USER").build();

        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
