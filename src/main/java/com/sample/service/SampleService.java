package com.sample.service;

import com.sample.dao.RepositoryInterface;
import com.sample.dao.SampleRepository;
import com.sample.dto.Item;
import com.sample.dto.Tag;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
public class SampleService implements ServiceInterface {

    private final RepositoryInterface sampleRepository;

    public SampleService(SampleRepository sampleRepository) throws IOException {
        this.sampleRepository = sampleRepository;

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Workspace\\sample\\DBSample.txt"));

        String sampleJson = "";
        String temp = "";
        while ((temp = bufferedReader.readLine()) != null) {
            sampleJson += temp;
        }

        bufferedReader.close();

        JSONObject jObject = new JSONObject(sampleJson);

        JSONArray jArr = jObject.getJSONArray("sample");

        for (int i = 0; i < jArr.length(); i++) {
            JSONObject obj = jArr.getJSONObject(i);

            Item item = new Item();

            item.setTitle(obj.getString("title"));
            item.setContent(obj.getString("content"));
            item.setDate(obj.getString("date"));
            item.setUpdateDate(obj.getString("updateDate"));

            contentRegex(item);
            sampleRepository.addItem(item);
        }
    }

    public Map<String, List> selectAll(String page) {
        return sampleRepository.selectAll(Integer.parseInt(page));
    }

    public Item addItem(Item item) {
        contentRegex(item);
        item.setDate(date());
        return sampleRepository.addItem(item);
    }

    public Item editItem(Item item, String itemId) {
        contentRegex(item);
        item.setItemId(Long.parseLong(itemId));
        item.setUpdateDate(date());
        return sampleRepository.editItem(item);
    }

    public String date() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        return dateFormat.format(date);
    }

    public Item selectEditItem(String itemId) {
        return sampleRepository.selectEditItem(Long.parseLong(itemId));
    }

    public void contentRegex(Item item) {
        log.info("contentRegex");
        String decompositionTag = item.getContent();
        Pattern p = Pattern.compile("#[a-zA-Z가-힣0-9 ]+[^,]");
        Matcher m = p.matcher(decompositionTag);

        List<Tag> tagList = new ArrayList<>();
        List<String> tagArray = new ArrayList<>();

        while (m.find()) {
            Tag tag = new Tag();
            if (tagArray.isEmpty()) {
                setTag(m.group(), tagList, tagArray, tag);
            } else {
                int count = 0;
                for (String s : tagArray) {
                    if (!s.equals(m.group())) {
                        count++;
                    }
                    else {
                        break;
                    }
                }
                if (count == tagArray.size()) {
                    setTag(m.group(), tagList, tagArray, tag);
                }
            }
        }
        item.setTagList(tagList);
        item.setContent(item.getContent().split("#")[0]);
    }

    public void setTag(String tagValue, List<Tag> tagList, List<String> tagArray, Tag tag) {
        tag = getTag(tagValue, tag);
        tagArray.add(tagValue);
        tagList.add(tag);
    }

    public Tag getTag(String tagValue, Tag tag) {
        Long tagId = sampleRepository.duplicationCheck(tagValue);
        if (tagId == null) {
            tag = sampleRepository.addTag(tagValue);
        } else {
            tag.setTagId(tagId);
            tag.setTagData(tagValue);
        }
        return tag;
    }

    public Map<String, List> selectByTag(String tagId, String page) {
        return sampleRepository.selectByTag(Long.parseLong(tagId), Integer.parseInt(page));
    }

    @Override
    public List<Tag> selectTagList() {
        return sampleRepository.selectTagList();
    }
}
