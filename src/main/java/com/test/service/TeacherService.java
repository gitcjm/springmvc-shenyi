package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TeacherService implements UserService {
    private HttpServletResponse response;

    @Override
    public void setResponse(HttpServletResponse resp) {
        this.response = resp;
    }

    @Override
    public void userReg() {

    }

    @Override
    public Boolean userLogin() throws IOException {
        response.getWriter().write("教师登录<br/>");
        return false;
    }
}
