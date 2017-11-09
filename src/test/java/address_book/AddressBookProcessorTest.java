package address_book;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddressBookProcessorTest {
    private AddressBookProcessor addressBookProcessor;

    private static Path fileLocation;

    @BeforeAll
    public static void setUpSuit() {
        final String testFile = "src/test/resources/AddressBook";
        final Path projectDir = Paths.get("").toAbsolutePath();
        fileLocation = Paths.get(projectDir.toString(), testFile);
    }

    @BeforeEach
    public void setUpTest() {
        addressBookProcessor = new AddressBookProcessor(fileLocation.toString());
    }

    @Test
    public void shouldCountMales() {
        // given
        final String male = "male";

        final long expected = 3;

        // when
        long actual = addressBookProcessor.count(e -> e.toLowerCase().equals(male), Column.SEX);

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldFindTheOldestPerson() {
        // given
        final String expected = "Wes Jackson, Male, 14/08/74";

        // when
        final String actual = addressBookProcessor.findOldest();

        // then
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    public void shouldReturnAgeInDaysBetweenSpecifiedPeople() {
        // given
        final String person1 = "Bill McKnight";
        final String person2 = "Paul Robinson";

        final long expected = 2862;

        // when
        final long actual = addressBookProcessor.getAgeDifferenceInDays(person1, person2);

        // then
        assertThat(actual).isEqualTo(expected);
    }


    @Test
    public void shouldThrowExceptionWhenWrongFileLocation() {
        // given
        String wrongPath = "wrongPath";

        String exceptionMessage = "File not found: wrongPath";

        // when
        Throwable exception = assertThrows(ProcessingException.class, () -> new AddressBookProcessor(wrongPath));

        // then
        Assertions.assertEquals(exceptionMessage, exception.getMessage());
    }
}
