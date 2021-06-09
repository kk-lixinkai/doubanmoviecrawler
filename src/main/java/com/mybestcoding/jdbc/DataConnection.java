package com.mybestcoding.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author: lixinkai
 * @description: 数据库连接类
 * @date: 2021/6/6 21:23
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class DataConnection {
    private static final String user = "root";
    private static final String password = "lixinkai";
    private static final String url = "jdbc:mysql://localhost:3306/crawler?useUnicode=true&characterEncoding=utf8&serverTimezone=Asia/Shanghai";
    private static Connection connection = null;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        connection = DriverManager.getConnection(url, user, password);
        return connection;
    }

    /**
     * 关闭数据库连接
     *
     * @throws SQLException
     */
    public static void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }
}
