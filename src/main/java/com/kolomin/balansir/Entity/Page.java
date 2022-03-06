package com.kolomin.balansir.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.StringJoiner;

/**
 * @author macbook on 25.02.2022
 */
@Entity
@Table(name="page_table")
@Getter
@Setter
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

    @Column
    private String qr;

   public  Page() {
    }

    public Page(String page, String message, String qr) {
        this.page = page;
        this.message = message;
        this.qr=qr;
    }

    @Override
    public String toString() {
        return "\n{\n" +
                "\t\"page\": \"" + page + "\",\n" +
                "\t\"msg\": \"" + message + "\"\n" +
                "}";
    }

}
