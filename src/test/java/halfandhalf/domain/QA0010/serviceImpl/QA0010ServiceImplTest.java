package halfandhalf.domain.QA0010.serviceImpl;

import halfandhalf.domain.QA0010.dto.QA0011Dto;
import halfandhalf.domain.QA0010.dto.QA0012Dto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class QA0010ServiceImplTest {
    @Autowired QA0010ServiceImpl qa0010Service;

    @Test
    @DisplayName("")
    public void findQuestion() throws Exception {
        //given
        //when
        List<QA0011Dto> question = qa0010Service.findQuestion();
        //then
        Assertions.assertThat(question).isNotNull();
    }

    @Test
    @DisplayName("")
    public void findAnswer() throws Exception {
        //given
        //when
        List<QA0012Dto> answer = qa0010Service.findAnswer();
        //then
        Assertions.assertThat(answer).isNotNull();
    }
}