package com.miniProject.subway.order;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import static com.miniProject.subway.common.JDBCTemplate.close;
import static com.miniProject.subway.common.JDBCTemplate.getConnection;

public class OrderSandwichService {

    public static void insertSandwichOrder (String sandwichcode, String ordercode, String sanName, String breadName, int price) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/SandwichOrder-query.xml"));

            String query = prop.getProperty("insertSandwichOrder");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,sandwichcode);
            pstmt.setString(2,ordercode);
            pstmt.setString(3,sanName);
            pstmt.setString(4, breadName);
            pstmt.setInt(5,price);

            result = pstmt.executeUpdate();


        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(pstmt);
            close(con);
        }

    }

    public static void insertSandwichOption(String sandwichCode, String OptionCode, String topping){

        Connection con = getConnection();
        PreparedStatement pstmt = null;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/SandwichOption-query.xml"));
            String query = prop.getProperty("insertOption");
            pstmt = con.prepareStatement(query);

            pstmt.setString(1, sandwichCode);
            pstmt.setString(2, OptionCode);
            pstmt.setString(3, topping);

            pstmt.executeUpdate();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstmt);
            close(con);
        }
    }

}
