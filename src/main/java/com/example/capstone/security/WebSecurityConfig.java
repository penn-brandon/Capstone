package com.example.capstone.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> authorize
                        .antMatchers("/").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(withDefaults())
                .logout(withDefaults());
        return http.build();
    }
/*
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity

                //.ignoringAntMatchers("/contact")
                //.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())

                //.and()
                .authorizeHttpRequests() // Setting authentic system
//			    .antMatchers("/login").permitAll() // Pages that allow user to access without authentication
                .antMatchers("/index", "/logout").authenticated()
                .antMatchers("/testInsert/*","/intoQuery","/checkInto","/saveCheckSystem","/findCheckByEmpno","/empCheck","/employeeQuery","/employeeOvertime","/saveOvertime","/findEmpOvertime","/findEmpOvertimepending","/findEmpOvertimeauditing","/EmpSignApply","/saveEmpComplementSign","/EmpSignQuery","/empPendingQuery","/empAudittedQuery").authenticated()
                .antMatchers("/mangerCheckQuery","/findAllCheck","/ManagersystemOvertime","/manageQuery","/manageAudit","/ManagerSignAllQuery","/ManagerSignQuery","/ManagerSignAudit").hasAnyRole("HR_MANAGER", "HR","RD_MANAGER","SALES_MANAGER")
                .antMatchers("/").permitAll()
                .antMatchers("/editPersonalInfo", "/personnel", "/personalInformationUpdate", "/personalInformation","/pages", "/personnelAuthorization").authenticated()
                .antMatchers( "/department", "/departmentDetail", "/departmentManagerNoUpdate", "/addNewDepartment", "/createNewDepartment").hasAnyRole("HR_MANAGER", "HR")
                .antMatchers("/bulletinList","/bulletinDetail","/myBulletin","/bulButton","/insertMessage","/bulletinGetMsg","/bulletinInsertEnroll","/bulletinFindEnroll","/bulletin/getImage").authenticated()
                .antMatchers("/myApplyList","/bulletinListUser","/insertMessage","/bulletinGetMsg","/bulletinDelMsg","/bulletinChangeLike","/bulletinFindLike","/findEnrollNumByNo").authenticated()
                .antMatchers("/applyList","/bulletinManage","/bulletinListMag","/bulletinEventInsert","/bulletinAnnoInsert","/insertEventBulletion","/insertAnnoBulletion").hasAnyRole("HR")
                .antMatchers("/bulletinDetailMsg","/bulletinEditEventPage","/bulletinEdiAnnoPage","/bulletin/EditEventop","/bulletin/EditEvent","/bulletin/DelEventPage","/bulletin/DelAnnoPage","/bulletinEditEventPage").hasAnyRole("HR")
                .antMatchers("/modifyLoginModel","/searchLoginModel","/modify","/findAuthorities", "/findNewAuthorities").hasAnyRole("HR_MANAGER", "HR")
                .antMatchers("/addNewPersonnel","/createNewPersonnel").hasAnyRole("HR_MANAGER", "HR")
                .antMatchers("/css/**", "/vendor/**", "/img/**", "/js/**", "/scss/**","/build/**").permitAll()
                .antMatchers("/Leave/LeaveResult","/schedule/TableScheduling").hasAnyRole("HR_MANAGER", "HR","RD_MANAGER","SALES_MANAGER")
                .antMatchers("/G/**","/Leave/**","/schedule/**").authenticated()
                .antMatchers("/updatePassword","/updateNewPassword").authenticated()
                .antMatchers("/calendartasks", "/calendarTaskUpdate", "/calendarTaskDelete", "/shiftsforcalendar").authenticated()
                .anyRequest().hasRole("ADMIN")


                .and()
                .formLogin()
                .loginPage("/login").permitAll() // Rewrite the default login page
                .defaultSuccessUrl("/index") //lead to the default when authentication pass but no specific address
                .failureUrl("/login") // Redirect to login page if authentication
                .and()
//      "/authorization", "/authorizationSeaching", where is the controller
                .logout()

                .logoutSuccessUrl("/login").permitAll()
                .and()
                .httpBasic();
    }
*/
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	LOG.info("AuthenticationManagerBuilder");
//    	auth.userDetailsService(loginUserDetailsService);
//        auth.authenticationProvider(loginAuthenticationProvider);
//    }

//	  @Override
//	  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		  LOG.info("configure");
//		  auth
//		  	.inMemoryAuthentication()
//		  		.withUser("admin").password("12345").authorities("admin")
//		  		.and()
//		  		.withUser("user").password("12345").authorities("read")
//		  		.and()
//		  	.passwordEncoder(NoOpPasswordEncoder.getInstance());
//		  auth.userDetailsService(loginUserDetailsService);
//	       auth.authenticationProvider(loginAuthenticationProvider);
//	  }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
//	@Bean
//	public PasswordEncoder passwordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
//	}

}