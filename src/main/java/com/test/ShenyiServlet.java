package com.test;

/**
 * 根据 web.xml 配置，/myservlet/（统一资源标示符）由此类来提供服务
 * 实际上，此类只提供调度，具体的服务由 ShenyiClass 类提供
 * 调用方法采用java反射机制
 * */

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ShenyiServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setHeader("Content-type", "text/html; Charset=utf-8");
        PrintWriter pw = resp.getWriter();

        // java 反射机制
        try {
            Class c = Class.forName("com.test.ShenyiClass");
            Object obj = c.newInstance();

            // 截取URI, 并调用与注解同名的那个方法
            String getUri = req.getRequestURI();
            getUri = getUri.replace("/shenyi_war_exploded/myservlet/", "");

            Method[] mList = c.getMethods();
            for (Method m : mList) {
                // 根据注解，调用相应的方法
                Annotation[] anList = m.getAnnotations();
                for(Annotation an : anList) {
                    ShenyiAnnotation getA = (ShenyiAnnotation)an;
                    if(getA.name().equals(getUri)) {
                        m.invoke(obj, new Object[]{req, resp});
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
