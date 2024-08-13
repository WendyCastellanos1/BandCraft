package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.HashSet;
import java.util.List;

public interface TalentDAO extends JpaRepository<Talent, Long> {

    Talent findById(Integer id);

    Talent findByName(String name);


    HashSet<Talent> findByNameIgnoreCase(String name);

}