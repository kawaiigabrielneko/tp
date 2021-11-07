package seedu.insurancepal.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.insurancepal.commons.exceptions.DataConversionException;
import seedu.insurancepal.model.ReadOnlyClientBook;
import seedu.insurancepal.model.ReadOnlyUserPrefs;
import seedu.insurancepal.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends InsurancePalStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getInsurancePalFilePath();

    @Override
    Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException, IOException;

    @Override
    void saveInsurancePal(ReadOnlyClientBook addressBook) throws IOException;

}
