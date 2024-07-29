package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;


public interface MemberDAO extends JpaRepository<User, Long> {



}
