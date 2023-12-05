package com.oscar.manage.domain;

import lombok.Data;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@Table(name = "navbar")
public class Navbar implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "sequence")
    private Number sequence;

    @Column(name = "url_name")
    private String url;

    @Column(name = "id_delete")
    private Boolean isDelete;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Navbar that = (Navbar) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
