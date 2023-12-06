package com.oscar.database.server;

import com.oscar.database.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebPageServiceImpl implements WebPageService {
    private WebPageRepository webPageRepository;

    @Autowired
    public void autowired(WebPageRepository webPageRepository) {
        this.webPageRepository = webPageRepository;
    }


    @Override
    public WebPage saveOne(WebPage page) {
        return webPageRepository.save(page);
    }

    @Override
    public WebPage updateOne(WebPage page) {
        return webPageRepository.save(page);
    }

    @Override
    public WebPage findOneByPid(String pid) {
        return webPageRepository.findByPid(pid);
    }

    @Override
    public List<WebPage> findAll() {
        return webPageRepository.findAll();
    }
}
