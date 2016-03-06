package address_book;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.assertj.core.api.Assertions.assertThat;

import java.nio.file.Path;
import java.nio.file.Paths;

public class AddressBookProcessorTest {
    private AddressBookProcessor addressBookProcessor;

    private static Path fileLocation;

    @BeforeClass
    public static void setUpSuit() {
        final String testFile = "src/test/resources/AddressBook";
        final Path projectDir = Paths.get("").toAbsolutePath();
        fileLocation = Paths.get(projectDir.toString(), testFile);
    }

    @Before
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

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void shouldThrowExceptionWhenWrongFileLocation() {
        // given
        String wrongPath = "wrongPath";

        String exceptionMessage = "File not found: wrongPath";
        thrown.expect(ProcessingException.class);
        thrown.expectMessage(exceptionMessage);

        // when
        addressBookProcessor = new AddressBookProcessor(wrongPath);
    }
}
