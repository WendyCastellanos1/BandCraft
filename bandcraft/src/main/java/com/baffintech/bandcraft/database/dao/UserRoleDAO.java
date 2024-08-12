package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {


    List<UserRole> findByUser(User user);

    // List<UserRole>findByUserId(Integer userId);

   @Modifying
    @Query(value = "delete from user_roles u where u.user_id = :userId and u.role_name = :userRole", nativeQuery = true)
    void deleteRoleByUserId(@Param("userId") Integer userId, @Param("userRole") String userRole);
}

