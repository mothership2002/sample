package com.privatenotesample.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class Item {

    private Long itemId;

    private String title;
    private String content;

    private String date;
    private String updateDate;

    private List<Tag> tagList;
}
