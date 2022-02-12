package com.kolomin.balansir.Controller;

import com.kolomin.balansir.Service.SecurityService;
import com.kolomin.balansir.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@Slf4j
public class AccessController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserService userService;

    /**
     * Метод возвращает все пароли, который заданы для данного QR-кода
     * */
    @GetMapping("/admin/getpasswords")
    public String getPasswords(@RequestParam("id") Long id, Model model){
            model.addAttribute("qr",securityService.getPasswords(id));
            return "password";
    }
}
