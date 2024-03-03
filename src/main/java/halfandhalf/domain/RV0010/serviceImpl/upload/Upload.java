package halfandhalf.domain.RV0010.serviceImpl.upload;

import halfandhalf.com.exception.FileUploadException;
import halfandhalf.com.util.getDate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Upload {

    String originalFileName = "";
    private final String uploadDir;
    private final MultipartFile file;

    public Upload(String uploadDir, MultipartFile file) {
        this.uploadDir = uploadDir;
        this.file = file;
    }

    public Map<String,String> uploadImage() throws Exception {
        Map<String,String> imgFile = new HashMap<>();
        originalFileName = file.getOriginalFilename();

        try{
            String makeDir = dir().get("makeDir");
            String dirPath = dir().get("dirPath");
            String maskingFileName = maskingFileName();
            String extension = extension();

            File folder = new File(makeDir);

            if (!folder.exists()) {
                folder.mkdirs();
            }

            File destination = new File(makeDir + maskingFileName + extension);
            file.transferTo(destination);

            imgFile.put("path",dirPath);
            imgFile.put("original",originalFileName);
            imgFile.put("masking",maskingFileName + extension);
        }
        catch (Exception e){
            throw new FileUploadException("파일 누락, 또는 다른 형식으로 요청하였습니다");
        }
        return imgFile;
    }

    private Map<String, String> dir() {
        Map<String, String> dir = new HashMap<>();
        String nowDate = getDate.getCurrentTime("YYYYMMDD");
        String makeDir = uploadDir + nowDate + File.separator;
        // uploadDir을 사용하면 static처럼 뒤에 계속 날짜가 붙는다.
        dir.put("makeDir", makeDir);

        int dirIndex = makeDir.indexOf("/src/");
//        int dirIndex = makeDir.indexOf("utteok");  // local 전용
        dir.put("dirPath", makeDir.substring(dirIndex));
        return dir;
    }

    private String maskingFileName() {
        String nowTime = getDate.getCurrentTime("HHmmss");
        // 파일명 난수화 + 확장자
        UUID uuid = UUID.randomUUID();
        return uuid.toString().substring(0, 5) + nowTime;
    }

    private String extension() {
        return originalFileName.substring(originalFileName.indexOf("."));
    }
}
