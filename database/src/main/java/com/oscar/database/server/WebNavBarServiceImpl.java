package com.oscar.database.server;

import com.oscar.database.domain.WebNavbar;
import com.oscar.database.domain.WebNavbarRepository;
import com.oscar.database.dto.WebNavbarDTO;
import com.oscar.database.exception.NotFoundException;
import com.oscar.database.exception.ParameterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebNavBarServiceImpl implements WebNavbarService {
    private WebNavbarRepository webNavbarRepository;

    @Autowired
    public void autowired(WebNavbarRepository webNavbarRepository) {
        this.webNavbarRepository = webNavbarRepository;
    }

    @Override
    public WebNavbar saveOne(WebNavbar webNavbar) {
        return webNavbarRepository.save(webNavbar);
    }

    @Override
    public WebNavbar saveOneByDto(WebNavbarDTO webNavbarDTO) {
        WebNavbar temp = webNavbarRepository.findByPid(webNavbarDTO.getPid());
        if (temp != null) {
            throw new ParameterException("pid重複無法新增");
        }
        return webNavbarRepository.save(webNavbarDTO.convertToWebNavbar());
    }

    @Override
    public WebNavbar updateOne(WebNavbar webNavbar) {
        return webNavbarRepository.save(webNavbar);
    }

    @Override
    public WebNavbar updateOneByDto(WebNavbarDTO webNavbarDTO) {
        WebNavbar temp = webNavbarRepository.findByPid(webNavbarDTO.getPid());
        if (temp == null) {
            throw new NotFoundException("無法更新");
        }
        webNavbarDTO.convertToWebNavbar(temp);
        return webNavbarRepository.save(temp);
    }

    @Override
    public List<WebNavbar> findAll() {
        List<WebNavbar> webNavbarList = webNavbarRepository.findAll();
        if (webNavbarList.isEmpty()) {
            throw new NotFoundException("導覽列未找到");
        }
        return webNavbarList;
    }

    @Override
    public WebNavbar findOneByPid(String pid) {
        WebNavbar temp = webNavbarRepository.findByPid(pid);
        if (temp == null) {
            throw new NotFoundException("導覽列未找到");
        }
        return temp;
    }
}
