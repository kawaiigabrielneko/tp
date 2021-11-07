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
import seedu.insurancepal.model.ReadOnlyInsurancePal;

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
    public Optional<ReadOnlyInsurancePal> readInsurancePal() throws DataConversionException {
        return readInsurancePal(filePath);
    }

    /**
     * Similar to {@link #readInsurancePal()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyInsurancePal> readInsurancePal(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableInsurancePal> jsonAddressBook = JsonUtil.readJsonFile(
                filePath, JsonSerializableInsurancePal.class);
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
    public void saveInsurancePal(ReadOnlyInsurancePal addressBook) throws IOException {
        saveInsurancePal(addressBook, filePath);
    }

    /**
     * Similar to {@link #saveInsurancePal(ReadOnlyInsurancePal)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveInsurancePal(ReadOnlyInsurancePal addressBook, Path filePath) throws IOException {
        requireNonNull(addressBook);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableInsurancePal(addressBook), filePath);
    }

}
