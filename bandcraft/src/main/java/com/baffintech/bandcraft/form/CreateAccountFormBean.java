package com.baffintech.bandcraft.form;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CreateAccountFormBean {

    private Integer id;

    @NotEmpty
    private String email;

    @NotEmpty
    private String password;

}