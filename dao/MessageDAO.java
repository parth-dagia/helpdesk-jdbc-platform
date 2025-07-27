package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Message;
import util.DBUtil;

public class MessageDAO {

    public void addMessage(Message message) {
        String sql = "INSERT INTO MESSAGE (ticket_id, sender_type, sender_id, content) VALUES (?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, message.getTicketId());
            stmt.setString(2, message.getSenderType());
            stmt.setInt(3, message.getSenderId());
            stmt.setString(4, message.getContent());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Message added successfully!");
            } else {
                System.out.println("Failed to add message.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Message> getMessagesByTicketId(int ticketId) {
        List<Message> messages = new ArrayList<>();
        String sql = "SELECT * FROM MESSAGE WHERE ticket_id = ? ORDER BY sent_at";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Message msg = new Message(
                    rs.getInt("ticket_id"),
                    rs.getString("sender_type"),
                    rs.getInt("sender_id"),
                    rs.getString("content")
                );
                messages.add(msg);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return messages;
    }
}
