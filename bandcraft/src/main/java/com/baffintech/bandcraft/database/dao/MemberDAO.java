package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;

import java.util.List;


public interface MemberDAO extends JpaRepository<Member, Long> {

    Member findById(Integer id);
    Member findByUser(User user);
    List<Member> findByFirstName(String firstName);
    List<Member> findByLastName(String lastName);
    List<Member> findByNickname(String nickname);

    @Query(value = "select * from members m where m.first_name = :search OR m.last_name = :search OR m.nickname = :search",  nativeQuery = true)
    List<Member> findByFirstNameOrLastNameOrNickname(String search);

//    @Query (value = "select m from Member m where m.isActive = 1")
//    List<Member> findByActive();
//    List<Member> findByIsActive(Byte isActive, Pageable pageable);

    //List<Member> findByIsActiveAndFirstName(Byte isActive, String firstName);

    //List<Member> findByIsActiveAndLastName(Byte isActive, String lastName);

    //List<Member> findByIsActiveAndNickname(Byte isActive, String nickname);

    //List<Member> findByGender(String gender);
    //List<Member> findByIsActiveAndGender(Byte isActive, String gender, Pageable pageable);

    //List<Member> findByGeneration(String generation);
    //List<Member> findByIsActiveAndGeneration(Byte isActive, String generation, Pageable pageable);

    //List<Member> findByIsActiveAndSpeaksPortuguese  (Byte isActive, Byte speaksPortuguese, Pageable pageable);

    //List<Member> findByIsActiveAndSpeaksSpanish(Byte isActive, Byte speaksSpanish, Pageable pageable);

    //List<Member> findByIsBanned(Byte isBanned, Pageable pageable);


}
