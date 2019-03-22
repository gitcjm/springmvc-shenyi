package com.test.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface UserService {

    void setResponse(HttpServletResponse resp);
    void userReg();
    Boolean userLogin() throws IOException;
}
