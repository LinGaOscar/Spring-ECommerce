package com.oscar.database.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebNavbarRepository extends JpaRepository<WebNavbar, Long> {
    WebNavbar findByPid(String pid);
}
