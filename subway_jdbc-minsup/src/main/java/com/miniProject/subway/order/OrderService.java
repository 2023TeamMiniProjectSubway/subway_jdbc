package com.miniProject.subway.order;

import com.miniProject.subway.member.MemberDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.miniProject.subway.common.JDBCTemplate.close;
import static com.miniProject.subway.common.JDBCTemplate.getConnection;
import static com.miniProject.subway.order.OrderController.priceBasket;


public class OrderService {

    // 주문테이블에 회원아이디와 총금액 넣기
    public int insertMenu(String id) {
        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/order-query.xml"));
            String query = prop.getProperty("insertMenu");

            System.out.println(query);

            pstmt = con.prepareStatement(query);

            pstmt.setString(1,id );  // List에 정보가 담겨 있으니까 그 리스트에서 겟해야함.
//            System.out.println("현재 총 가격 : " + priceBasket());
            pstmt.setInt(2, priceBasket());

            result = pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }
        return result;
    }

    // 주문번호 찾기
    public String getOrderCode(){
        Connection con = getConnection();

        ResultSet rset = null;

        PreparedStatement pstmt = null;

        String result = "";

        Properties prop =  new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/getOrdercode_query.xml"));

            String query = prop.getProperty("getOrderCode");

            System.out.println(query);

            pstmt = con.prepareStatement(query);

            rset = pstmt.executeQuery();

            if(rset.next()){
                result = rset.getString("LPAD(MAX(ORDER_CODE),3,'0')");
                System.out.println(result);

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(rset);
            close(con);
        }
        return result;
    }
}
