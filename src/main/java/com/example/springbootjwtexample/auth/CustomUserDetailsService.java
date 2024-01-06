package com.example.springbootjwtexample.auth;

import com.example.springbootjwtexample.dto.UserCredential;
import com.example.springbootjwtexample.model.User;
import com.example.springbootjwtexample.service.UserService;
import com.example.springbootjwtexample.util.auth.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;
    private final HttpServletRequest request;
    private final JwtUtil jwtUtil;

    public CustomUserDetailsService(UserService userService, HttpServletRequest request, JwtUtil jwtUtil) {
        this.userService = userService;
        this.request = request;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String authorization = request.getHeader("Authorization");
        if (authorization != null) {
            String token = jwtUtil.resolveToken(request);
            jwtUtil.tokenControl(token);
        }
        User user = userService.getUserByUsername(username);
        return new CustomUserDetails(new UserCredential(user.getId(), user.getUsername(), user.getPassword(), user.getRoles()));
    }
}
