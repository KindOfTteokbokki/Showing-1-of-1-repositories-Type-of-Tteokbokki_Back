package halfandhalf.com.util;

import org.springframework.util.StringUtils;

public class Validation {
    public static String Nickname(String name){
        String nickName = name.replaceAll("[^ㄱ-ㅎ|가-힣]", "");
        if(!StringUtils.hasText(nickName)) {
            return "utteok" + getDate.getCurrentTime("YYYYMMDDHHmmss");
        }
        else {
            return nickName;
        }
    }
}
