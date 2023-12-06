package com.oscar.database.server;

import com.oscar.database.domain.WebNavbar;

import java.util.List;

public interface WebNavbarService {
    WebNavbar saveOne(WebNavbar navbar);

    WebNavbar updateOne(WebNavbar navbar);

    WebNavbar findOneByPid(String pid);

    List<WebNavbar> findAll();
}
