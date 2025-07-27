package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Escalation;
import util.DBUtil;

public class EscalationDAO {

    public void addEscalation(Escalation escalation) {
        String sql = "INSERT INTO ESCALATION (ticket_id, reason) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, escalation.getTicketId());
            stmt.setString(2, escalation.getReason());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Escalation recorded for ticket #" + escalation.getTicketId());
            } else {
                System.out.println("Failed to record escalation.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
