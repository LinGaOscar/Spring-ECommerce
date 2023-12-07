package com.oscar.database.server;

import com.oscar.database.domain.*;
import com.oscar.database.dto.WebPageDTO;
import com.oscar.database.exception.NotFoundException;
import com.oscar.database.exception.ParameterException;
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
    public WebPage saveOneByDto(WebPageDTO webPageDTO) {
        WebPage temp = webPageRepository.findByPid(webPageDTO.getPid());
        if (temp != null) {
            throw new ParameterException("pid重複無法新增");
        }
        return webPageRepository.save(webPageDTO.convertToWebPage());
    }

    @Override
    public WebPage updateOne(WebPage page) {
        return webPageRepository.save(page);
    }

    @Override
    public WebPage updateOneByDto(WebPageDTO webPageDTO) {
        WebPage temp = webPageRepository.findByPid(webPageDTO.getPid());
        if (temp == null) {
            throw new NotFoundException("無法更新");
        }
        webPageDTO.convertToWebPage(temp);
        return webPageRepository.save(temp);
    }

    @Override
    public List<WebPage> findAll() {
        List<WebPage> webPageList = webPageRepository.findAll();
        if (webPageList.isEmpty()){
            throw new NotFoundException("頁面未找到");
        }
        return webPageList;
    }

    @Override
    public WebPage findOneByPid(String pid) {
        WebPage temp = webPageRepository.findByPid(pid);
        if (temp == null) {
            throw new NotFoundException("頁面未找到");
        }
        return temp;
    }
}
