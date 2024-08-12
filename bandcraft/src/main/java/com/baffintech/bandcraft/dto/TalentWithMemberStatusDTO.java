package com.baffintech.bandcraft.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

