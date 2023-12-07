package com.oscar.database.server;

import com.oscar.database.domain.WebPage;
import com.oscar.database.dto.WebPageDTO;

import java.util.List;

public interface WebPageService {
    WebPage saveOne(WebPage webPage);

    WebPage saveOneByDto(WebPageDTO webPageDTO);

    WebPage updateOne(WebPage webPage);

    WebPage updateOneByDto(WebPageDTO webPageDTO);

    List<WebPage> findAll();

    WebPage findOneByPid(String pid);
}
