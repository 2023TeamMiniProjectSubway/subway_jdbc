package com.miniProject.subway.member;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static com.miniProject.subway.common.JDBCTemplate.close;
import static com.miniProject.subway.common.JDBCTemplate.getConnection;

public class MemberService {

    public void insertMember(String id, String pwd, String name, String email, String phone){
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/member-query.xml"));
            String query = prop.getProperty("insertMember");

            System.out.println(query);

            pstmt = con.prepareStatement(query);

            pstmt.setString(1, id);
            pstmt.setString(2, pwd);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, phone);

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }

        if(result > 0){
            System.out.println("멤버 등록 성공! ");
        }
        else{
            System.out.println("멤버 등록 실패! ");
        }

    }

    public void showMember(){

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        ResultSet rset = null;

        Properties prop = new Properties();
        List<MemberDTO> arr = new ArrayList<>();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/member-query.xml"));

            String query = prop.getProperty("selectMember");

            System.out.println(query);

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            while(rset.next()){
                System.out.println("회원 ID : " + rset.getString("MEMBER_ID")
                                + "| 회원 비밀번호 : " + rset.getString("MEMBER_PASSWORD")
                                + "| 회원 이름 : " + rset.getString("MEMBER_NAME")
                                + "| 회원 이메일 : " + rset.getString("EMAIL")
                                + "| 회원 연락처 : " + rset.getString("PHONE")
                                + "| 회원 포인트 : " + rset.getString("POINT"));

            }



        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            close(rset);
            close(pstmt);
            close(con);
        }


    }


}


