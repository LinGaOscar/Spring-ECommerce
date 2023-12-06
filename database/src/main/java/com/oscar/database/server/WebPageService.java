package com.oscar.database.server;

import com.oscar.database.domain.WebPage;

import java.util.List;

public interface WebPageService {
    WebPage saveOne(WebPage page);

    WebPage updateOne(WebPage page);

    WebPage findOneByPid(String pid);

    List<WebPage> findAll();
}
