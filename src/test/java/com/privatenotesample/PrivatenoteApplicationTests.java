package com.privatenotesample;

import com.privatenotesample.dao.SampleRepository;
import com.privatenotesample.dto.Item;
import com.privatenotesample.dto.Tag;
import com.privatenotesample.service.SampleService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@SpringBootTest
class PrivatenoteApplicationTests {

    SampleRepository sampleRepository = new SampleRepository();
    SampleService sampleService = new SampleService(sampleRepository);

    PrivatenoteApplicationTests() throws IOException {
    }

    @Test
    void contextLoads() {
        Item item1 = new Item();
        item1.setTitle("투명하되 인간에 미인을 영원히 청춘의 고동을 곧 새 운다");
        item1.setContent("투명하되 인간에 미인을 영원히 청춘의 고동을 곧 새 운다. 불러 있을 바이며, 피에 그들의 없으면 예수는 것은 것이다. 맺어, 거친 곧 희망의 무엇이 이상, 곳이 남는 아니다. 일월과 때에, 싸인 철환하였는가? 이는 있는 인간의 우리 아름다우냐? 가는 새 심장은 날카로우나 풀이 열매를 위하여 있는 보라. 싶이 위하여서, 뜨거운지라, 황금시대다. 청춘의 이상의 같은 이상의 수 이상은 있으며, 꾸며 무엇을 있다. 이상의 그들의 그들은 보배를 할지라도 운다. 무엇을 이것이야말로 눈이 따뜻한 구하지 이것을 타오르고 열락의 그러므로 보라. 돋고, 속잎나고, 곧 보는 이상 아니다.\n\n가지에 찾아 만천하의 오직 관현악이며, 위하여서. 대중을 가는 아니더면, 목숨이 때까지 충분히 듣는다. 따뜻한 찬미를 피어나기 영원히 인간에 사막이다. 같은 방황하여도, 인간에 행복스럽고 청춘의 위하여, 새 같지 칼이다. 그러므로 위하여 가치를 있으며, 석가는 아니더면, 놀이 것이다. 귀는 긴지라 아름답고 방황하여도, 뭇 꽃 것이 평화스러운 쓸쓸하랴? 보는 같으며, 따뜻한 두손을 것이 쓸쓸한 아니다. 가치를 더운지라 그들의 우리 그들에게 위하여서. 황금시대를 것이 못할 모래뿐일 따뜻한 풀밭에 심장의 우리는 때문이다. 위하여, 무엇을 대고, 얼마나 그들은 것이다. 못하다 돋고, 하였으며, 이상을 풍부하게 못할 있으랴?\n\n대한 그것을 일월과 청춘의 속에 그들의 끓는 아니다. 끓는 굳세게 쓸쓸한 있는가? 피어나는 그와 못할 그들은 가장 청춘의 일월과 앞이 생명을 보라. 있는 우리 그림자는 이상 이상이 교향악이다. 인간의 소금이라 어디 설산에서 위하여, 청춘의 낙원을 그리하였는가? 없으면 전인 장식하는 바이며, 기관과 기쁘며, 우리 목숨이 뿐이다. 풀이 같으며, 두기 무엇을 봄바람이다. 열매를 그들은 그들은 위하여, 만물은 말이다. 능히 그들은 꽃이 운다. 찾아다녀도, 동산에는 눈이 장식하는 두손을 끓는 주며, 찬미를 이상은 것이다.\n\n만물은 이는 방지하는 눈이 길지 열락의 것이다. 그들의 가치를 물방아 없으면, 쓸쓸하랴? 노래하며 주며, 발휘하기 말이다. 그들은 평화스러운 그것을 위하여서. 따뜻한 넣는 곳으로 사랑의 용감하고 방황하였으며, 가슴이 듣는다. 발휘하기 가는 우리 타오르고 따뜻한 이상을 것이다. 위하여, 끓는 청춘의 영락과 투명하되 이 청춘이 실현에 때문이다. 가슴이 우리는 광야에서 무한한 열락의 발휘하기 설레는 동력은 뜨고, 칼이다. 아니더면, 꽃이 인간의 노래하며 것이다. 것이 원대하고, 구하지 것이다. 인생을 예가 방지하는 이것이다.\n\n #계산 기하, #계산 기하, #계산 기하");
        item1.setDate("2021-09-22T05:17:59.565Z");
        item1.setUpdateDate("2021-09-22T05:17:59.565Z");

        sampleService.addItem(item1);

        Assertions.assertThat(item1.getTagList().get(0).getTagData()).isEqualTo("#계산 기하");
        Assertions.assertThat(item1.getTagList().get(0).getTagId()).isEqualTo(1);

    }

}
