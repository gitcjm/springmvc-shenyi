package com.jtthink;

/**
 * Created by cjm on 2019-2-27
 */

import com.test.ShenyiBean;
import com.test.ShenyiPool;
import com.test.service.*;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.jws.soap.SOAPBinding;
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
        //PrintWriter pw = resp.getWriter();

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
        /*Class.forName("com.test.ShenyiPool");
        Connection conn = com.test.ShenyiPool.getConnection();
        conn.createStatement().executeQuery("select sleep(15)");
        com.test.ShenyiPool.freeConnection(conn);*/

        // 面向切面编程，实际上就是”装饰器模式“，只不过通过配置文件来自动装配代码而已
        /**
         * 不自己创建对象，而由 spring 的 BeanFactory 利用配置文件（aopconfig.xml）中的 bean 来创建
         * UserService studentService = new StudentService();
         * */
        /*BeanFactory factory = new ClassPathXmlApplicationContext("aopconfig.xml");
        CommonTool commonTool = (CommonTool) factory.getBean("CommonTool");
        UserService studentService = (UserService) factory.getBean("StudentService");
        UserService teacherService = (UserService) factory.getBean("TeacherService");

        commonTool.setResponse(resp);
        studentService.setResponse(resp);
        teacherService.setResponse(resp);

        studentService.userLogin();
        teacherService.userLogin();*/

        /** AOP编程之：IOC容器（Inversion of Control）*/
        /*UserPay userPay = new UserPay();
        userPay.setResponse(resp);

        //PayService payService = new BankPay();
        //PayService payService = new AliPay();
        PayService payService = new WeiChatPay();

        userPay.setPayMethod(payService);
        userPay.pay();*/

        BeanFactory factory = new ClassPathXmlApplicationContext("payconfig.xml");
        UserPay userPay = (UserPay) factory.getBean("userpay");
        // 配置文件中已引用该bean，不需要创建它了
        //PayService payService = (PayService) factory.getBean("bankpay");
        userPay.setResponse(resp);
        userPay.pay();
    }
}
