package com.kolomin.balansir.Model;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author macbook on 23.02.2022
 */
@Getter
@Setter
@ToString
public class CSVResourceModel {

    @CsvBindByPosition(position = 0)
    private String suffixQR;

    @CsvBindByPosition(position = 1)
    private Long peopleCount;

    @CsvBindByPosition(position = 2)
    private String messageResource;
}
