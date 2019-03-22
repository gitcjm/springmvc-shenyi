package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CommonTool {
    private HttpServletResponse response;

    public void setResponse(HttpServletResponse resp) {
        this.response = resp;
    }

    public void addUserLog() throws IOException {
        response.getWriter().write("插入一条日志<br/>");
    }

    public void addUserOnline(Boolean logined) throws IOException {
        if (logined) {
            response.getWriter().write("记录在线人数<br/>");
        } else {
            response.getWriter().write("登录失败，不作记录");
        }

    }
}
