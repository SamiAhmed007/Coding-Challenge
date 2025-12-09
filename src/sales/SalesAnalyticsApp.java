import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Entry point for running the sales analytics on a CSV file.
 */
public class SalesAnalyticsApp {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: java SalesAnalyticsApp <path_to_sales.csv_file>");
            System.exit(1);
        }

        Path csvPath = Paths.get(args[0]);

        SalesCsvReader reader = new SalesCsvReader();
        List<SalesRecord> records;
        try {
            records = reader.read(csvPath);
        } catch (IOException e) {
            System.err.println("Failed to read CSV file: " + e.getMessage());
            return;
        }

        SalesAnalytics analytics = new SalesAnalytics();

        double totalRevenue = analytics.totalRevenue(records);
        Map<String, Double> revenueByRegion = analytics.revenueByRegion(records);
        Map<String, Double> revenueByProduct = analytics.revenueByProduct(records);
        List<Map.Entry<String, Double>> top3Products =
                analytics.topNProductsByRevenue(records, 3);
        double avgOrderValue = analytics.averageOrderValue(records);
        Map<LocalDate, Double> dailyRevenue = analytics.dailyRevenue(records);
        Map<String, Integer> unitsSoldByProduct =
                analytics.unitsSoldByProduct(records);

        System.out.println("=== Sales Analytics Results ===\n");

        System.out.printf("Total revenue: %.2f%n%n", totalRevenue);

        System.out.println("Revenue by region:");
        revenueByRegion.forEach((region, revenue) ->
                System.out.printf("  %-10s : %.2f%n", region, revenue));
        System.out.println();

        System.out.println("Revenue by product:");
        revenueByProduct.forEach((product, revenue) ->
                System.out.printf("  %-10s : %.2f%n", product, revenue));
        System.out.println();

        System.out.println("Top 3 products by revenue:");
        top3Products.forEach(entry ->
                System.out.printf("  %-10s : %.2f%n", entry.getKey(), entry.getValue()));
        System.out.println();

        System.out.printf("Average order value: %.2f%n%n", avgOrderValue);

        System.out.println("Daily revenue:");
        dailyRevenue.forEach((LocalDate date, Double revenue) ->
                System.out.printf("  %s : %.2f%n", date, revenue));
        System.out.println();

        System.out.println("Units sold by product:");
        unitsSoldByProduct.forEach((product, units) ->
                System.out.printf("  %-10s : %d%n", product, units));
    }
}