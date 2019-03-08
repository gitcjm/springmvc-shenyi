package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

public class ShenyiPool {
    // Database connection string
    private static String conn_url = "jdbc:mysql://localhost:3306/jt_product?" +
            "user=root&password=Mysql57@deb&useUnicode=true&characterEncoding=utf-8";
    /**
     * 自定义连接池
     * The second parameter of map is status
     * "0": connection is sleeep
     * "1": connection is busy
     * */
    private static Map<Connection, String> pools;

    // 静态代码块：首先被执行，且只执行一次，常驻内存
    static {
        pools = new HashMap<Connection, String>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            for(int i=1; i<=2; i++) {
                pools.put(DriverManager.getConnection(conn_url), "0");
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // 从连接池中取出一个连接
    public static Connection getConnection()
            throws SQLException
    {
        Connection pool = null;
        String status = "";
        // 遍历连接池，寻找空闲的连接
        while (pools.keySet().iterator().hasNext()) {
            pool = pools.keySet().iterator().next();
            status = pools.values().iterator().next();

            if (status == "0") {
                String re = pools.replace(pool, "1");
                return pool;
            }
        }
        // 连接池中没有空闲连接，则新建一个，并放入池中
        pool = DriverManager.getConnection(conn_url);
        pools.put(pool, "0");

        return pool;
    }
}
