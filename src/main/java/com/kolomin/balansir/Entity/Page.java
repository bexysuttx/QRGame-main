package com.kolomin.balansir.Entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author macbook on 25.02.2022
 */
@Entity
@Table(name="page_table")
@Data
public class Page {

    @Id
    @SequenceGenerator(name = "PAGE_GENERATOR", sequenceName = "PAGE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PAGE_GENERATOR")
    @Column(unique = true,nullable = false)
    private Long id;

    @Column(unique = true,nullable = false)
    private String page;

    @Column
    private String message;

   public  Page() {
    }

    public Page(String page, String message) {
        this.page = page;
        this.message = message;
    }
}
