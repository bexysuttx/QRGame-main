package com.kolomin.balansir.Service.impl;

import com.kolomin.balansir.Entity.Page;
import com.kolomin.balansir.Repository.PageRepository;
import com.kolomin.balansir.Service.PageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
