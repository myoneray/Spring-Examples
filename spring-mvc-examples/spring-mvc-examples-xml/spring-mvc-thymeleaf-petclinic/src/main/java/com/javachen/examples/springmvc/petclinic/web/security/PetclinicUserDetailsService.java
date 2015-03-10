package com.javachen.examples.springmvc.petclinic.web.security;

import com.javachen.examples.springmvc.petclinic.model.Permission;
import com.javachen.examples.springmvc.petclinic.model.Role;
import com.javachen.examples.springmvc.petclinic.model.User;
import com.javachen.examples.springmvc.petclinic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:june.chan@foxmail.com">june</a>.
 * @date 2014-12-04 11:35.
 */
@Component
public class PetclinicUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (StringUtils.isEmpty(username)) {
            throw new UsernameNotFoundException("Username was empty");
        }

        List<User> users = userService.findByName(username);

        if (users == null||users.size()==0) {
            throw new UsernameNotFoundException("Username not found");
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        for (Role role : users.get(0).getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            for (Permission permission : role.getPermissions()) {
                grantedAuthorities.add(new SimpleGrantedAuthority(permission.getName()));
            }
        }
        return new PetclinicUserDetails(users.get(0), grantedAuthorities);
    }
}