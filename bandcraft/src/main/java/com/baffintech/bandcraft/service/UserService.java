package com.baffintech.bandcraft.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.form.CreateAccountFormBean;
import java.util.Date;

    @Slf4j
    @Service
    public class UserService {

        @Autowired
        private UserDAO userDAO;

        public User createUser(CreateAccountFormBean form) {

            // there were no errors, so we can create the new user in the database
            User user = new User();
            user.setEmail(form.getEmail());
            user.setPassword(form.getPassword());
            user.setDateCreated(new Date());

            // save the user to the database
            userDAO.save(user);

            return user;
        }
    }
