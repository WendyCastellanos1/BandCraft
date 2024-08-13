package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;
import java.util.Map;

public interface MemberTalentDAO extends JpaRepository<MemberTalent, Long> {

    MemberTalent findById(Integer id);                  // gets a single member-talent mapping by its pk id


    @Query(value = " select * from member_talents where member_id = :memberId and talent_id = :talentId",
            nativeQuery = true)
    MemberTalent findByMemberIdAndTalentId(Integer memberId, Integer talentId);

    List<MemberTalent> findByMember(Member member);

    @Query(value="select * from member_talents where member_id = :memberId", nativeQuery = true)
    List<MemberTalent> findByMemberId(Integer memberId);      // get member-talent mappings for a specific *member* (not checking if member isActive)

    List<MemberTalent> findByTalent(Talent talent);
    List<MemberTalent> findByTalentId(Integer talentId);      // get member-talent mappings for a specific *talent* (not checking if member isActive)

    @Query(value="select t.id, t.name, t.description from talents t inner join member_talents mt on mt.talent_id = t.id where member_id = :memberId", nativeQuery = true)
    List<Map<String,Object>> findTalentsInMemberTalentsByMemberId(Integer memberId);


    //List<MemberTalent> findAllMemberTalents();          // get ALL member-talent mapping in the table (all members and talents represented) (not checking if *anything* isActive)
    //List<MemberTalent> findByMemberOrTalent(String search);
    // List<MemberTalent> findAllMemberTalentsByMemberIdAndIsActive(Integer memberId, Byte isActive);  // same but only ACTIVE member-talent mappings
    // List<MemberTalent> findAllMemberTalentsByTalentIdAndIsActive(Integer talentId, Byte isActive);  // same but only ACTIVE member-talent mappings
    // List<MemberTalent> findByCanImprovAndIsActive(Byte canImprov, Byte isActive);
    // List<MemberTalent> findByReadsChordChartsAndIsActive(Byte readsChordCharts, Byte isActive);
    // List<MemberTalent> findBySightReadsThisTalentAndIsActive(Byte sightReadsThisTalent, Byte isActive);
    // List<MemberTalent> findByKey1PreferredAndIsActive();


}