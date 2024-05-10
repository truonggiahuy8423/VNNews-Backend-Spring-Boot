package com.server.vnnews.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TestController {
    @GetMapping("get")
    public List<Integer> getAll() {
        // Thông tin kết nối MySQL
        String url = "jdbc:mysql://localhost:3306/vnnewsdb"; // Thay localhost, mydatabase, username và password bằng thông tin tương ứng của bạn
        String username = "admin";
        String password = "12345678";

        // Tạo đối tượng Connection
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            System.out.println("Kết nối đến MySQL thành công!");

            // Tạo đối tượng Statement để thực thi các truy vấn SQL
            Statement statement = conn.createStatement();

            // Thực thi truy vấn SELECT
            String sqlQuery = "SELECT * FROM tb"; // Thay mytable bằng tên bảng của bạn
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            ArrayList<Integer> ls = new ArrayList<>();
            // In ra kết quả của truy vấn SELECT
            while (resultSet.next()) {
                ls.add(resultSet.getInt("id"));

            }
            return ls;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối MySQL: " + e.getMessage());
            return null;
        }
    }
}
