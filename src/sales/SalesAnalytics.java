import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Provides analytical operations over a list of SalesRecord using Java Streams.
 */
public class SalesAnalytics {

    public double totalRevenue(List<SalesRecord> records) {
        return records.stream()
                .mapToDouble(SalesRecord::getRevenue)
                .sum();
    }

    public Map<String, Double> revenueByRegion(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getRegion,
                        Collectors.summingDouble(SalesRecord::getRevenue)
                ));
    }

    public Map<String, Double> revenueByProduct(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProduct,
                        Collectors.summingDouble(SalesRecord::getRevenue)
                ));
    }

    public List<Map.Entry<String, Double>> topNProductsByRevenue(List<SalesRecord> records, int n) {
        return revenueByProduct(records).entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue(Comparator.reverseOrder()))
                .limit(n)
                .collect(Collectors.toList());
    }

    public double averageOrderValue(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getOrderId,
                        Collectors.summingDouble(SalesRecord::getRevenue)
                ))
                .values().stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);
    }

    public Map<LocalDate, Double> dailyRevenue(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getDate,
                        Collectors.summingDouble(SalesRecord::getRevenue)
                ));
    }

    public Map<String, Integer> unitsSoldByProduct(List<SalesRecord> records) {
        return records.stream()
                .collect(Collectors.groupingBy(
                        SalesRecord::getProduct,
                        Collectors.summingInt(SalesRecord::getQuantity)
                ));
    }
}