package com.dao;

import com.dto.MemberDto;
import com.dto.MusicDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MusicDao {

    private String drv = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/plum";
    private String user = "dev01";
    private String pwd = "12341234";

    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public MusicDao() {
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

    public List<MusicDto> getSearchMusicList(String searchIndex) {
        List<MusicDto> mList = new ArrayList<>();
        String query = "SELECT * FROM Music WHERE m_singer LIKE ? OR m_title LIKE ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            searchIndex = "%" + searchIndex + "%";
            pstmt.setString(1, searchIndex);
            pstmt.setString(2, searchIndex);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                if (mList == null){
                    mList = new ArrayList<>();
                }
                MusicDto mData = new MusicDto();
                mData.setM_code(rs.getInt(1));
                mData.setM_title(rs.getString(2));
                mData.setM_singer(rs.getString(3));
                mData.setM_date(rs.getString(4));
                mData.setM_album(rs.getString(5));
                mList.add(mData);
            }
        } catch (SQLException e) {
            mList = null;
        } finally {
            close();
        }
        return mList;
    }


    public int insertMusicData(MusicDto mData) {
        int result = 0;
        String query = "INSERT INTO Music VALUES (DEFAULT,?,?,?,?)";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, mData.getM_title());
            pstmt.setString(2, mData.getM_singer());
            pstmt.setDate(3, Date.valueOf(mData.getM_date()));
            pstmt.setString(4, mData.getM_album());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public MusicDto getSearchMusicData(int code) {
        MusicDto mData = null;
        String query = "SELECT * FROM Music WHERE m_code = ?";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, code);
            rs = pstmt.executeQuery();
            if (rs.next()){
                mData = new MusicDto();
                mData.setM_code(rs.getInt(1));
                mData.setM_title(rs.getString(2));
                mData.setM_singer(rs.getString(3));
                mData.setM_date(rs.getString(4));
                mData.setM_album(rs.getString(5));
            }

        } catch (SQLException e) {
            mData = null;
        } finally {
            close();
        }
        return mData;
    }

    public int updateMusicData(MusicDto mData) {
        int result = 0;
        String query = "UPDATE Music SET m_title = ?, m_singer = ?, m_date = ?, m_album = ? WHERE m_code = ?";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1,mData.getM_title());
            pstmt.setString(2,mData.getM_singer());
            pstmt.setDate(3, Date.valueOf(mData.getM_date()));
            pstmt.setString(4,mData.getM_album());
            pstmt.setInt(5,mData.getM_code());
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public int deleteMusicData(int code) {
        int result = 0;
        String query = "DELETE FROM Music WHERE m_code = ?";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, code);
            result = pstmt.executeUpdate();
        } catch (SQLException e) {
            result = 0;
        } finally {
            close();
        }
        return result;
    }

    public List<MusicDto> getMusicList() {
        // DB에서 테이블의 내용을 모두 가져와서 List(목록)에 저장
        // 저장하기에 앞서 데이터를 담을 공간을 생성
        List<MusicDto> mList = new ArrayList<>();

        // 전체 데이터를 가져오는 쿼리문 작성
        String query = "SELECT * FROM Music";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()){
                if (mList == null) {
                    mList = new ArrayList<>();
                }
                MusicDto mData = new MusicDto();
                mData.setM_code(rs.getInt(1));
                mData.setM_title(rs.getString(2));
                mData.setM_singer(rs.getString(3));
                mData.setM_date(rs.getString(4));
                mData.setM_album(rs.getString(5));
                mList.add(mData);
            }
        } catch (SQLException e) {
            mList = null;
        } finally {
            close();
        }
        return mList;
    }

    public List<MusicDto> getLatestMusicList() {
        // DB에서 테이블의 내용을 모두 가져와서 List(목록)에 저장
        // 저장하기에 앞서 데이터를 담을 공간을 생성
        List<MusicDto> mList = new ArrayList<>();

        // 전체 데이터를 가져오는 쿼리문 작성
        String query = "SELECT * FROM Music ORDER BY m_date DESC LIMIT 0,10";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()){
                if (mList == null) {
                    mList = new ArrayList<>();
                }
                MusicDto mData = new MusicDto();
                mData.setM_code(rs.getInt(1));
                mData.setM_title(rs.getString(2));
                mData.setM_singer(rs.getString(3));
                mData.setM_date(rs.getString(4));
                mData.setM_album(rs.getString(5));
                mList.add(mData);
            }
        } catch (SQLException e) {
            mList = null;
        } finally {
            close();
        }
        return mList;
    }

    public List<MusicDto> getPopularMusicList() {
        List<MusicDto> mList = new ArrayList<>();
        String query = "SELECT P.m_code, count(*), m_title, m_singer, m_date, m_album FROM playlist P\n" +
                "JOIN Music M ON P.m_code = M.m_code\n" +
                "GROUP BY P.m_code\n" +
                "ORDER BY count(*) DESC LIMIT 0, 10";

        try {
            conn = DriverManager.getConnection(url,user,pwd);
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()){
                if (mList == null) {
                    mList = new ArrayList<>();
                }
                MusicDto mData = new MusicDto();
                mData.setM_code(rs.getInt(1));
                mData.setM_title(rs.getString(3));
                mData.setM_singer(rs.getString(4));
                mData.setM_date(rs.getString(5));
                mData.setM_album(rs.getString(6));
                mList.add(mData);
            }
        } catch (SQLException e) {
            mList = null;
        } finally {
            close();
        }
        return mList;
    }

    public List<MusicDto> showPlayList(MemberDto profil) {
        List<MusicDto> mList = new ArrayList<>();
        String query = "SELECT DISTINCT m_title, m_singer, m_album FROM music " +
                "JOIN playlist ON playlist.m_code = music.m_code " +
                "WHERE playlist.m_id = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, profil.getM_id());
            rs = pstmt.executeQuery();

            while (rs.next()){
                if(mList == null){
                    mList = new ArrayList<>();
                }
                MusicDto mDto = new MusicDto();
                mDto.setM_title(rs.getString(1));
                mDto.setM_singer(rs.getString(2));
                mDto.setM_album(rs.getString(3));
                mList.add(mDto);
            }
        } catch (SQLException e) {
            mList = null;
        } finally {
            close();
        }

        return mList;
    }
}
