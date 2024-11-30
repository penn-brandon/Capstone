package com.psugv.capstone.security;

import com.psugv.capstone.exception.NoQueryResultException;
import com.psugv.capstone.login.model.UserAuthorityModel;
import com.psugv.capstone.login.model.UserModel;
import com.psugv.capstone.util.ChatServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SecurityTest {

    @Autowired
    private ChatServer chatServer;

    @BeforeEach
    void setUp() throws Exception {
    }

    @AfterEach
    void tearDown() throws Exception {
    }

    UserModel um1 = new UserModel(3, "freddy", "1234", "Freddy The Creator", null, "male", true, new HashSet<UserAuthorityModel>());

    UserAuthorityModel uam = new UserAuthorityModel(2,"Normal", um1);

    UserDetails ud =  new SecurityUserLogin(um1);

    @Test
    public void analyzeGetAuthorities() {

        Set<UserAuthorityModel> uamSet = new HashSet<>();

        uamSet.add(uam);

        um1.setAuthorities(uamSet);

        Set<GrantedAuthority> another = (Set<GrantedAuthority>) ud.getAuthorities();

        assertEquals(another.size(), uamSet.size());
    }

    @Autowired
    private UserDetailsService loginUserDetailsService;

    @Test
    public void analyzeLoadUserByUsername(){

        assertEquals(ud.getUsername(), loginUserDetailsService.loadUserByUsername("freddy").getUsername());

    }

    @Test
    public void analyzeOtherUserDetails() {

        assertEquals("1234", ud.getPassword());
        assertEquals("freddy", ud.getUsername());
        assertEquals(true, ud.isAccountNonExpired());
        assertEquals(true, ud.isAccountNonLocked());
        assertEquals(true, ud.isCredentialsNonExpired());
        assertEquals(true, ud.isEnabled());
    }

    @Autowired
    private LoginAuthenticationProvider authenticationProvider;

    @Test
    public void analyzeAuthenticate(){

        Authentication authentication = new UsernamePasswordAuthenticationToken("freddy", "1234");
        Authentication result = authenticationProvider.authenticate(authentication);

        assertNotNull(result);
        assertEquals("freddy", result.getName());
        assertEquals("1234", result.getCredentials());

        ChatServer.loginCheckin(3);

        ChatServer.removeFromOnlineUserPool(3);
    }
}
