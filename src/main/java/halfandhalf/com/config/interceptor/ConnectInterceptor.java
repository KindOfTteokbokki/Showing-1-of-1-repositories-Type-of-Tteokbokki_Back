package halfandhalf.com.config.interceptor;

import halfandhalf.domain.LG0010.dao.LG0040Dao;
import halfandhalf.domain.LG0010.dto.LG0040Dto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

@Component
public class ConnectInterceptor implements HandlerInterceptor {

    private final LG0040Dao lg0040Dao;

    public ConnectInterceptor(LG0040Dao lg0040Dao) {
        this.lg0040Dao = lg0040Dao;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("get IP : " + getClientIp(request));
        String[] ip = new String[2];
        try {
            ip = getClientIp(request).split("/");
        } catch (ArrayIndexOutOfBoundsException e) {
            ip[0] = "";
            ip[1] = getClientIp(request);
        } finally {
            String[] finalIp = ip;
            Optional.ofNullable(lg0040Dao.checkIp(new LG0040Dto(finalIp[0], finalIp[1])))
                    .ifPresentOrElse(
                            (findIp) -> lg0040Dao.updateDateIp(new LG0040Dto(findIp.getName(), findIp.getIp()))
                            , () -> lg0040Dao.regIp(new LG0040Dto(finalIp[0], finalIp[1]))
                    );
        }
    }

    public static String getClientIp(HttpServletRequest request) throws UnknownHostException {
        String ip = request.getHeader("X-Forwarded-For");

        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-Real-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("X-RealIP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("REMOTE_ADDR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }

        if(ip.equals("0:0:0:0:0:0:0:1") || ip.equals("127.0.0.1"))
        {
            InetAddress address = InetAddress.getLocalHost();
            ip = address.getHostName() + "/" + address.getHostAddress();
        }

        return ip;
    }

}
