package com.oscar.database.server;

import com.oscar.database.domain.WebNavbar;
import com.oscar.database.dto.WebNavbarDTO;

import java.util.List;

public interface WebNavbarService {
    WebNavbar saveOne(WebNavbar webNavbar);

    WebNavbar saveOneByDto(WebNavbarDTO webNavbarDTO);

    WebNavbar updateOne(WebNavbar webNavbar);

    WebNavbar updateOneByDto(WebNavbarDTO webNavbarDTO);

    List<WebNavbar> findAll();

    WebNavbar findOneByPid(String pid);
}
