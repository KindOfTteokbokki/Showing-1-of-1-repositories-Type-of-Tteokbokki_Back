package halfandhalf.utteokMain.review.upload;

import halfandhalf.com.util.getDate;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

public class UploadData {

    private final String uploadDir;
    @Getter
    private final MultipartFile file;

    @Getter
    private final String dirPath;
    @Getter
    private final String makeDir;
    @Getter
    private final String original;
    @Getter
    private final String masking;
    @Getter
    private final String extension;

    public UploadData(String uploadDir, MultipartFile file) {
        this.uploadDir = uploadDir;
        this.file = file;
        this.original = file.getOriginalFilename();

        // uploadDir을 사용하면 static처럼 뒤에 계속 날짜가 붙는다.
        this.makeDir = uploadDir + getDate.getCurrentTime("YYYYMMDD") + File.separator;

//        int dirIndex = makeDir.indexOf("utteok");  // local 전용
        this.dirPath = makeDir.substring(makeDir.indexOf("/src/"));

        // 파일명 난수화 + 확장자
        this.masking = UUID.randomUUID().toString().substring(0, 5) + getDate.getCurrentTime("HHmmss");

        this.extension = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().indexOf("."));
    }

}
