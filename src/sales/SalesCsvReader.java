import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Reads SalesRecord data from a CSV file.
 */
public class SalesCsvReader {

    public List<SalesRecord> read(Path path) throws IOException {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .skip(1) // skip header
                    // .filter(line -> !line.isBlank())
                    .map(this::parseLine)
                    .collect(Collectors.toList());
        }
    }

    private SalesRecord parseLine(String line) {
        String[] parts = line.split(",", -1);
        if (parts.length < 7) {
            throw new IllegalArgumentException("Invalid line: " + line);
        }

        String orderId = parts[0].trim();
        LocalDate date = LocalDate.parse(parts[1].trim());
        String region = parts[2].trim();
        String product = parts[3].trim();
        String category = parts[4].trim();
        int quantity = Integer.parseInt(parts[5].trim());
        double unitPrice = Double.parseDouble(parts[6].trim());

        return new SalesRecord(orderId, date, region, product, category, quantity, unitPrice);
    }
}