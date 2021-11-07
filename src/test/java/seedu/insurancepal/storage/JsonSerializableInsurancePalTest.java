package seedu.insurancepal.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.commons.util.JsonUtil;
import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.testutil.TypicalPersons;

public class JsonSerializableInsurancePalTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableInsurancePalTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPersonsAddressBook.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPersonAddressBook.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePersonAddressBook.json");

    @Test
    public void toModelType_typicalPersonsFile_success() throws Exception {
        JsonSerializableInsurancePal dataFromFile = JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
                JsonSerializableInsurancePal.class).get();
        InsurancePal addressBookFromFile = dataFromFile.toModelType();
        InsurancePal typicalPersonsAddressBook = TypicalPersons.getTypicalAddressBook();
        assertEquals(addressBookFromFile, typicalPersonsAddressBook);
    }

    @Test
    public void toModelType_invalidPersonFile_throwsIllegalValueException() throws Exception {
        JsonSerializableInsurancePal dataFromFile = JsonUtil.readJsonFile(INVALID_PERSON_FILE,
                JsonSerializableInsurancePal.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicatePersons_throwsIllegalValueException() throws Exception {
        JsonSerializableInsurancePal dataFromFile = JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
                JsonSerializableInsurancePal.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableInsurancePal.MESSAGE_DUPLICATE_PERSON,
                dataFromFile::toModelType);
    }

}
