package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.SLA;
import util.DBUtil;

public class SLADAO {

    public void addSLA(SLA sla) {
        String sql = "INSERT INTO SLA (category_id, max_response_time, max_resolution_time, sla_name) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, sla.getCategoryId());
            stmt.setInt(2, sla.getMaxResponseTime());
            stmt.setInt(3, sla.getMaxResolutionTime());
            stmt.setString(4, sla.getSlaName());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("SLA inserted successfully!");
            } else {
                System.out.println("Failed to insert SLA.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SLA getSLAByCategoryId(int categoryId) {
        String sql = "SELECT * FROM SLA WHERE category_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, categoryId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new SLA(
                    rs.getInt("category_id"),
                    rs.getInt("max_response_time"),
                    rs.getInt("max_resolution_time"),
                    rs.getString("sla_name")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
