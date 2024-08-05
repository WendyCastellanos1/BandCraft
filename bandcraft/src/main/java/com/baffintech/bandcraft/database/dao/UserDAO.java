package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

public interface UserDAO extends JpaRepository<User, Long> {

    // select * from employee where lower(email) = lower(:email)
    User findByUsernameIgnoreCase(String username);    // the username is the primary email address we keep for the user

}
