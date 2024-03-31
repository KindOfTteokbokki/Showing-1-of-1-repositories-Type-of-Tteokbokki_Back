package halfandhalf.jpa;

import halfandhalf.utteokMain.combination.dto.CombinationDto;
import halfandhalf.utteokMain.combination.entity.CombinationEntity;
import halfandhalf.utteokMain.combination.service.CombinationService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class combinationTest {
    @Autowired
    CombinationService combinationService;

    @Test
    @DisplayName("")
    public void findOne() throws Exception {
        //given

        //when
        CombinationDto oneCombination = combinationService.findOneCombination(1L);
        //then
        Assertions.assertThat(oneCombination.getCombination_seq()).isEqualTo(1L);
    }
    @Test
    @DisplayName("")
    public void find() throws Exception {
        //given

        //when
        List<CombinationEntity> combination = combinationService.findCombination();
        //then
        Assertions.assertThat(combination.size()).isEqualTo(4);
    }
}
