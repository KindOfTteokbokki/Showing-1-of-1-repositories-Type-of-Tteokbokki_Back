package halfandhalf.domain.TT0010.serviceImpl;

import halfandhalf.com.aop.login.EventImpl;
import halfandhalf.com.exception.LoginException;
import halfandhalf.domain.TT0010.dto.TT0010Dto;
import halfandhalf.domain.TT0010.dto.TT0012Dto;
import org.assertj.core.api.Assertions;
//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/*
 * Junit 4 -> Junit 5
 * import org.junit.Test; > org.junit.jupiter.api.Test;
 * @RunWith(SpringRunner.class) > @ExtendWith(SpringExtension.class)
 * 접근제어자 public 으로 수정
 */


@ExtendWith(SpringExtension.class)
@SpringBootTest(properties = "classpath:/application.yml")
@TestPropertySource(properties = "spring.profiles.active=test")
@Transactional
public class TT0010ServiceImplTest {
    @Autowired
    TT0010ServiceImpl tt0010Service;

    @Test
    @DisplayName("")
    public void EventImpl() throws Exception {
        //given
        EventImpl event = new EventImpl();
        //when
//        event.beforeEvent();
        //then
        org.junit.jupiter.api.Assertions.assertThrows(LoginException.class, () ->
                {
                    event.beforeEvent();
                }
        );
    }

    @Test
    @DisplayName("칭호 노출 - 아무것도 선택 없음, 아이디 있음")
    public void findTitle1() throws Exception {
        //given
        TT0010Dto tt0010Dto = new TT0010Dto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700");
        //when
        TT0010Dto title = tt0010Service.findTitle(tt0010Dto, 1L);
        //then
        Assertions.assertThat(title).isNotNull();
        System.out.println("title.toString() : " + title.toString());

        List<TT0010Dto> haveTitle = tt0010Service.findHaveTitle(1L);
        TT0010Dto haveTitleDto = null;
        for(TT0010Dto dto : haveTitle) {
            if(dto.getTitle_name().equals(title.getTitle_name())) {
                haveTitleDto = dto;
            }
        }
        Assertions.assertThat(title.getTitle_name()).isEqualTo(haveTitleDto.getTitle_name());
    }
    @Test
    @DisplayName("칭호 노출 - 선택은 있지만 없는 데이터, 아이디 있음")
    public void findTitle2() throws Exception {
        //given
        TT0010Dto tt0010Dto = new TT0010Dto("ch100", "ch200", "ch300", "ch402", "ch500", "ch600", "ch703");
        //when
        TT0010Dto title = tt0010Service.findTitle(tt0010Dto, 1L);
        //then
        Assertions.assertThat(title).isNotNull();
        System.out.println("title.toString() : " + title.toString());

        List<TT0010Dto> haveTitle = tt0010Service.findHaveTitle(1L);
        System.out.println("haveTitle.size() : " + haveTitle.size());
        TT0010Dto haveTitleDto = null;
        for(TT0010Dto dto : haveTitle) {
            if(dto.getTitle_name().equals(title.getTitle_name())) {
                haveTitleDto = dto;
            }
        }
        Assertions.assertThat(title.getTitle_name()).isEqualTo(haveTitleDto.getTitle_name());
        System.out.println("haveTitleDto.toString() : " + haveTitleDto.toString());
    }
    @Test
    @DisplayName("칭호 노출 - 선택은 있지만 있는 데이터, 아이디 있음")
    public void findTitle3() throws Exception {
        //given
        TT0010Dto tt0010Dto = new TT0010Dto("ch102", "ch200", "ch301", "ch401", "ch501", "ch601", "ch700");
        //when
        TT0010Dto title = tt0010Service.findTitle(tt0010Dto, 1L);
        //then
        Assertions.assertThat(title).isNotNull();
        System.out.println("title.toString() : " + title.toString());

        List<TT0010Dto> haveTitle = tt0010Service.findHaveTitle(1L);
        System.out.println("haveTitle.size() : " + haveTitle.size());
        TT0010Dto haveTitleDto = null;
        for(TT0010Dto dto : haveTitle) {
            if(dto.getTitle_name().equals(title.getTitle_name())) {
                haveTitleDto = dto;
            }
        }
        Assertions.assertThat(title.getTitle_name()).isEqualTo(haveTitleDto.getTitle_name());
        System.out.println("haveTitleDto.toString() : " + haveTitleDto.toString());
    }
    @Test
    @DisplayName("칭호 노출 - 아무것도 선택 없음, 아이디 없음")
    public void findTitle4() throws Exception {
        //given
        TT0010Dto tt0010Dto = new TT0010Dto("ch100", "ch200", "ch300", "ch400", "ch500", "ch600", "ch700");
        //when
        TT0010Dto title = tt0010Service.findTitle(tt0010Dto, 0L);
        //then
        Assertions.assertThat(title).isNotNull();
        System.out.println("title.toString() : " + title.toString());

        Assertions.assertThat(tt0010Service.findHaveTitle(0L)).isNotNull();
    }
    @Test
    @DisplayName("칭호 노출 - 선택은 있지만 없는 데이터, 아이디 없음")
    public void findTitle5() throws Exception {
        //given
        TT0010Dto tt0010Dto = new TT0010Dto("ch100", "ch200", "ch300", "ch402", "ch500", "ch600", "ch703");
        //when
        TT0010Dto title = tt0010Service.findTitle(tt0010Dto, 0L);
        //then
        Assertions.assertThat(title).isNotNull();
        System.out.println("title.toString() : " + title.toString());

        Assertions.assertThat(tt0010Service.findHaveTitle(0L)).isNotNull();
    }
    @Test
    @DisplayName("칭호 노출 - 선택이 있고 있는 데이터, 아이디 없음")
    public void findTitle6() throws Exception {
        //given
        TT0010Dto tt0010Dto = new TT0010Dto("ch102", "ch200", "ch301", "ch401", "ch501", "ch601", "ch700");
        //when
        TT0010Dto title = tt0010Service.findTitle(tt0010Dto, 0L);
        //then
        Assertions.assertThat(title).isNotNull();
        System.out.println("title.toString() : " + title.toString());

        Assertions.assertThat(tt0010Service.findHaveTitle(0L)).isNotNull();
    }

    @Test
    @DisplayName("모든 칭호 노출 - 칭호가 있는 경우")
    public void findAllTitleNotHave() throws Exception {
        //given

        //when
        List<TT0012Dto> allTitleFromUser = tt0010Service.findAllTitleNotHave(1L);
        //then
        Assertions.assertThat(allTitleFromUser).isNotNull();
        System.out.println("allTitleFromUser.toString() : " + allTitleFromUser.toString());
    }
    @Test
    @DisplayName("칭호 하나 노출 - 칭호가 있는 경우")
    public void findCountTitle() throws Exception {
        //given

        //when
        TT0012Dto countTitle = tt0010Service.findCountTitle(1L);
        //then
        Assertions.assertThat(countTitle).isNotNull();
        System.out.println("countTitle.toString() : " + countTitle.toString());
    }
}