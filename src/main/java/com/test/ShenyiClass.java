package com.test;

/**
 * 为/myservlet/（统一资源标识符）提供具体服务的类 */

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;
import org.apache.commons.configuration.tree.ConfigurationNode;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ShenyiClass {

    @ShenyiLoad
    com.test.ShenyiBean sb;

    // 在此使用了“我自己的注解”
    @ShenyiAnnotation(name = "an", age = 20)
    public void getMe(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("name", "cjm");
        req.setAttribute("age", "18");
        // 通过 request 的调度方法，将 req, resp 转发到 an.asp 页面
        req.getRequestDispatcher("/an.jsp").forward(req, resp);
    }

    // 利用注解，通过 URI 来执行此方法
    @ShenyiAnnotation(name="age", age = 20)
    public void getAge(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().write("My age is 22");
    }

    // if (URI == "cfg") then execute this method
    @ShenyiAnnotation(name="cfg", age = 30)
    public void getConfig(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setHeader("Content-type", "text/html; Charset=utf-8");
        // 获取配置文件
        String mycfg = this.getClass().getClassLoader().getResource("")
                .getPath() + "../shenyi-serv.xml";
        //resp.getWriter().write(mycfg);
        // 读取配置文件
        try {
            XMLConfiguration xc = new XMLConfiguration();
            xc.load(mycfg);

            ConfigurationNode root = xc.getRootNode();
            //resp.getWriter().write(root.getName());
            List<ConfigurationNode> list = root.getChildren();
            for (ConfigurationNode b : list) {
                List<ConfigurationNode> listP = b.getChildren();
                for(ConfigurationNode p : listP) {
                    String pName = p.getAttribute(1).getName();
                    String pValue = p.getAttribute(1).getValue().toString();
                    resp.getWriter().write( pName + "=" + pValue + "<br/>");
                }
            }

        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }
}
