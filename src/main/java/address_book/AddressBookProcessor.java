package address_book;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddressBookProcessor {
    private static final String COMMA = ",";

    private final Path path;

    public AddressBookProcessor(final String fileLocation) {
        path = Paths.get(fileLocation);

        if (!Files.exists(path)) {
            throw new ProcessingException("File not found: " + path.toString());
        }
    }

    public Long count(final Predicate<String> p, final Column column) {
        return process(reader -> reader.lines()
            .map(line -> cellValueOf(column, line))
            .filter(p)
            .count());
    }

    public String findOldest() {
        return process(reader -> reader.lines()
            .map(line -> splitTrim(line))
            .min(Comparator.comparing(this::toDate))
            .map(line -> line.toString().replaceAll("[\\[\\]]", ""))
            .orElse("No oldest person found. Wrong or empty file."));
    }

    public long getAgeDifferenceInDays(final String person1, final String person2) {
        final List<LocalDateTime> dates = process(reader -> reader.lines()
            .map(line -> splitTrim(line))
            .filter(line -> line.get(Column.NAME.index()).equals(person1) || line.get(Column.NAME.index()).equals(person2))
            .map(line -> toDate(line).atStartOfDay())
            .collect(Collectors.toCollection(ArrayList<LocalDateTime>::new))
        );

        long daysDiff = dates.size() >= 2 ? Duration.between(dates.get(0), dates.get(1)).toDays() : Long.MAX_VALUE;

        return Math.abs(daysDiff);
    }

    private <R> R process(Function<BufferedReader, R> function) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return function.apply(br);
        } catch (IOException e) {
            throw new ProcessingException(e);
        }
    }

    private List<String> splitTrim(final String line) {
        final ArrayList<String> trimmed = Arrays.stream(line.split(COMMA))
            .map(e -> e.trim())
            .collect(Collectors.toCollection(ArrayList<String>::new));

        return trimmed;
    }

    private LocalDate toDate(final List<String> line) {
        return LocalDate.parse(line.get(Column.DATE.index()).trim(), DateTimeFormatter.ofPattern("dd/MM/yy"));
    }

    private String cellValueOf(final Column column, final String line) {
        return splitTrim(line).get(column.index());
    }
}
