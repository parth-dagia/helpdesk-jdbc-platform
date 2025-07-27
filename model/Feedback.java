package model;

public class Feedback {
    private int feedbackId;
    private int ticketId;
    private int rating;      // 1–5
    private String comments;

    public Feedback(int ticketId, int rating, String comments) {
        this.ticketId = ticketId;
        this.rating = rating;
        this.comments = comments;
    }

    public int getTicketId() { return ticketId; }
    public int getRating() { return rating; }
    public String getComments() { return comments; }
}
