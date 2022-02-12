package com.kolomin.balansir.Model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class QRPersonalAccessModel {
    private String template;
    private int quantity;
    private int length;
    private int count;
}
