package seedu.insurancepal.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.commons.exceptions.DataConversionException;
import seedu.insurancepal.model.ReadOnlyInsurancePal;
import seedu.insurancepal.model.ReadOnlyUserPrefs;
import seedu.insurancepal.model.UserPrefs;

/**
 * Manages storage of InsurancePal data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private InsurancePalStorage insurancePalStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code InsurancePalStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(InsurancePalStorage insurancePalStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.insurancePalStorage = insurancePalStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ InsurancePal methods ==============================

    @Override
    public Path getInsurancePalFilePath() {
        return insurancePalStorage.getInsurancePalFilePath();
    }

    @Override
    public Optional<ReadOnlyInsurancePal> readInsurancePal() throws DataConversionException, IOException {
        return readInsurancePal(insurancePalStorage.getInsurancePalFilePath());
    }

    @Override
    public Optional<ReadOnlyInsurancePal> readInsurancePal(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return insurancePalStorage.readInsurancePal(filePath);
    }

    @Override
    public void saveInsurancePal(ReadOnlyInsurancePal addressBook) throws IOException {
        saveInsurancePal(addressBook, insurancePalStorage.getInsurancePalFilePath());
    }

    @Override
    public void saveInsurancePal(ReadOnlyInsurancePal addressBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        insurancePalStorage.saveInsurancePal(addressBook, filePath);
    }

}
