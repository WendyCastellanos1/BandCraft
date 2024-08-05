package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateAccountFormBean;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

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

        return user;
    }
}
