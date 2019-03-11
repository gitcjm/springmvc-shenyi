package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ShenyiPool {
    /**
     * 数据库连接字符串
     * */
    static String conn_url = "jdbc:mysql://localhost:3306/jt_product?" +
            "user=root&password=Mysql57@deb&useUnicode=true&characterEncoding=utf-8";
    /**
     * 自定义连接池
     * The second parameter of map is status
     * "0": connection is sleeep
     * "1": connection is busy
     * */
    static Map<Connection, String> pools;

    // 静态代码块：首先被执行，且只执行一次，常驻内存
    static {
        pools = new HashMap<Connection, String>();

        Connection pool = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for(int i=1; i<=1; i++) {
                pool = DriverManager.getConnection(conn_url);
                pools.put(pool, "0");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 从连接池中取出一个连接
    public static Connection getConnection() throws SQLException {
        // 遍历连接池，寻找空闲的连接
        /*Iterator<Connection> iterator = pools.keySet().iterator();
        while (iterator.hasNext()) {
            pool = iterator.next();
            if (pools.replace(pool, "0", "1")) {
                return pool;
            }
        }*/
        for (Connection pool : pools.keySet()) {
            // 如果当前连接空闲，则返回这个连接，并标记为忙
            if (pools.replace(pool, "0", "1")) {
                return pool;
            }
        }

        // 如果连接池中没有空闲连接，则新建一个，并放入池中
        Connection pool = null;
        pool = DriverManager.getConnection(conn_url);
        pools.put(pool, "0");
        return pool;
    }

    // 释放一个连接
    public static void freeConnection(Connection conn) {
        // 将连接标记为闲
        pools.replace(conn, "1", "0");
    }
}
