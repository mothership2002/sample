package com.privatenotesample.dao;

import com.privatenotesample.dto.Item;
import com.privatenotesample.dto.Tag;

import java.util.List;
import java.util.Map;

public interface RepositoryInterface {

    Map<String,List> selectAll(int page);

    Item editItem(Item item); ;

    Tag addTag(String group);

    Item addItem(Item item);

    Item selectEditItem(Long itemId);

    Long duplicationCheck(String tagData);

    Map<String, List> selectByTag(Long tagId,int page);

    List<Tag> selectTagList();
}
