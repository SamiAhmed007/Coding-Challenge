import java.time.LocalDate;

/**
 * Represents a single sales record (one line in the CSV).
 */
public class SalesRecord {

    private final String orderId;
    private final LocalDate date;
    private final String region;
    private final String product;
    private final String category;
    private final int quantity;
    private final double unitPrice;

    public SalesRecord(String orderId,
                       LocalDate date,
                       String region,
                       String product,
                       String category,
                       int quantity,
                       double unitPrice) {
        this.orderId = orderId;
        this.date = date;
        this.region = region;
        this.product = product;
        this.category = category;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() { return orderId; }
    public LocalDate getDate() { return date; }
    public String getRegion() { return region; }
    public String getProduct() { return product; }
    public String getCategory() { return category; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }

    public double getRevenue() {
        return quantity * unitPrice;
    }

    @Override
    public String toString() {
        return "SalesRecord{" +
                "orderId='" + orderId + '\'' +
                ", date=" + date +
                ", region='" + region + '\'' +
                ", product='" + product + '\'' +
                ", category='" + category + '\'' +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                '}';
    }
}