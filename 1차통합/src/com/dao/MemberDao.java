package com.dao;

import com.dto.AdminDto;
import com.dto.MemberDto;
import java.sql.*;
import java.util.InputMismatchException;

public class MemberDao {

    private String drv = "com.mysql.cj.jdbc.Driver";
    private String url = "jdbc:mysql://localhost:3306/plum";
    private String user = "dev01";
    private String pwd = "12341234";

    private Connection conn;
    private PreparedStatement pstmt;
    private PreparedStatement pstmt2;
    private ResultSet rs;

    public MemberDao(){
        try {
            Class.forName(drv);
        } catch (ClassNotFoundException e) {
            System.out.println("드라이브 로드 실패");
        }
    }

    private void close(){
        try{
            if(rs != null) rs.close();
            if(pstmt != null) pstmt.close();
            if(conn != null) conn.close();
        } catch(SQLException e) { }
    }


    public int insertData(MemberDto memData) {
        MemberDto ck_mDto = null;
        int result = 0;

        String query1 = "SELECT m_id FROM member WHERE m_id = ?";
        String query2 = "INSERT INTO member VALUES (?, ?, ?, ?)";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query1);
            pstmt.setString(1, memData.getM_id());
            rs = pstmt.executeQuery();

            if (rs.next()) {
                result = -1;

            } else {
                pstmt2 = conn.prepareStatement(query2);
                pstmt2.setString(1, memData.getM_id());
                pstmt2.setString(2, memData.getM_pwd());
                pstmt2.setString(3, memData.getM_name());
                pstmt2.setInt(4, memData.getM_age());
                result = pstmt2.executeUpdate();
            }

        } catch (SQLException e) {
            result = 0;

        } finally {
            close();
        }
        return result;
    }

    /*
    public int insertData(MemberDto memData) {

        int result = 0;

        String query2 = "INSERT INTO member VALUES (?, ?, ?, ?)";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt2 = conn.prepareStatement(query2);

            pstmt2 = conn.prepareStatement(query2);
            pstmt2.setString(1, memData.getM_id());
            pstmt2.setString(2, memData.getM_pwd());
            pstmt2.setString(3, memData.getM_name());
            pstmt2.setInt(4, memData.getM_age());
            result = pstmt2.executeUpdate();

        }
        catch(SQLIntegrityConstraintViolationException e) {
            result = -1;
        }
        catch (SQLException e){
            result = 0;
        }
            
        finally {
            close();
        }
        return result;
    }
     */

    public MemberDto loginData(MemberDto memData) {
        MemberDto mData = null;

        String query = "SELECT * FROM member WHERE m_id = ? AND m_pwd = ?";

        try {

            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memData.getM_id());
            pstmt.setString(2, memData.getM_pwd());
            rs = pstmt.executeQuery();

            if (rs.next()){
                mData = new MemberDto();
                mData.setM_id(rs.getString(1));
                mData.setM_pwd(rs.getString(2));
                mData.setM_name(rs.getString(3));
                mData.setM_age(rs.getInt(4));

            }

        } catch (SQLException e) {
            mData = null;
        }finally {
            close();
        }

        return mData;

    }

    public MemberDto findPwData(MemberDto memData) {
        MemberDto mData = null;

        String query = "SELECT * FROM member WHERE m_id = ? AND m_name = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, memData.getM_id());
            pstmt.setString(2, memData.getM_name());
            rs = pstmt.executeQuery();

            if(rs.next()){
                mData = new MemberDto();
                mData.setM_pwd(rs.getString(2));
            }

        } catch (SQLException e) {
            mData = null;
        } finally {
            close();
        }
        return mData;
    }


    public AdminDto mLogiData(AdminDto aDto) {

        AdminDto aData = null;

        String query = "SELECT * FROM admin WHERE a_id = ? AND a_pwd = ?";

        try {
            conn = DriverManager.getConnection(url, user, pwd);
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, aDto.getA_id());
            pstmt.setString(2, aDto.getA_pwd());
            rs = pstmt.executeQuery();

            if(rs.next()){
                aData = new AdminDto();
                aData.setA_id(rs.getString(1));
                aData.setA_pwd(rs.getString(2));

            }
        } catch (SQLException e) {
            aData = null;
        } finally {
            close();
        }
        return aData;

    }
} // DAO end

