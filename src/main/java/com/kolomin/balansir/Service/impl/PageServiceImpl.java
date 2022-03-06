package com.kolomin.balansir.Service.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.kolomin.balansir.Entity.Page;
import com.kolomin.balansir.Repository.PageRepository;
import com.kolomin.balansir.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author macbook on 27.02.2022
 */
@Service
public class PageServiceImpl implements PageService {

    @Autowired
    private PageRepository pageRepository;

    @Override
    public String getMessageByUrl(String path) {
        Page page = pageRepository.findByPage(path);
        if (page == null || page.getMessage()==null) {
            return "Сообщения нет!";
        } else {
            return page.getMessage();
        }
    }

    @Override
    public List<Page> getMessages(String suffix) {
        return pageRepository.findByQr(suffix);
    }

    @Override
    public String editMessage(HttpEntity<String> rq) {
        JsonElement request = new JsonParser().parse(rq.getBody());
        String pageUrl = request.getAsJsonObject().get("page_url").toString().replaceAll("\"","");
        try {
            Page page = pageRepository.findByPage(pageUrl);
            page.setMessage(request.getAsJsonObject().get("msg").toString().replaceAll("\"", ""));
            pageRepository.save(page);
            return "{\"success\": true}";
        } catch (IllegalArgumentException e) {
            return "{\"success\": false}";
        }
    }


}
