package model;

import java.sql.Timestamp;

public class Ticket {
    private int ticketId;
    private int customerId;
    private int categoryId;
    private int agentId;
    private String priority;     // 'low', 'medium', 'high', 'urgent'
    private String title;
    private String description;
    private String status;       // 'open', 'in_progress', etc.
    private Timestamp createdAt;

    public Ticket(int customerId, int categoryId, int agentId, String priority, String title, String description, String status) {
        this.customerId = customerId;
        this.categoryId = categoryId;
        this.agentId = agentId;
        this.priority = priority;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // Getters
    public int getCustomerId() { return customerId; }
    public int getCategoryId() { return categoryId; }
    public int getAgentId() { return agentId; }
    public String getPriority() { return priority; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getStatus() { return status; }
}
