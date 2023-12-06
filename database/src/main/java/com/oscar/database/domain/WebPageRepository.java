package com.oscar.database.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WebPageRepository extends JpaRepository<WebPage, Long> {
    WebPage findByPid(String pid);
}
