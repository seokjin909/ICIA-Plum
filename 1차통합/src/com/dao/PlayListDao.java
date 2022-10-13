package com.dao;

import com.dto.MemberDto;
import com.dto.MusicDto;
import com.dto.PlayListDto;

import java.sql.*;

public class PlayListDao {

    private String drv = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/plum";
    private String user = "dev01";
    private String pwd = "12341234";

    // DB 관련 객체
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    // 드라이버 로드(생성자)
    public PlayListDao() {
        try {
            Class.forName(drv);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
        }
    }

    public int insertMusicData(MusicDto mData, MemberDto profil) {
        int result = 0;
        String query = "INSERT INTO playlist VALUES (DEFAULT,?,?)";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id());
            pstmt.setInt(2, mData.getM_code());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public MusicDto getFirstMusic(PlayListDto pList, MemberDto profil) {
        MusicDto music = null;
        String query = "SELECT MS.m_code, m_title, m_singer, M.m_id FROM playList P JOIN member M ON P.m_id = M.m_id JOIN Music MS ON P.m_code = MS.m_code WHERE M.m_id = ?";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id());
            rs = pstmt.executeQuery();

            while (rs.next()){
                music = new MusicDto();
                music.setM_title(rs.getString(2));
                music.setM_singer(rs.getString(3));
            }
        } catch (SQLException e) {
            music = null;
        } finally {
            close();
        }
       return music;
    }
}
