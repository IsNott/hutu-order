package org.nott.common.handler;

import com.alibaba.fastjson2.JSON;
import org.nott.common.ResponseEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Nott
 * @date 2024-6-6
 */

public class HttpHandler {

    public static void writeResponse(ResponseEntity<?> responseEntity, HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(200);
        httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter writer = httpServletResponse.getWriter();
        writer.write(JSON.toJSONString(responseEntity));
        writer.flush();
    }
}
