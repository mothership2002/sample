package com.privatenotesample.controller;

import com.privatenotesample.dto.Item;
import com.privatenotesample.dto.Tag;
import com.privatenotesample.service.SampleService;
import com.privatenotesample.service.ServiceInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/")
public class SampleController {

    private final ServiceInterface sampleServices;

    public SampleController(SampleService sampleService) {
        this.sampleServices = sampleService;
    }

    @GetMapping("/posts")
    public Map<String, List> selectAll(@RequestParam("page") String page) {
        log.info("selectAll");
        return sampleServices.selectAll(page);
    }

    @PostMapping("/posts")
    public void addItem(@RequestBody Item item) throws IOException {
        log.info("addItem");
        sampleServices.addItem(item);
    }

    @GetMapping("/posts/{itemId}")
    public Item selectEditItem(@PathVariable String itemId) {
        log.info("selectEdit");
        return sampleServices.selectEditItem(itemId);
    }

    @PutMapping("/posts/{itemId}")
    public void editItem(@RequestBody Item item, @PathVariable String itemId) {
        log.info("editItem");
        sampleServices.editItem(item, itemId);
    }

    @GetMapping("/tag/{tagId}")
    public Map<String, List> selectByTag(@PathVariable String tagId, @RequestParam(defaultValue = "1") String page) {
        log.info("selectByTag");
        return sampleServices.selectByTag(tagId, page);
    }

    @GetMapping("/tag-list")
    public List<Tag> selectTagList(){
        log.info("tagList");
        return sampleServices.selectTagList();
    }

}


