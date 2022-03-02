package com.kolomin.balansir.Controller;

import com.kolomin.balansir.Service.CSVFilesService;
import com.kolomin.balansir.Service.impl.SecurityService;
import com.kolomin.balansir.Service.impl.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
@Slf4j
public class AccessController {

    @Autowired
    private SecurityService securityService;

    @Autowired
    private CSVFilesService csvFilesService;

    /**
     * Метод возвращает все пароли, который заданы для данного QR-кода
     * */
    @GetMapping("/admin/getpasswords")
    public String getPasswords(@RequestParam("id") Long id, Model model){
            model.addAttribute("qr",securityService.getPasswords(id));
            return "password";
    }

    @GetMapping("/admin/loadCsv")
    public String getLoadCsv() {
        return "csv-load";
    }

    @PostMapping("/admin/loadCsv")
    public String getResourceBuilder(@RequestParam("file") MultipartFile multipartFile,Model model) throws IOException {
        String status= csvFilesService.resourceFileHandler(multipartFile);
        model.addAttribute("status",status);
        return "csv-load";
    }

}
