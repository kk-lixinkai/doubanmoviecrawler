package com.mybestcoding.domain;

import com.mybestcoding.jdbc.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinkai
 * @description:
 * @date: 2021/6/8 16:18
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class MovieInfoRepository {
    private final static String SELECT = "SELECT * FROM movieinfo";
    private final static String INSERT = "INSERT INTO movieinfo(title, director, writers, actor, type, score, number)VALUES(?, ?, ?, ?, ?, ?, ?)";
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    public static void insert(List<MovieInfo> movieInfos) throws SQLException {
        Connection connection = DataConnection.getConnection();
        connection.setAutoCommit(false);
        ps = connection.prepareStatement(INSERT);
        for (MovieInfo movieInfo : movieInfos) {
            ps.setString(1, movieInfo.getTitle());
            ps.setString(2, movieInfo.getDirector());
            ps.setString(3, movieInfo.getWriters());
            ps.setString(4, movieInfo.getActor());
            ps.setString(5, movieInfo.getType());
            ps.setString(6, movieInfo.getScore());
            ps.setString(7, movieInfo.getNumber());
            ps.addBatch();
        }
        ps.executeBatch();
        connection.commit();
        release();
    }

    public static List<MovieInfo> selectAll() throws SQLException {
        List<MovieInfo> movieInfos = new ArrayList<>();
        ps = DataConnection.getConnection().prepareStatement(SELECT);
        rs = ps.executeQuery();
        while (rs.next()) {
            String title = rs.getString("title");
            String director = rs.getString("director");
            String writers = rs.getString("writers");
            String actor = rs.getString("actor");
            String type = rs.getString("type");
            String score = rs.getString("score");
            String number = rs.getString("number");
            movieInfos.add(new MovieInfo(title, director, writers, actor, type, score, number));
        }
        return movieInfos;
    }

    /**
     * 释放资源
     *
     * @throws SQLException
     */
    private static void release() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        DataConnection.close();
    }
}
