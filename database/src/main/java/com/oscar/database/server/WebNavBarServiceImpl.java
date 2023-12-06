package com.oscar.database.server;

import com.oscar.database.domain.WebNavbar;
import com.oscar.database.domain.WebNavbarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebNavBarServiceImpl implements WebNavbarService {
    private WebNavbarRepository navbarRepository;

    @Autowired
    public void autowired(WebNavbarRepository navbarRepository) {
        this.navbarRepository = navbarRepository;
    }

    @Override
    public WebNavbar saveOne(WebNavbar navbar) {
        return navbarRepository.save(navbar);
    }

    @Override
    public WebNavbar updateOne(WebNavbar navbar) {
        return navbarRepository.save(navbar);
    }

    @Override
    public WebNavbar findOneByPid(String pid) {
        return navbarRepository.findByPid(pid);
    }

    @Override
    public List<WebNavbar> findAll() {
        return navbarRepository.findAll();
    }
}
