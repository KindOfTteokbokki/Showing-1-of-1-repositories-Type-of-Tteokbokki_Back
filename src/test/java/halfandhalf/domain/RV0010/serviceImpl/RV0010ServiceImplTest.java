package halfandhalf.domain.RV0010.serviceImpl;

import halfandhalf.domain.RV0010.dto.RV0010Dto;
import halfandhalf.domain.RV0010.dto.RV0011Dto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
class RV0010ServiceImplTest {
    @Autowired RV0010ServiceImpl rv0010Service;

    @Test
    @DisplayName("추천 - 페이징 처리")
    public void findRecommendByPage() throws Exception {
        //given
        RV0011Dto rv0011Dto = new RV0011Dto(0, 1);
        //when
        List<RV0010Dto> recommendByPage = rv0010Service.findRecommendByPage(rv0011Dto, 1L);
        //then
        Assertions.assertThat(recommendByPage.size()).isEqualTo(1);
    }
    
    @Test
    @DisplayName("추천 - 시퀀스로 하나 가져오기")
    public void findOneFromRecommend() throws Exception {
        //given
        RV0010Dto rv0010Dto = new RV0010Dto();
        rv0010Dto.setReview_seq(1);
        //when
        RV0010Dto oneFromRecommend = rv0010Service.findOneFromRecommend(rv0010Dto);
        //then
        Assertions.assertThat(oneFromRecommend).isNotNull();
        Assertions.assertThat(oneFromRecommend.getReview_seq()).isEqualTo(rv0010Dto.getReview_seq());
    }

    @Test
    @DisplayName("사진 없이 컨텐츠만 저장")
    public void saveRecommend1() throws Exception {
        //given
        RV0010Dto rv0010Dto = new RV0010Dto();
        rv0010Dto.setContent("test_content");
        MultipartFile file = new MockMultipartFile("", new FileInputStream(new File("")));
        //when
        rv0010Service.saveRecommend(rv0010Dto, file);
        //then
//        verify(saveRecommend).save(entity);
//        Assertions.
    }
    @Test
    @DisplayName("사진 있이 컨텐츠와 저장")
    public void saveRecommend2() throws Exception {
        //given
        RV0010Dto rv0010Dto = new RV0010Dto();
        rv0010Dto.setContent("test_file");
        MultipartFile file = new MockMultipartFile("0001.jpg"
                , new FileInputStream(new File("C:\\Users\\Hwang\\Desktop\\서버구축\\img\\꿀조합\\0001.jpg")));
        //when
        rv0010Service.saveRecommend(rv0010Dto, file);
        //then
        File f = new File("C:\\workspace\\Type-of-Tteokbokki_Back\\utteok\\image\\review\\");
        Assertions.assertThat(f.exists()).isNotNull();
    }

    @Test
    @DisplayName("사진 없음 컨텐츠 없음")
    public void saveRecommend3() throws Exception {
        //given
        RV0010Dto rv0010Dto = new RV0010Dto();
        MultipartFile file = new MockMultipartFile("", new FileInputStream(new File("")));
        //when
        rv0010Service.saveRecommend(rv0010Dto, file);
        //then
//        Assertions.assertThat()
    }

    @Test
    @DisplayName("사진 있음 컨텐츠 없음")
    public void saveRecommend4() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("컨텐츠 수정")
    public void modifyRecommend1() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("사진 수정")
    public void modifyRecommend2() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("컨텐츠 사진 수정")
    public void modifyRecommend3() throws Exception {
        //given

        //when

        //then

    }

    @Test
    @DisplayName("추천 삭제")
    public void deleteRecommend() throws Exception {
        //given

        //when

        //then

    }
}