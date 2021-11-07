package seedu.insurancepal.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.insurancepal.commons.exceptions.DataConversionException;
import seedu.insurancepal.model.ClientBook;
import seedu.insurancepal.model.ReadOnlyClientBook;

/**
 * Represents a storage for {@link ClientBook}.
 */
public interface InsurancePalStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInsurancePalFilePath();

    /**
     * Returns InsurancePal data as a {@link ReadOnlyClientBook}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyClientBook> readClientBook() throws DataConversionException, IOException;

    /**
     * @see #getInsurancePalFilePath()
     */
    Optional<ReadOnlyClientBook> readClientBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyClientBook} to the storage.
     * @param insurancePal cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInsurancePal(ReadOnlyClientBook insurancePal) throws IOException;

    /**
     * @see #saveInsurancePal(ReadOnlyClientBook)
     */
    void saveInsurancePal(ReadOnlyClientBook insurancePal, Path filePath) throws IOException;

}
