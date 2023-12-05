package com.oscar.manage.service;

import com.oscar.manage.domain.Navbar;

import java.util.List;

public interface NavbarService {
    Navbar saveOne(Navbar navbar);
    Navbar updateOne(Navbar navbar);
    Navbar findById(Long id);
    List<Navbar> findAll();
}
