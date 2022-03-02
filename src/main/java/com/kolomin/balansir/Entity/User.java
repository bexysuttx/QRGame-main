package com.kolomin.balansir.Entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "users_table")
@Data
public class User {

    @Id
    @SequenceGenerator(name = "USER_ID_GENERATOR", sequenceName = "USERS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_GENERATOR")
    @Column(unique = true,nullable = false)
    private Long id;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    private String role;

    public User() {
    }

    public User(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String toString() {
        return "\n{\n" +
                "\t\"id\": \"" + id + "\",\n" +
                "\t\"login\": \"" + login + "\"\n" +
                "}";
    }
}
