package com.spring.mvc.training.dto;

import com.spring.mvc.training.entity.Table;

public class TableDetailResponseDTO {

    private final int tableNo;
    private final String title;
    private final String content;
    private final String date;
    private final int viewCount;

    public TableDetailResponseDTO(Table table) {
        this.tableNo = table.getTableNo();
        this.title = table.getTitle();
        this.content = table.getContent();
        this.date = TableListResponseDTO.makePrettierDateString(table.getRegDateTime());
        this.viewCount = table.getViewCount();
    }
}
