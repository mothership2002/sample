package com.sample;

import com.sample.dao.SampleRepository;
import com.sample.dto.Item;
import com.sample.service.SampleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class SampleApplicationTests {

    SampleRepository sampleRepository = new SampleRepository();
    SampleService sampleService = new SampleService(sampleRepository);

    SampleApplicationTests() throws IOException {
    }

    @Test
    void contextLoads() {
        Item item1 = new Item();
        item1.setTitle(" ");
        item1.setContent(" #1234");
        item1.setDate("2021-09-22T05:17:59.565Z");
        item1.setUpdateDate("2021-09-22T05:17:59.565Z");

        sampleService.addItem(item1);

        Assertions.assertThat(item1.getTagList().get(0).getTagData()).isEqualTo("#123");
        Assertions.assertThat(item1.getTagList().get(0).getTagId()).isEqualTo(1);

    }

}
