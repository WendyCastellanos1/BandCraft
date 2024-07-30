package com.baffintech.bandcraft.security;

import com.baffintech.bandcraft.database.dao.*;
import com.baffintech.bandcraft.database.entity.*;
import com.baffintech.bandcraft.service.UserService;
import lombok.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // this user object is in the imports section
        // fetch the user from the database - username is what the person entered into the username field on the login form

        User user = userDAO.findByEmailIgnoreCase(username);

        // if the user is null then whatever the person entered on the login form does not exist in the dn
        // automatically throw and exception
        if (user == null) {
            throw new UsernameNotFoundException("Username ' " + username + " ' not found in the database.");
        }

        // check the account status
        boolean accountIsEnabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNonExpired = true;
        boolean accountNonLocked = true;

        // Use the user object from the database to get the user roles
        List<UserRole> userRoles = userRoleDAO.findByUserId(user.getId());
        // passing the user roles to create the granted authorities
        Collection<? extends GrantedAuthority> authorities = buildGrantAuthorities(userRoles);

        // this User object is part of Spring Security
        // because both objets are named User, we have to use the full path to the object
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getEmail(),  // this parameter is the username, in our case the user from the database
                user.getPassword(), // this is the users encrypted password from the database
                accountIsEnabled, // is this account enabled, if false, then spring security will deny access
                accountNonExpired,
                credentialsNonExpired,
                accountNonLocked,
                authorities); // this is the list of security roles that the user is *Authorized* to have

        return userDetails;
    }

    private Collection<? extends GrantedAuthority> buildGrantAuthorities(List<UserRole> userRoles) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        for (UserRole role : userRoles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;
    }
}
