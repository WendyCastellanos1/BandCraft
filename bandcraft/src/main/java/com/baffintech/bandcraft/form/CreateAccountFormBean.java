package com.baffintech.bandcraft.form;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
public class CreateAccountFormBean {

    private Integer id;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

}