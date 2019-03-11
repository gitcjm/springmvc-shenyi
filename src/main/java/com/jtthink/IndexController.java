package com.jtthink;

/**
 * Created by cjm on 2019-2-27
 */

import com.test.ShenyiBean;
import com.test.ShenyiPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@Controller
public class IndexController {

    @Autowired
    ShenyiBean sb;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public void loadIndex(HttpServletResponse resp)
            throws IOException, ClassNotFoundException, SQLException
    {
        resp.setHeader("Content-type", "text/html; Charset=utf-8");
        PrintWriter pw = resp.getWriter();

        /*ShenyiBean sb = new ShenyiBean();
        sb.setName("Shenyi");
        sb.setPhone("139-0359-0101");
        // 通过 @Autowired 注解，生成sb对象，通过xml初始化其属性
        pw.write(sb.getName() + "<br/>" + sb.getPhone());*/

        // 连接mysql数据库（官方文档上有示例）
        /*String conn_url = "jdbc:mysql://localhost:3306/jt_product?user=root
                &password=Mysql57@deb&useUnicode=true&characterEncoding=utf-8";
        // mysql-connector-java 8.0版与5.1版有区别
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(conn_url);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select sleep(10)");
        conn.close();*/

        // 使用数据库连接池
        Class.forName("com.test.ShenyiPool");
        Connection conn = com.test.ShenyiPool.getConnection();
        conn.createStatement().executeQuery("select sleep(15)");
        com.test.ShenyiPool.freeConnection(conn);
    }
}
