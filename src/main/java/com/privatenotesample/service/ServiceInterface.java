package com.privatenotesample.service;

import com.privatenotesample.dto.Item;
import com.privatenotesample.dto.Tag;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

public interface ServiceInterface {

    Map<String, List> selectAll(String page);

    Item addItem(Item item);

    Item editItem(Item item, String itemId);

    Item selectEditItem(String itemId);

    String date();

    void contentRegex(Item item);

    Tag getTag(String tagValue, Tag tag);

    void setTag(String tagValue, List<Tag> tagList, List<String> tagArray, Tag tag);

    Map<String, List> selectByTag(String tagId, String page);

    List<Tag> selectTagList();
}
