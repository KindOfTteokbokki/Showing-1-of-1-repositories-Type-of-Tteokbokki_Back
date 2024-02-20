package fileUpload;

import org.junit.jupiter.api.Test;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class file {
//    1.1. MultipartFile
//    스프링 프레임워크에서 제공하는 인터페이스로, HTTP 요청을 통해 업로드된 파일의 메타데이터 및 내용을 담고 있음
//    주로 스프링 컨트롤러에서 파일 업로드 처리 시 사용
//    1.2. File
//    자바 표준 라이브러리(java.io)에 포함된 클래스로, 파일 시스템에 있는 파일의 메타데이터와 경로를 다룰 때 사용됨
//    주로 파일 시스템에서 파일을 읽거나 쓸 때 사용
    @Test
    void upload() {

    }

}
