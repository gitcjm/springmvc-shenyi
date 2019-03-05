package com.jtthink;

/**
 * Created by cjm on 2019-2-27
 */

import com.test.ShenyiBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
public class IndexController {

    @Autowired
    ShenyiBean sb;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void loadIndex(HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-type", "text/html; Charset=utf-8");
        PrintWriter pw = resp.getWriter();

        /*ShenyiBean sb = new ShenyiBean();
        sb.setName("Shenyi");
        sb.setPhone("139-0359-0101");*/

        // 通过 @Autowired 注解，生成sb对象，通过xml初始化其属性
        pw.write(sb.getName() + "<br/>" + sb.getPhone());
    }
}
