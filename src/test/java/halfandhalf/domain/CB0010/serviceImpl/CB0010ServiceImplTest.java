package halfandhalf.domain.CB0010.serviceImpl;

import halfandhalf.domain.CB0010.dto.CB0010Dto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
class CB0010ServiceImplTest {
    @Autowired CB0010ServiceImpl cb0010Service;

    @Test
    @DisplayName("findOneFromCombination")
    public void findOneFromCombination() throws Exception {
        //given
        CB0010Dto cb0010Dto = new CB0010Dto();
        cb0010Dto.setCombination_seq(1);
        //when
        CB0010Dto oneFromCombination = cb0010Service.findOneFromCombination(cb0010Dto);
        //then
        Assertions.assertThat(oneFromCombination.getFile_masking_name()).isEqualTo("0001.jpg");
    }
}