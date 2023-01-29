package com.github.kardzhaliyski.blogwebapp.security;

import com.github.kardzhaliyski.blogwebapp.mappers.UserMapper;
import com.github.kardzhaliyski.blogwebapp.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.Optional;

public class AppUserDetailsService implements UserDetailsService {

    UserMapper userMapper;

    public AppUserDetailsService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> u = userMapper.getByUsername(username);
        if (u.isEmpty()) {
            throw new UsernameNotFoundException("User not found! For username: " + username);
        }

        User user = u.get();
        return map(user);
    }

    private UserDetails map(User user) {
        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return user.password;
            }

            @Override
            public String getUsername() {
                return user.username;
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
        };
    }
}
