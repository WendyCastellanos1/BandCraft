package com.baffintech.bandcraft.form;

import com.baffintech.bandcraft.database.entity.Band;
import com.baffintech.bandcraft.database.entity.MemberTalent;
import lombok.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;

@Getter
@Setter
@ToString
public class CreateBandDetailFormBean {

    // JSR-303 specification (what these annotations are called...for validation process..created by Hibernate people orignally to be done on entity, but entity was too late of a check, better before controller!

    // this field is ONLY set when user calls the /band-detail/edit URL and gives a valid bandDetailId
    // it's our flag: if null, then *create*, if not null, then *edit*
    private Integer id;     // this will auto-increment on the insert, so not available until already created, not on create form

    private Band band;

    private MemberTalent memberTalent;

    private Instant dateCreated;

    private Instant dateUpdated;

    private Integer lastUpdatedId;

}
