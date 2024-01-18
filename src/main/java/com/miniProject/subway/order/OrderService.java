package com.miniProject.subway.order;

import com.miniProject.subway.member.MemberDTO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import static com.miniProject.subway.common.JDBCTemplate.close;
import static com.miniProject.subway.common.JDBCTemplate.getConnection;
import static com.miniProject.subway.order.OrderController.priceBasket;


public class OrderService {

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
}
