package com.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@Controller
public class MyDBServlet extends HttpServlet {

    // 继承 HttpServlet 类，就要覆盖 doGet 方法
    @RequestMapping(value = "/mydb", method = RequestMethod.GET )
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html; Charset=utf-8");
        PrintWriter pw = resp.getWriter();

        // 连接 mysql 数据库
        String conn_url = "jdbc:mysql://localhost:3306/jt_product?user=root&password=Mysql57@deb" +
                "&useUnicode=true&characterEncoding=utf-8";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(conn_url);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product");
            while (rs.next()) {
                pw.write(rs.getString("p_name") + " ");
                pw.write(rs.getString("p_type") + "<br/>");
            }
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

}
