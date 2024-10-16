package com.psugv.capstone.security;

import com.psugv.capstone.login.model.UserModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/css/**", "/images/**", "/javascript/**", "/sql/**", "/views/**").permitAll()
                        .requestMatchers("/", "/login", "/signup", "/index", "/error").permitAll()
                        .requestMatchers("/chat", "/send", "/select", "/loadMessage", "/loadAllChatRoomName").authenticated()
                        //.requestMatchers("/view/open/error", "/view/open/index", "/view/open/login", "/view/open/signup").permitAll()
                        //.requestMatchers("**/error", "**/index", "**/login", "**/signup").permitAll()
                        //.requestMatchers("/chat").authenticated()
                        .anyRequest().authenticated()
                );


        http.httpBasic(Customizer.withDefaults());

        http.csrf((csrf) -> csrf.disable());

/*
        http.logout(logout -> logout
                        .logoutSuccessUrl("/index").permitAll());
*/

        http.formLogin(form -> form
                        .loginPage("/login").permitAll()
                        .defaultSuccessUrl("/chat", true)
                        .failureUrl("/login?failed").permitAll());

//        http.formLogin(Customizer.withDefaults());

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

