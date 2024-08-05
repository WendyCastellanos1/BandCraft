package com.baffintech.bandcraft.form;

import com.baffintech.bandcraft.database.entity.BandDetail;
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
public class CreateBandFormBean {

    // JSR-303 specification (what these annotations are called...for validation process..created by Hibernate people originally to be done on entity, but entity was too late of a check, better before controller!

    // this field is ONLY set when user calls the /band/edit URL and gives a valid band id
    // it's our flag: if null, then *create*, if not null, then *edit*
    private Integer id; // the band id will auto-increment, can't get it from the web page when creating

    private Integer eventId;

    // not available to do UNTIL the band is saved, the member-talents are mapped to it, then this field can be updated, so has to be allowed NULl until those steps done
    //@NotEmpty(message = "Lead member is required.")     //TODO make FK to memberId in db and JPA
    private Integer leadMemberId;

    @NotEmpty(message = "Is-single-use is required.")
    private Byte isSingleUse;

    private Byte isActive;

    private Instant dateCreated;

    private Instant dateUpdated;

    private Integer lastUpdatedId;

    private Set<BandDetail> bandDetails = new LinkedHashSet<>();

}
