package com.javachen.examples.springmvc.petclinic.web.security;

import com.javachen.examples.springmvc.petclinic.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-04 11:27.
 */
public class PetclinicUserDetails implements UserDetails {

    private User user;
    private List<? extends GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

    public PetclinicUserDetails( User user, List<? extends GrantedAuthority> authorities) {
        this.user = user;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    public User getUser() {
        return user;
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