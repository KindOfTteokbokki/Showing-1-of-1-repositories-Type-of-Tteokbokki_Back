package halfandhalf.utteokMain.review.upload;

import halfandhalf.com.exception.FileUploadException;

import java.io.File;

public class UploadImg {
    public static void uploadOperating(UploadData uploadData) throws FileUploadException {
        try{
            File folder = new File(uploadData.getMakeDir());

            if (!folder.exists()) {
                folder.mkdirs();
            }

            uploadData.getFile().transferTo(new File(uploadData.getMakeDir() + uploadData.getMasking() + uploadData.getExtension()));
        }
        catch (Exception e){
            throw new FileUploadException("파일 누락, 또는 다른 형식으로 요청하였습니다.");
        }
    }
}
