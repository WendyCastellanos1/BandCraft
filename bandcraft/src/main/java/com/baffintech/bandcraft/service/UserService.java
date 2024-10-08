package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateAccountFormBean;
import com.baffintech.bandcraft.security.UserDetailsServiceImpl;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createUser(CreateAccountFormBean form) {

        // there were no errors so we can create the new user in the database
        User user = new User();

        user.setUsername(form.getUsername());  // note: email IS the username

        // we are getting in a plain text password bc the user entered it into the form
        String encryptedPassword = passwordEncoder.encode(form.getPassword());
        user.setPassword(encryptedPassword);

        user.setDateCreated(new Date().toInstant());

        // save the user to the database
        userDAO.save(user);

        // need to assign a USER Role to the new User bc login info now saved and person can login to create a profile next; can't see Create Profile link unless "USER" in user_roles table
        Boolean result = userRoleService.setNewUserRole(user, "USER");
        log.debug("If true: " + result + "User: " + user.toString() + " has been given the fresh, new role of USER, which means person can now login and see a Create Profile link to become a member.");

        return user;
    }
}