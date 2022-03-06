package com.kolomin.balansir.Service;

import com.kolomin.balansir.Entity.Page;
import org.springframework.http.HttpEntity;

import java.util.List;

/**
 * @author macbook on 27.02.2022
 */
public interface PageService {

    String getMessageByUrl(String url);

    List<Page> getMessages(String suffix);

    String editMessage(HttpEntity<String> rq);
}
