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

    public static void insertSandwichOrder (String code, String sanName, String breadName, int price) {

        Connection con = getConnection();

        PreparedStatement pstmt = null;

        int result = 0;

        Properties prop = new Properties();

        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/miniProject/subway/mapper/SandwichOrder-query.xml"));

            String query = prop.getProperty("insertSandwichOrder");

            pstmt = con.prepareStatement(query);
            pstmt.setString(1,code);
            pstmt.setString(2,sanName);
            pstmt.setString(3,breadName);
            pstmt.setInt(4,price);

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

}
