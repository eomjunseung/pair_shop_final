package fianl.shop.eom.api;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

    @NotEmpty
    private String name;
    @NotEmpty
    private String password;

}
