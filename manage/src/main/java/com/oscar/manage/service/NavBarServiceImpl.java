package com.oscar.manage.service;

import com.oscar.manage.domain.Navbar;
import com.oscar.manage.domain.NavbarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NavBarServiceImpl implements NavbarService {
    private NavbarRepository navbarRepository;

    @Autowired
    public void autowired(NavbarRepository navbarRepository) {
        this.navbarRepository = navbarRepository;
    }

    @Override
    public Navbar saveOne(Navbar navbar) {
        return navbarRepository.save(navbar);
    }

    @Override
    public Navbar updateOne(Navbar navbar) {
        return navbarRepository.save(navbar);
    }

    @Override
    public Navbar findById(Long id) {
        return navbarRepository.findById(id).orElse(null);
    }

    @Override
    public List<Navbar> findAll() {
        return navbarRepository.findAll();
    }
}
