package seedu.insurancepal.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.insurancepal.testutil.Assert.assertThrows;
import static seedu.insurancepal.testutil.TypicalPersons.ALICE;
import static seedu.insurancepal.testutil.TypicalPersons.HOON;
import static seedu.insurancepal.testutil.TypicalPersons.IDA;
import static seedu.insurancepal.testutil.TypicalPersons.getTypicalAddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.insurancepal.commons.exceptions.DataConversionException;
import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.ReadOnlyInsurancePal;

public class JsonInsurancePalStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonInsurancePalStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyInsurancePal> readAddressBook(String filePath) throws Exception {
        return new JsonInsurancePalStorage(Paths.get(filePath)).readInsurancePal(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidPersonAddressBook.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidPersonAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidPersonAddressBook.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        InsurancePal original = getTypicalAddressBook();
        JsonInsurancePalStorage jsonAddressBookStorage = new JsonInsurancePalStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveInsurancePal(original, filePath);
        ReadOnlyInsurancePal readBack = jsonAddressBookStorage.readInsurancePal(filePath).get();
        assertEquals(original, new InsurancePal(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addPerson(HOON);
        original.removePerson(ALICE);
        jsonAddressBookStorage.saveInsurancePal(original, filePath);
        readBack = jsonAddressBookStorage.readInsurancePal(filePath).get();
        assertEquals(original, new InsurancePal(readBack));

        // Save and read without specifying file path
        original.addPerson(IDA);
        jsonAddressBookStorage.saveInsurancePal(original); // file path not specified
        readBack = jsonAddressBookStorage.readInsurancePal().get(); // file path not specified
        assertEquals(original, new InsurancePal(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyInsurancePal addressBook, String filePath) {
        try {
            new JsonInsurancePalStorage(Paths.get(filePath))
                    .saveInsurancePal(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new InsurancePal(), null));
    }
}
