package model;

import java.sql.Timestamp;

public class Message {
    private int messageId;
    private int ticketId;
    private String senderType;  // 'CUSTOMER' or 'AGENT'
    private int senderId;
    private String content;
    private Timestamp sentAt;

    public Message(int ticketId, String senderType, int senderId, String content) {
        this.ticketId = ticketId;
        this.senderType = senderType;
        this.senderId = senderId;
        this.content = content;
    }

    public int getTicketId() { return ticketId; }
    public String getSenderType() { return senderType; }
    public int getSenderId() { return senderId; }
    public String getContent() { return content; }
}
