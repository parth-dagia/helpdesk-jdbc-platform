package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Ticket;
import util.DBUtil;

public class TicketDAO {

    public void createTicket(Ticket ticket) {
        String sql = "INSERT INTO TICKET (customer_id, category_id, agent_id, priority, title, description, status) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ticket.getCustomerId());
            stmt.setInt(2, ticket.getCategoryId());
            stmt.setInt(3, ticket.getAgentId());
            stmt.setString(4, ticket.getPriority());
            stmt.setString(5, ticket.getTitle());
            stmt.setString(6, ticket.getDescription());
            stmt.setString(7, ticket.getStatus());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Ticket created successfully!");
            } else {
                System.out.println("Failed to create ticket.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
