
package com.example.capstone.security;

import java.util.ArrayList;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String empNo = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        LoginModel loginModel = loginService.getLoginModelByEmpNo(empNo);
        if (loginModel != null) {
            if (passwordEncoder.matches(pwd, loginModel.getEmployeePassword())) {
                return new UsernamePasswordAuthenticationToken(empNo, pwd, getGrantedAuthorities(loginModel.getAuthorities()));
            }
            else {
                System.out.println("Invalid username or password!");
                throw new BadCredentialsException("Invalid username or password!");
            }
        }
        else {
            throw new BadCredentialsException("No user registered with this details!");
        }
    }

    private List<GrantedAuthority> getGrantedAuthorities(Set<Authorities> authorities) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (Authorities authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthorityName()));
        }
        return grantedAuthorities;
    }

    @Override
    public boolean supports(Class<?> authenticationType) {
        //System.out.println(authenticationType.equals(UsernamePasswordAuthenticationToken.class));
        return authenticationType.equals(UsernamePasswordAuthenticationToken.class);
    }

//	@Override
//	public boolean supports(Class<?> authentication) {
//	    return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//	}

}
