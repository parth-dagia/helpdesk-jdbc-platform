package model;

import java.sql.Timestamp;

public class Escalation {
    private int escalationId;
    private int ticketId;
    private Timestamp escalatedOn;
    private String reason;

    public Escalation(int ticketId, String reason) {
        this.ticketId = ticketId;
        this.reason = reason;
    }

    public int getTicketId() { return ticketId; }
    public String getReason() { return reason; }
}
