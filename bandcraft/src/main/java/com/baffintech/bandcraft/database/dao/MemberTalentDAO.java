package com.baffintech.bandcraft.database.dao;

import com.baffintech.bandcraft.database.entity.*;
import org.springframework.data.jpa.repository.*;

import java.util.List;

public interface MemberTalentDAO extends JpaRepository<MemberTalent, Long> {

    MemberTalent findById(Integer id);                  // gets a single member-talent mapping by its pk id

    //List<MemberTalent> findAllMemberTalents();          // get ALL member-talent mapping in the table (all members and talents represented) (not checking if *anything* isActive)

    List<MemberTalent> findByMemberId(Integer memberId);      // get member-talent mappings for a specific *member* (not checking if member isActive)
    // List<MemberTalent> findAllMemberTalentsByMemberIdAndIsActive(Integer memberId, Byte isActive);  // same but only ACTIVE member-talent mappings

    List<MemberTalent> findByTalentId(Integer talentId);      // get member-talent mappings for a specific *talent* (not checking if member isActive)
   // List<MemberTalent> findAllMemberTalentsByTalentIdAndIsActive(Integer talentId, Byte isActive);  // same but only ACTIVE member-talent mappings

    // List<MemberTalent> findByCanImprovAndIsActive(Byte canImprov, Byte isActive);
//    List<MemberTalent> findByReadsChordChartsAndIsActive(Byte readsChordCharts, Byte isActive);
//    List<MemberTalent> findBySightReadsThisTalentAndIsActive(Byte sightReadsThisTalent, Byte isActive);
//    List<MemberTalent> findByKey1PreferredAndIsActive();


}