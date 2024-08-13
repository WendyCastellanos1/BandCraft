package com.baffintech.bandcraft.service;

import com.baffintech.bandcraft.database.dao.MemberDAO;
import com.baffintech.bandcraft.database.dao.UserDAO;
import com.baffintech.bandcraft.database.dao.UserRoleDAO;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.User;
import com.baffintech.bandcraft.database.entity.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Set;

@Slf4j
@Component
public class UserRoleService {

    @Autowired
    private MemberDAO memberDAO;

    @Autowired
    private UserRoleDAO userRoleDAO;

    @Autowired
    private UserDAO userDAO;

    // note: the public has no record in the database.
    //       anyone is a "USER" once a login is made, but info profile not completed
    //       anyone who completes and saves the info profile is a "MEMBER"
    //       only an "ADMIN" or DBA can make a "USER" or "MEMBER" into an "ADMIN"


    public UserRole modifyRole(User user, String oldRole, String newRole) {

        List<UserRole> userRoles = userRoleDAO.findByUser(user);

        if (userRoles != null) {
            for (UserRole userRole : userRoles) {

                if (userRole.getRoleName() == oldRole) {
                    userRole.setRoleName(newRole);
                }
                userRole = userRoleDAO.save(userRole);

                log.debug("Final userRole here is: " + userRole);

                return userRole;    // can test on other side
            }
        }
        return null;        // we didn't get any userRoles, person maybe shouldn't be here, check for null in calling statement
    }


    public Boolean setNewUserRole(User user, String newRoleName) {

        // we are probably here because a person created a login; is now a user in the users table; needs to be have role "USER" in the user roles table
        // we could also be here because a person is getting an ADDITIONAL role. this creates a new user role association, doesn't modify an existing user role in the db
        Boolean result = false;

        if (user != null) {
            UserRole userRole = new UserRole();
            userRole.setUser(user);
            userRole.setRoleName(newRoleName);
            userRole.setCreateDate(Instant.now());
            userRoleDAO.save(userRole);
            result = true;
        }
        return result;
    }
}



  //      List<UserRole> userRoles = userRoleDAO.findByUser(user);                    // get the user FROM the member to avoid grabbing the ADMIN as current user when ADMIN is updating some MEMBER's stuff
        // if role called 'USER' is found, change that one to 'MEMBER', eliminating 'USER' as the value
//        Boolean result = false;
//        while (result == false) {
//            for (UserRole userRole : userRoles) {
//                if (userRole.getRoleName() == oldRole) {
//
//                    //we have a match, so re-assign
//                    userRole.setRoleName(newRole);
//
//                    // with the target role updated, save this role
//                    userRoleDAO.save(userRole);
//
//                    // still here, so return true, assuming success
//                    result = true;
//                }
//            }
//        }
