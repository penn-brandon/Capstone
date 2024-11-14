package com.psugv.capstone.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests((requests) -> requests.requestMatchers("/css/**", "/images/**", "/javascript/**", "/sql/**", "/views/**").permitAll()
                .requestMatchers("/", "/login", "/signup", "/index", "/error", "/register", "logout").permitAll()
                .requestMatchers("/chat", "/send", "/select", "/loadMessage", "/loadAllChatRoomName","/searchUsers").authenticated()
                .requestMatchers("/listening/**", "/controller/**", "/capstone").permitAll().anyRequest().authenticated());

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);


        http.logout(logout -> logout
                        .logoutSuccessUrl("/index").permitAll());


        http.formLogin(form -> form.loginPage("/login").permitAll().defaultSuccessUrl("/chat", true).failureUrl("/login?failed").permitAll());

        return http.build();
    }

    /*
        @Bean
        public WebSecurityCustomizer webSecurityCustomizer() {
            return (web) -> web. ignoring()
                    // Spring Security should completely ignore URLs starting with / resources/
                    .requestMatchers("/css/**", "/images/**", "/javascript/**", "/sql/**");
        }
    */
    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }
    /*
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {

        return new HaveIBeenPwnedRestApiPasswordChecker();
    }*/
/*
    @Bean
    public UserDetailsService userDetailsService() {

        UserDetails user = User.withUsername("weichuan").password("19951027").authorities("read").build();

        return new InMemoryUserDetailsManager(user);
    }
    */
}

