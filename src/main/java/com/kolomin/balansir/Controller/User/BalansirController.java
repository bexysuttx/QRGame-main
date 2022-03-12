package com.kolomin.balansir.Controller.User;

import com.kolomin.balansir.Form.LoginForm;
import com.kolomin.balansir.Service.PageService;
import com.kolomin.balansir.Service.impl.AdminService;
import com.kolomin.balansir.Service.impl.QRService;
import com.kolomin.balansir.Service.impl.ResourceService;
import com.kolomin.balansir.Util.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.kolomin.balansir.Configuration.ConfigHandler.*;
import static com.kolomin.balansir.Service.impl.AdminService.*;

@Controller
@Slf4j
public class BalansirController {


    private QRService qrService;
    private ResourceService resourceService;
    private PageService pageService;

    @Autowired
    public BalansirController(QRService qrService, ResourceService resourceService, AdminService adminService,PageService pageService) {
        this.qrService = qrService;
        this.resourceService = resourceService;
        this.pageService=pageService;
    }

    @RequestMapping("favicon.ico")
    @ResponseBody
   public void favicon() {}

    @GetMapping("/")
    public String getIndexPage() {
        return "main";
    }

    @GetMapping({"/adm","/Adm"})
    public String getAdminPage() {
        return "redirect://"+urlFront+"admin";
    }

    @GetMapping("/{path}")
    public String getIndex(@PathVariable String path, Model model, HttpSession session) {
        String suffix = DataUtil.normalizeName(path);
        if (!qrService.getSecurity(suffix) || session.getAttribute(suffix) != null) { ////
            return goPage(suffix, model);
        } else {///
            model.addAttribute("qr", suffix);
            return "access";////
        }
    }

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

    @GetMapping("/b/{path}")
    public String getPage(@PathVariable String path, Model model, HttpServletRequest request) {

        model.addAttribute("message",pageService.getMessageByUrl(request.getRequestURI()));
        return "custom-resource";
    }

    private String goPage(String path, Model model) {
        System.out.println("Отсканировали");
        model.addAttribute("qr", path);
        model.addAttribute("url", thisHostPort + "go/");
        return "index";
    }

//    На хэштаблицах
    @ResponseBody
    @GetMapping("/go/{path}")
    public String redirect (@PathVariable String path,HttpSession session){
       if (session.getAttribute(path) !=null || !qrService.getSecurity(path)  ) {
            try {
                try {
                    String resource = getResource(path);
                    if (resource != null) {
                        Long Came_people_count = resource_came_people_count.get(resource)+1;
                        resource_came_people_count.put(resource, Came_people_count);
                        if (!resource_infinity.containsKey(resource)) {
                            if (Came_people_count == resource_people_count.get(resource)) {
                                resource_deleted.put(resource, true);
                                //ЗАТюнить, вызывается get 2 раза
                                if (qr_team.get(path) || resource_team.containsKey(resource)) {
                                    qr_resources.get(path).pollLast();
                                } else {
                                    qr_resources.get(path).pollFirst();
                                }
                            }
                        }
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
                resourceUrl=qr_resources.get(path).pollFirst();
                qr_resources.get(path).addLast(resourceUrl);
            }
            else {
                resourceUrl=qr_resources.get(path).peekFirst();
                if (resource_team.containsKey(resourceUrl)) {
                    qr_resources.get(path).addLast(resourceUrl);
                    qr_resources.get(path).pollFirst();
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
