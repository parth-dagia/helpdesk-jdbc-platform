package model;

public class SLA {
    private int slaId;
    private int categoryId;
    private int maxResponseTime;     // in minutes
    private int maxResolutionTime;   // in minutes
    private String slaName;          // 'Standard', 'Premium', 'VIP'

    public SLA(int categoryId, int maxResponseTime, int maxResolutionTime, String slaName) {
        this.categoryId = categoryId;
        this.maxResponseTime = maxResponseTime;
        this.maxResolutionTime = maxResolutionTime;
        this.slaName = slaName;
    }

    public int getCategoryId() { return categoryId; }
    public int getMaxResponseTime() { return maxResponseTime; }
    public int getMaxResolutionTime() { return maxResolutionTime; }
    public String getSlaName() { return slaName; }
}
