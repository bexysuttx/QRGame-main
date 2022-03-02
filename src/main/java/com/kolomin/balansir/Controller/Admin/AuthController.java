package com.kolomin.balansir.Controller.Admin;

import com.kolomin.balansir.Service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthController {
    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public String getBCryptPassword(HttpEntity<String> rq, HttpServletResponse response){
        return userService.login(rq,response);
    }
}