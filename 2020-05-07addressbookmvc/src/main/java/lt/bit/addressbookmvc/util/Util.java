package lt.bit.addressbookmvc.util;

import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.HandlerMapping;

public class Util {
    public static String requestPath(HttpServletRequest request) {
        return
            request.getContextPath() +
            request.getAttribute(HandlerMapping.LOOKUP_PATH) +
            (request.getAttribute(HandlerMapping.LOOKUP_PATH).toString().endsWith("/") ? "" : "/");
    }
}





