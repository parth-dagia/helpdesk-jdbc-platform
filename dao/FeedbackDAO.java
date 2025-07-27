package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Feedback;
import util.DBUtil;

public class FeedbackDAO {

    public void addFeedback(Feedback feedback) {
        String sql = "INSERT INTO FEEDBACK (ticket_id, rating, comments) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, feedback.getTicketId());
            stmt.setInt(2, feedback.getRating());
            stmt.setString(3, feedback.getComments());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Feedback submitted for ticket #" + feedback.getTicketId());
            } else {
                System.out.println("❌ Failed to submit feedback.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
