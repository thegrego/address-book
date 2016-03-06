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
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class AddressBookProcessor {
    private static final String COMMA = ",";

    private final Path path;

    public AddressBookProcessor(final String fileLocation) {
        path = Paths.get(fileLocation);

        if(!Files.exists(path)) {
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
            .min((d1, d2) -> toDate(d1).compareTo(toDate(d2)))
            .get());
    }

    public long getAgeDifferenceInDays(final String person1, final String person2) {
        LocalDateTime[] dates = process(reader -> reader.lines()
            .filter(line -> cellValueOf(Column.NAME, line).equals(person1) || cellValueOf(Column.NAME, line).equals(person2))
            .map(line -> toDate(line).atStartOfDay())
            .collect(Collectors.toSet())
            .toArray(new LocalDateTime[0])
        );

        long daysDiff = Duration.between(dates[0], dates[1]).toDays();

        return Math.abs(daysDiff);
    }

    private <R> R process(Function<BufferedReader, R> function) {
        try (BufferedReader br = Files.newBufferedReader(path)) {
            return function.apply(br);
        } catch (IOException e) {
            throw new ProcessingException(e);
        }
    }

    private LocalDate toDate(final String line) {
        return LocalDate.parse(cellValueOf(Column.DATE, line), DateTimeFormatter.ofPattern("dd/MM/yy"));
    }

    private String cellValueOf(final Column column, final String line) {
        return line.split(COMMA)[column.index()].trim();
    }
}
