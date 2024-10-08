package com.baffintech.bandcraft.form;

import com.baffintech.bandcraft.database.entity.User;
import lombok.*;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreateMemberFormBean {

    // our flag: if null, then *create*, if not null, then *edit*
    // this field is ONLY set when user calls the /member/edit URL and gives a valid member id
    private Integer id;     // member id will auto-increment, can't get from reg page until saved

    @Length(max=60, message = "First name can have a maximum of 50 characters")
    @NotEmpty(message = "First name is required.")
    private String firstName;

    @Length(max=60, message = "Last name can have a maximum of 50 characters")
    @NotEmpty(message = "Last name is required.")
    private String lastName;

    @Length(max=20, message = "Nickname can have a maximum of 20 characters")
    private String nickname;

    @Length(max=1, message = "Gender must be 1 character")
    @NotEmpty(message = "Gender is required.")
    private String gender;

    @Length(max=60, message = "Gender comments can have a maximum of 60 characters")
    private String genderComment;

    @Length(max=20, message = "Generation can have a maximum of 20 characters")
    @NotEmpty(message = "Generation is required.")
    private String generation;

    @Length(max=15, message = "Cell phone can have a maximum of 15 characters")
    @NotEmpty(message = "Cell phone is required.")
    private String phoneCell;

    @Length(max=15, message = "Alternative phone can have a maximum of 15 characters")
    @NotEmpty(message = "Alternative phone is required.")
    private String phoneAlt;

    // username IS the email. stored in user table

    @Length(max=100, message = "Email must be less than 100 characters")    // this email not encrypted
    private String emailAlt;

    private boolean speaksSpanish;
    private boolean speaksPortuguese;

    private String bio;
    private MultipartFile profilePhoto;
    private String socialMediaUrl;

    private Instant dateCreated;
    private Instant dateUpdated;

    private Integer lastUpdatedId;

   // private Byte registrationStatus;
    // private LocalDate dateReturning;                                        // TODO implement later, allow member to go on hiatus
   // private boolean isActive;                                                // TODO deal with in service class
   // private Byte isBanned;                                                  // TODO  this is set by an ADMIN in service



}
