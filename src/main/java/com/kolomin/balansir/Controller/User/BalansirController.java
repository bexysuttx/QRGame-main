package com.kolomin.balansir.Controller.User;

import com.kolomin.balansir.Form.LoginForm;
import com.kolomin.balansir.Service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import static com.kolomin.balansir.Config.ConfigHandler.*;
import static com.kolomin.balansir.Service.AdminService.*;

@Controller
@Slf4j
public class BalansirController {


    private QRService qrService;
    private ResourceService resourceService;

    @Autowired
    public BalansirController(QRService qrService, ResourceService resourceService, AdminService adminService) {
        this.qrService = qrService;
        this.resourceService = resourceService;
    }

    @RequestMapping("favicon.ico")////
    @ResponseBody////
   public void favicon() {}///

    @GetMapping("/")
    public String getIndexPage() {
        return "redirect:"+urlFront;
    }

    //////
    @GetMapping("/{path}")
    public String getIndex(@PathVariable String path, Model model, HttpSession session){
            if (!qrService.getSecurity(path) || session.getAttribute(path) != null) { ////
                return goPage(path, model);
            } else {///
                model.addAttribute("qr", path);
                return "access";////
            }
    }
    ////
////////
    @PostMapping("/sign-in-handler")
    public String signIn(LoginForm form,Model model,HttpSession session) {
       if(qrService.isAccess(form)){
           session.setAttribute(form.getQr(),form);
          return goPage(form.getQr(),model);
       } else {
           model.addAttribute("qr", form.getQr());
           model.addAttribute("failed", true);
           return "access";
       }
    }
    //////
//////
    private String goPage(String path, Model model) {
        System.out.println("Отсканировали");
        model.addAttribute("qr", path);
        model.addAttribute("url", thisHostPort + "go/");
        return "index";
    }
    ///////
//////
//    На хэштаблицах
    @ResponseBody
    @GetMapping("/go/{path}")
    public String redirect (@PathVariable String path,HttpSession session){
       if (session.getAttribute(path) !=null || !qrService.getSecurity(path)  ) {
            try {
                try {
                    String resource = getResource(path);
                    if (resource != null) {
                        Long Came_people_count = resource_came_people_count.get(resource);
                        if (!resource_infinity.get(resource)) {
                            if (Came_people_count + 1 == resource_people_count.get(resource))
                                resource_deleted.put(resource, true);
                        }
                        resource_came_people_count.put(resource, Came_people_count + 1);
                        return resource;
                    } else {
                        qr_default_count.put(path, qr_default_count.get(path) + 1);
                        return qr_defaultResource.get(path);
                    }
                } catch (Exception e) {
                    qr_general_default_count.put(path, qr_general_default_count.get(path) + 1);
                    return defaultResource;
                }
            } catch (Exception e) {
//            log.info("Ненужный мусор");
                return defaultResource;
            }
        }
       return "ACCESS DENIED";
    }

    //////

    private String getResource(String path) {
        String resourceUrl = null;
        try{
            if (qr_team.get(path)){
                Long camePeopleCount = 10000000000L;
                for (String resUrl: qr_resources.get(path)) {
                    if (!resource_deleted.get(resUrl)){
                        if (resource_came_people_count.get(resUrl) < camePeopleCount){
                            resourceUrl = resUrl;
                            camePeopleCount = resource_came_people_count.get(resUrl);
                        }
                    }
                }
            }
            else {
                for (String resUrl: qr_resources.get(path)) {
                    if (!resource_deleted.get(resUrl)){
                        resourceUrl = resUrl;
                        break;
                    }
                }
            }
            return resourceUrl;
        } catch (Exception e) {
            return null;
        }

    }




//    с походом в БД
//    @ResponseBody
//    @GetMapping("/go/{path}")
//    public synchronized String redirect (@PathVariable String path){
////        long m = System.currentTimeMillis();
////        log.info("Вызов урла с этим qr_suffix: " + path);
//        QR qr = qrService.getBySuffix(path);
//        try {
//            try {
//                Resource resource = getResource(qr, path);
//                if (resource != null){
//                    Long Came_people_count = resource.getCame_people_count();
//                    if (!resource.isInfinity()){
//                        if (Came_people_count + 1 == resource.getPeople_count())
//                            resource.setDeleted(true);
//                    }
//                    resource.setCame_people_count(Came_people_count + 1);
//                    resourceService.saveOrUpdate(resource);
//                    return resource.getUrl();
//                } else {
//                    qr.setDefault_resource_people_count(qr.getDefault_resource_people_count() + 1);
//                    qrService.saveOrUpdate(qr);
//                    return qr.getDefault_resource();
//                }
//            } catch (Exception e) {
//                qr.setGeneral_default_resource_people_count(qr.getGeneral_default_resource_people_count() + 1);
//                qrService.saveOrUpdate(qr);
//                return defaultResource;
//            }
//        }catch (Exception e){
////            log.info("Ненужный мусор");
//            return defaultResource;
//        }
//    }
//
//    private Resource getResource(QR qr, String path) {
//        try{
//            if (qr.isTeam()){
//                Resource resource = resourceService.getByQRSuffixNotDeletedAndCamePeopleCountMin(path);
//                return resource;
//            }
//            else {
//                Resource resource = resourceService.getByQRSuffixNotDeleted(path);
////                Long Came_people_count = resource.getCame_people_count();
////                resource.setCame_people_count(Came_people_count + 1);
////                if (resource.isInfinity()){
////                    qr.setTeam(true);
////                    qrService.saveOrUpdate(qr);
////                } else {
////                    if (Came_people_count + 1 == resource.getPeople_count())
////                        resource.setDeleted(true);
////                }
////                resourceService.saveOrUpdate(resource);
////            System.out.println(System.currentTimeMillis() - m);
//
//                return resource;
//            }
//        } catch (Exception e) {
//            return null;
//        }
//
//    }
}
