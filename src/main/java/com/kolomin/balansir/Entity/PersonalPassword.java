package com.kolomin.balansir.Entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "personal_password_table")
@Data
public class PersonalPassword {

    @Id
    @SequenceGenerator(name = "PERSONAL_PASSWORD_GENERATOR", sequenceName = "PERSONAL_PASSWORD_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERSONAL_PASSWORD_GENERATOR")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private QR qr;

    @Column
    private String password;

    @Column
    private int quantity;

    @Override
    public String toString() {
        return "PersonalPassword{" +
                "id=" + id +
                ", qr=" + qr.getId() +
                ", password='" + password + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}
