package com.soulmonk.ndfsm.security;

import com.soulmonk.ndfsm.domain.user.User;
import com.soulmonk.ndfsm.domain.user.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
/**
 * User: soulmonk
 * Date: 01.02.14
 * Time: 11:13
 */
public class UserDetailsAdapter implements UserDetails {
    private static final long serialVersionUID = 1L;

    protected static final Logger logger = LoggerFactory.getLogger(UserDetailsAdapter.class);

    private User user;

    public UserDetailsAdapter(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public Long getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public String getLastName() {
        return user.getLastName();
    }

    public String getFullName() {
        return user.getFullName();
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        for (UserRole role : user.getUserRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getRole().getAuthority()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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
        return user.getEnabled();
    }

    /**
     * @return UserDetailsAdapter or null if not logging
     */
    public static UserDetailsAdapter getLogged() {
        SecurityContext context = SecurityContextHolder.getContext();
        if (context == null) {
            logger.error("security context is null");
            return null;
        }
        Authentication authenticate = context.getAuthentication();
        if (authenticate == null) {
            logger.error("Authentication is null");
            return null;
        }

        Object o = authenticate.getPrincipal();
        if (o instanceof UserDetailsAdapter) {
            return (UserDetailsAdapter) o;
        } else {
            return null;
        }
    }
}
