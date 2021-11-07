package seedu.insurancepal.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.commons.exceptions.DataConversionException;
import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.commons.util.FileUtil;
import seedu.insurancepal.commons.util.JsonUtil;
import seedu.insurancepal.model.ReadOnlyClientBook;

/**
 * A class to access InsurancePal data stored as a json file on the hard disk.
 */
public class JsonInsurancePalStorage implements InsurancePalStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonInsurancePalStorage.class);

    private Path filePath;

    public JsonInsurancePalStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getInsurancePalFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException {
        return readClientBook(filePath);
    }

    /**
     * Similar to {@link #readClientBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyClientBook> readClientBook(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableClientBook> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableClientBook.class);
        if (!jsonAddressBook.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonAddressBook.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveInsurancePal(ReadOnlyClientBook addressBook) throws IOException {
        saveInsurancePal(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveInsurancePal(ReadOnlyClientBook)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInsurancePal(ReadOnlyClientBook addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableClientBook(addressBook), filePath);
    }

}
