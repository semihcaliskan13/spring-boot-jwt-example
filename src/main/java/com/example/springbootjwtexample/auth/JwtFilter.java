package com.example.springbootjwtexample.auth;

import com.example.springbootjwtexample.security.SecurityConfig;
import com.example.springbootjwtexample.util.auth.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();
        final String header = request.getHeader("Authorization");
        final String jwtToken;
        final String username;

        if (header == null || !header.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        jwtToken = header.substring(7);
        username = jwtUtil.findUsername(jwtToken);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            List<? extends GrantedAuthority> roles = (List<? extends GrantedAuthority>) userDetails.getAuthorities();
            if (jwtUtil.tokenControl(jwtToken)) {
                var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, roles);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

//    @Override
//    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
//
//        String path = request.getRequestURI();
//        String[] allowedPaths = SecurityConfig.PUBLIC_REQUEST_MATCHERS;
//        ArrayList<String> allowedPathsMapping = new ArrayList<>();
//
//        Arrays.stream(allowedPaths).map(a -> allowedPathsMapping.add(a.replace("/**", ""))).toList();
//        boolean result = allowedPathsMapping.stream().anyMatch(path::startsWith);
//        return result;
//    }
}
