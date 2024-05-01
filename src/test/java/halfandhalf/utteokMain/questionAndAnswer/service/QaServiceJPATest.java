package halfandhalf.utteokMain.questionAndAnswer.service;

import halfandhalf.utteokMain.questionAndAnswer.dto.QaDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
class QaServiceJPATest {
    @Autowired
    QaService qaService;

    @Test
    public void qaCache() throws Exception {
        // given

        // when
        long start1 = System.currentTimeMillis();
        System.out.println(qaService.findByCodeIdAndUseYn("101", "Y").toString());
        System.out.println(qaService.findByCodeIdAndUseYn("102", "Y").toString());
        long end1 = System.currentTimeMillis();


        long start2 = System.currentTimeMillis();
        System.out.println(qaService.findByCodeIdAndUseYn("101", "Y").toString());
        System.out.println(qaService.findByCodeIdAndUseYn("102", "Y").toString());
        long end2 = System.currentTimeMillis();

        System.out.println("캐시 X");
        System.out.println(end1 - start1);

        System.out.println("캐시 O");
        System.out.println(end2 - start2);

        // then
    }
}