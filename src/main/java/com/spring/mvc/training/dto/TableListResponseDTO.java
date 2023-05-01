package com.spring.mvc.training.dto;

import com.spring.mvc.training.entity.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@RequiredArgsConstructor
public class TableListResponseDTO {
    private final int tableNo;
    private final String title;
    private final String date;

    public TableListResponseDTO(Table table) {
        this.tableNo = table.getTableNo();
        this.title = table.getTitle();
        this.date = makePrettierDateString(table.getRegDateTime());
    }

    static String makePrettierDateString(LocalDateTime regDateTime) {
        DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return pattern.format(regDateTime);
    }

}
