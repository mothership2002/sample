package com.privatenotesample.dao;

import com.privatenotesample.dto.Item;
import com.privatenotesample.dto.Tag;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Repository;

import java.util.*;


@Repository
@Slf4j
public class SampleRepository implements RepositoryInterface {

    private static Map<Long, Item> store = new HashMap<>();
    private static Map<String, Long> tagStore = new HashMap<>();
    private static Map<Long, List<Long>> tagIndex = new HashMap<>();

    private static Long itemSequence = 0L;
    private static Long tagSequence = 0L;

    public Map<String, List> selectAll(int page) {
        Map<String, List> map = new HashMap<>();

        List<Item> list = new ArrayList<>(store.values());
        List<Item> pageList = new ArrayList<>();

        List<Integer> pagination = getPagination(page, list, pageList);

        map.put("posts", pageList);
        map.put("pagination", pagination);
        return map;
    }

    public Item selectEditItem(Long itemId) {
        return store.get(itemId);
    }

    public Item addItem(Item item) {
        itemSequence++;
        item.setItemId(itemSequence);
        store.put(itemSequence, item);
        setTagIndex(item);
        return item;
    }

    public Item editItem(Item item) {
        store.get(item.getItemId()).setTitle(item.getTitle());
        store.get(item.getItemId()).setUpdateDate(item.getUpdateDate());
        deleteTagIndex(item);
        store.get(item.getItemId()).setTagList(item.getTagList());
        store.get(item.getItemId()).setContent(item.getContent());
        setTagIndex(item);
        return item;
    }

    public Map<String, List> selectByTag(Long tagId, int page) {

        Map<String, List> map = new HashMap<>();
        List<Long> itemIndex = tagIndex.get(tagId);

        for(Map.Entry<String, Long> entry : tagStore.entrySet()){
            if(entry.getValue().equals(tagId)){
                List<String> temp = new ArrayList<>();
                temp.add(entry.getKey());
                map.put("tagName",temp);
                break;
            }
        }

        List<Item> list = new ArrayList<>();
        for (Long l : itemIndex) {
            list.add(store.get(l));
        }

        List<Item> listByPage = new ArrayList<>();

        List<Integer> pagination = getPagination(page, list, listByPage);

        map.put("posts", listByPage);
        map.put("pagination", pagination);
        return map;
    }

    public List<Tag> selectTagList() {
        List<Tag> list = new ArrayList<>();
        for(Map.Entry<String,Long> entry : tagStore.entrySet()){
            Tag tag = new Tag();
            tag.setTagData(entry.getKey());
            tag.setTagId(entry.getValue());
            list.add(tag);
        }
        return list;
    }


    //데이터 정제 관련 기능
    public List<Integer> getPagination(int page, List<Item> allItemList, List<Item> pageList) {
        int allPage = allItemList.size() - 1;
        List<Integer> pagination = new ArrayList<>();

        //현재 0 - 모든 아이템 갯수 1 - 페이지당 2
        pagination.add(page);
        pagination.add(allPage);
        pagination.add(5);

        // (list.size() - 1) 최근 삽입 데이터
        if (allItemList.size() != 0) {
            //한 페이지당 5개라 쳤을때
            for (int i = 0; i < 5; i++) {
                int a = allPage - i - ((page - 1) * 5);
                pageList.add(allItemList.get(a));
                if (a == 0) {
                    break;
                }
            }

        }
        return pagination;
    }

    public Long duplicationCheck(String tagData) {
        return tagStore.get(tagData);
    }

    public Tag addTag(String tagContent) {
        tagSequence++;
        Tag tag = new Tag();
        tag.setTagId(tagSequence);
        tag.setTagData(tagContent);
        tagStore.put(tag.getTagData(), tag.getTagId());
        return tag;
    }

    public void setTagIndex(Item item) {
        for(Tag t : item.getTagList()){
            List<Long> itemIdList = tagIndex.get(t.getTagId());
            if(itemIdList != null){
                itemIdList.add(item.getItemId());
            } else {
                List<Long> temp = new ArrayList<>();
                temp.add(item.getItemId());
                tagIndex.put(t.getTagId(),temp);
            }
        }
    }

    public void deleteTagIndex(Item item) {
        List<Tag> temp = store.get(item.getItemId()).getTagList();
        for(Tag t : temp){
            List<Long> itemIdList = tagIndex.get(t.getTagId());
            if(itemIdList != null || !itemIdList.isEmpty()){
                int deleteIndex = itemIdList.indexOf(item.getItemId());
                itemIdList.remove(deleteIndex);
            }
            if(itemIdList.isEmpty()){
                tagIndex.remove(t.getTagId());
                tagStore.remove(t.getTagData());
            }
        }
    }
}
