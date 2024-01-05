package com.example.springbootjwtexample.auth;

import com.example.springbootjwtexample.dto.UserCredential;
import com.example.springbootjwtexample.model.Role;
import jakarta.annotation.security.RolesAllowed;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class CustomUserDetails implements UserDetails {

    private String id;
    private String username;
    private String password;
    private List<Role> roles;

    public CustomUserDetails(UserCredential userCredential) {
        this.id = userCredential.getId();
        this.username = userCredential.getUsername();
        this.password = userCredential.getPassword();
        this.roles = userCredential.getRoles();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //TODO: add authorities to a user
        List<SimpleGrantedAuthority> allRoles = new ArrayList<>(roles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRoleName()))
                .toList()
        );

        return allRoles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
