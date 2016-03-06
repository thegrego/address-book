package address_book;

import java.nio.file.Path;
import java.nio.file.Paths;

public class App {
    public static void main(String[] args) {
        final String addressBookFile = "src/main/resources/AddressBook";
        final Path projectDir = Paths.get("").toAbsolutePath();
        final Path fileLocation = Paths.get(projectDir.toString(), addressBookFile);

        final AddressBookProcessor addressBookProcessor = new AddressBookProcessor(fileLocation.toString());

        final long males = addressBookProcessor.count(line -> line.toLowerCase().equals("male"), Column.SEX);
        final String oldestPerson = addressBookProcessor.findOldest();

        final String person1 = "Bill McKnight";
        final String person2 = "Paul Robinson";
        final long ageDiffInDays = addressBookProcessor.getAgeDifferenceInDays(person1, person2);

        System.out.println("Number of males: " + males);
        System.out.println("Oldest person: " + oldestPerson);
        System.out.format("Age difference in days between %s and %s: %d%n", person1, person2, ageDiffInDays);
    }
}
