package com.edw.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * <pre>
 *     com.edw.entity.Blacklist
 * </pre>
 *
 * @author Muhammad Edwin < edwin at redhat dot com >
 * 24 Jan 2023 14:00
 */
@Entity(name = "T_BLACKLIST")
public class Blacklist extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Blacklist() {
    }

    public Blacklist(String name) {
        this.name = name;
    }

    public Blacklist(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
