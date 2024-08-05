package com.baffintech.bandcraft.form;

import com.baffintech.bandcraft.database.entity.BandDetail;
import com.baffintech.bandcraft.database.entity.Member;
import com.baffintech.bandcraft.database.entity.Talent;
import lombok.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@ToString

public class CreateMemberTalentFormBean {

    // JSR-303 specification (what these annotations are called...for validation process..created by Hibernate people originally to be done on entity, but entity was too late of a check, better before controller!

    // this field is ONLY set when user calls the /member-talent/edit URL and gives a valid id
    // it's our flag: if null, then *create*, if not null, then *edit*
    private Integer id;

    @NotEmpty
    private Integer memberId;

    @NotEmpty
    private Member member;

    @NotEmpty
    private Integer talentId;

    @NotEmpty
    private Talent talent;

    // not implemented yet: allow member to rank their talents in order of preference after all are chosen
    private Byte preferenceRanking;

    private Byte canImprov;

    private Byte readsChordCharts;

    private Byte sightReadsThisTalent;

    private Character key1Preferred;

    private Character key2Preferred;

    private Character key1Harmony;

    private Character key1Avoid;

    private Byte hasProcessor;

    private String processorComments;

    private Byte needsLoanerInstrument;

    private Instant dateCreated;

    private Instant dateUpdated;

    private Integer lastUpdatedId;

    private Set<BandDetail> bandDetails = new LinkedHashSet<>();    // all mappings of this member-talent in existing band configurations as a band detail

}
