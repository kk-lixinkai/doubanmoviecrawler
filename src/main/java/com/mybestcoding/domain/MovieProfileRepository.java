package com.mybestcoding.domain;

import com.mybestcoding.jdbc.DataConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: lixinkai
 * @description:
 * @date: 2021/6/7 10:28
 * @GitHub: https://github.com/kk-lixinkai
 * @Gitee: https://gitee.com/bestbug
 * @version: 1.0
 */
public class MovieProfileRepository {
    public static final String MOVIE_PROFILE_SELECT = "SELECT url FROM movieprofile";
    public static final String MOVIE_PROFILE_INSERT = "INSERT INTO movieprofile(episodes_info,rate,cover_x,title,url,playable,cover,id,cover_y,is_new)VALUES(?,?,?,?,?,?,?,?,?,?)";
    static PreparedStatement ps = null;
    static ResultSet rs = null;


    /**
     * 查询所有数据
     *
     * @return
     * @throws SQLException
     */
    public static List<String> selectAll() throws SQLException {
        ps = DataConnection.getConnection().prepareStatement(MOVIE_PROFILE_SELECT);
        rs = ps.executeQuery();
        List<String> urls = new ArrayList<>();
        while (rs.next()) {
            String url = rs.getString("url");
            urls.add(url);
        }
        release();
        return urls;
    }


    /**
     * 插入数据
     *
     * @param movieProfiles
     * @throws SQLException
     */
    public static void insert(List<MovieProfile> movieProfiles) throws SQLException {
        ps = DataConnection.getConnection().prepareStatement(MOVIE_PROFILE_INSERT);
        for (MovieProfile movieProfile : movieProfiles) {
            ps.setString(1, movieProfile.getEpisodes_info());
            ps.setString(2, movieProfile.getRate());
            ps.setInt(3, movieProfile.getCover_x());
            ps.setString(4, movieProfile.getTitle());
            ps.setString(5, movieProfile.getUrl());
            ps.setBoolean(6, movieProfile.isPlayable());
            ps.setString(7, movieProfile.getCover());
            ps.setString(8, movieProfile.getId());
            ps.setInt(9, movieProfile.getCover_y());
            ps.setBoolean(10, movieProfile.isIs_new());
            ps.execute();
        }
        release();
    }


    /**
     * 释放资源
     *
     * @throws SQLException
     */
    public static void release() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        DataConnection.close();
    }


}
