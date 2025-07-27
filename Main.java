import dao.CustomerDAO;
import dao.EscalationDAO;
import dao.FeedbackDAO;
import dao.MessageDAO;
import dao.SLADAO;
import dao.TicketDAO;
import java.sql.*;
import java.util.List;
import model.Customer;
import model.Escalation;
import model.Feedback;
import model.Message;
import model.SLA;
import model.Ticket;
import util.DBUtil;

public class Main {
    public static void main(String[] args) {
        // 🔹 Step 1: Insert a customer
        Customer customer = new Customer("Alice", "9876543210", "alice@example.com");
        CustomerDAO customerDao = new CustomerDAO();
        customerDao.addCustomer(customer);

        // 🔹 Step 2: Insert a ticket
        int customerId = 1;
        int categoryId = 1;
        int agentId = 1;

        Ticket ticket = new Ticket(
            customerId,
            categoryId,
            agentId,
            "high",
            "Unable to access portal",
            "Customer can't log in to the helpdesk portal.",
            "open"
        );

        TicketDAO ticketDao = new TicketDAO();
        ticketDao.createTicket(ticket);

        // 🔹 Step 3: Add messages to the ticket
        Message message1 = new Message(1, "CUSTOMER", 1, "I need help logging in.");
        Message message2 = new Message(1, "AGENT", 1, "Sure! I can help you reset your password.");

        MessageDAO messageDao = new MessageDAO();
        messageDao.addMessage(message1);
        messageDao.addMessage(message2);

        // 🔹 Step 4: View conversation history
        System.out.println("\nConversation history for Ticket #1:");
        List<Message> messages = messageDao.getMessagesByTicketId(1);
        for (Message msg : messages) {
            System.out.println(msg.getSenderType() + " (" + msg.getSenderId() + "): " + msg.getContent());
        }

        // 🔹 Step 5: Insert SLA for the ticket's category
        SLA sla = new SLA(
            categoryId,
            30,     // Max response time (minutes)
            120,    // Max resolution time (minutes)
            "Standard"
        );

        SLADAO slaDao = new SLADAO();
        slaDao.addSLA(sla);

        // 🔹 Step 6: Check if ticket breached SLA and escalate
        try (Connection conn = DBUtil.getConnection()) {
            String getTicketTimeSQL = "SELECT created_at FROM TICKET WHERE ticket_id = ?";
            PreparedStatement ticketStmt = conn.prepareStatement(getTicketTimeSQL);
            ticketStmt.setInt(1, 1);
            ResultSet rs = ticketStmt.executeQuery();

            if (rs.next()) {
                Timestamp createdAt = rs.getTimestamp("created_at");

                SLADAO slaDao2 = new SLADAO();
                SLA slaDetails = slaDao2.getSLAByCategoryId(1);

                if (slaDetails != null) {
                    int maxMinutes = slaDetails.getMaxResolutionTime();
                    long maxMillis = createdAt.getTime() + (maxMinutes * 60L * 1000);
                    Timestamp slaDeadline = new Timestamp(maxMillis);
                    Timestamp now = new Timestamp(System.currentTimeMillis());

                    if (now.after(slaDeadline)) {
                        System.out.println("\n⛔ Ticket has breached SLA!");

                        Escalation escalation = new Escalation(1, "Resolution time exceeded SLA.");
                        EscalationDAO escalationDao = new EscalationDAO();
                        escalationDao.addEscalation(escalation);
                    } else {
                        System.out.println("\n✅ Ticket is still within SLA limits.");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 🔹 Step 7: Submit Feedback for Ticket #1
        Feedback feedback = new Feedback(
            1,
            5,
            "Very quick and helpful response. Thanks!"
        );

        FeedbackDAO feedbackDao = new FeedbackDAO();
        feedbackDao.addFeedback(feedback);
    }
}
