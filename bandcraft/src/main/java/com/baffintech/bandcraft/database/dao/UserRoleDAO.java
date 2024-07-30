package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface UserRoleDAO extends JpaRepository<UserRole, Long> {

    List<UserRole> findByUserId(Integer userId);



}

