package com.baffintech.bandcraft.dto;

import com.baffintech.bandcraft.database.entity.MemberTalent;
import com.baffintech.bandcraft.database.entity.Talent;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class TalentWithMemberStatusDTO {

    // "existing" talent fields
    private Integer id;
    private String name;
    private String description;

    // new field (reason for this DTO)
    private Boolean isMapped;

}


// private String addNewMemberTalentURL;

//    public static boolean isTalentSelected(List<Talent> existingMemberTalents, Talent talent) {
//        for (Talent existingMemberTalent : existingMemberTalents) {
//            if (existingMemberTalent.getId().equals(talent.getId())) {
//                return true;
//            }
//        }
//        return false;
//    }



