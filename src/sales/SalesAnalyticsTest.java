import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;


/**
 * Unit tests for all analysis methods in SalesAnalytics.
 */
public class SalesAnalyticsTest {

    private List<SalesRecord> sampleRecords() {
        return Arrays.asList(
                new SalesRecord("O1", LocalDate.of(2024, 1, 1),
                        "North", "Widget A", "Widgets", 10, 10.0), // 100
                new SalesRecord("O2", LocalDate.of(2024, 1, 1),
                        "South", "Widget B", "Widgets", 5, 20.0),  // 100
                new SalesRecord("O3", LocalDate.of(2024, 1, 2),
                        "North", "Widget A", "Widgets", 3, 10.0),  // 30
                new SalesRecord("O4", LocalDate.of(2024, 1, 2),
                        "East", "Widget C", "Gadgets", 2, 50.0),   // 100
                new SalesRecord("O5", LocalDate.of(2024, 1, 3),
                        "West", "Widget B", "Widgets", 1, 20.0)    // 20
        );
    }    

    @Test
    void totalRevenue_shouldSumAllRecordRevenues() {
        SalesAnalytics analytics = new SalesAnalytics();
        double total = analytics.totalRevenue(sampleRecords());
        assertEquals(350.0, total, 1e-6);
    }

    @Test
    void revenueByRegion_shouldGroupAndSumByRegion() {
        SalesAnalytics analytics = new SalesAnalytics();
        Map<String, Double> byRegion = analytics.revenueByRegion(sampleRecords());

        assertEquals(130.0, byRegion.get("North"), 1e-6); // 100 + 30
        assertEquals(100.0, byRegion.get("South"), 1e-6);
        assertEquals(100.0, byRegion.get("East"), 1e-6);
        assertEquals(20.0,  byRegion.get("West"), 1e-6);
    }

    @Test
    void revenueByProduct_shouldGroupAndSumByProduct() {
        SalesAnalytics analytics = new SalesAnalytics();
        Map<String, Double> byProduct = analytics.revenueByProduct(sampleRecords());

        assertEquals(130.0, byProduct.get("Widget A"), 1e-6);
        assertEquals(120.0, byProduct.get("Widget B"), 1e-6);
        assertEquals(100.0, byProduct.get("Widget C"), 1e-6);
    }

    @Test
void topNProductsByRevenue_shouldReturnProductsSortedByRevenueDesc() {
    SalesAnalytics analytics = new SalesAnalytics();
    List<Map.Entry<String, Double>> top2 =
            analytics.topNProductsByRevenue(sampleRecords(), 2);

    assertEquals(2, top2.size());
    assertEquals("Widget A", top2.get(0).getKey());
    assertEquals(130.0, top2.get(0).getValue(), 1e-6);
    assertEquals("Widget B", top2.get(1).getKey());
    assertEquals(120.0, top2.get(1).getValue(), 1e-6);
}


    @Test
    void averageOrderValue_shouldComputeAverageOfOrderTotals() {
        SalesAnalytics analytics = new SalesAnalytics();
        double avg = analytics.averageOrderValue(sampleRecords());
        // order totals: 100,100,30,100,20 → sum=350, count=5 → avg=70
        assertEquals(70.0, avg, 1e-6);
    }

    @Test
    void dailyRevenue_shouldGroupAndSumByDate() {
        SalesAnalytics analytics = new SalesAnalytics();
        Map<LocalDate, Double> daily = analytics.dailyRevenue(sampleRecords());

        assertEquals(200.0, daily.get(LocalDate.of(2024, 1, 1)), 1e-6);
        assertEquals(130.0, daily.get(LocalDate.of(2024, 1, 2)), 1e-6);
        assertEquals(20.0, daily.get(LocalDate.of(2024, 1, 3)), 1e-6);
    }

    @Test
    void unitsSoldByProduct_shouldSumQuantitiesPerProduct() {
        SalesAnalytics analytics = new SalesAnalytics();
        Map<String, Integer> units = analytics.unitsSoldByProduct(sampleRecords());

        assertEquals(13, units.get("Widget A").intValue()); // 10 + 3
        assertEquals(6,  units.get("Widget B").intValue()); // 5 + 1
        assertEquals(2,  units.get("Widget C").intValue());
    }

    public static void main(String[] args) {
        SalesAnalyticsTest test = new SalesAnalyticsTest();
        test.totalRevenue_shouldSumAllRecordRevenues();
        test.revenueByRegion_shouldGroupAndSumByRegion();
        test.revenueByProduct_shouldGroupAndSumByProduct();
        test.topNProductsByRevenue_shouldReturnProductsSortedByRevenueDesc();
        test.averageOrderValue_shouldComputeAverageOfOrderTotals();
        test.dailyRevenue_shouldGroupAndSumByDate();
        test.unitsSoldByProduct_shouldSumQuantitiesPerProduct();
        System.out.println("All tests passed!");
    }
}