package com.baffintech.bandcraft.form;

import com.baffintech.bandcraft.database.entity.MemberTalent;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@ToString
public class CreateTalentFormBean {

    private Integer id;

    @NotEmpty(message="Talent name is required.")
    private String name;

    @NotEmpty(message="Talent description is required.")
    private String description;


    private String urlSmallPhoto;
    private String urlLargePhoto;

    // TODO admin makes active process, put in MemberService
    private Byte isActive;      // defaults to...TODO

    private Instant dateCreated;

    private Instant dateUpdated;

    private Integer lastUpdatedId;

    private Set<MemberTalent> memberTalents;

}
