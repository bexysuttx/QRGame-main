package com.kolomin.balansir.Form;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;


@Setter
@Getter
public class LoginForm {
    private String qr;
    private String password;
}
