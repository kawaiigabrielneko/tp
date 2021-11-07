package seedu.insurancepal.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.insurancepal.commons.exceptions.DataConversionException;
import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.ReadOnlyInsurancePal;

/**
 * Represents a storage for {@link InsurancePal}.
 */
public interface InsurancePalStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getInsurancePalFilePath();

    /**
     * Returns InsurancePal data as a {@link ReadOnlyInsurancePal}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyInsurancePal> readInsurancePal() throws DataConversionException, IOException;

    /**
     * @see #getInsurancePalFilePath()
     */
    Optional<ReadOnlyInsurancePal> readInsurancePal(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyInsurancePal} to the storage.
     * @param insurancePal cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveInsurancePal(ReadOnlyInsurancePal insurancePal) throws IOException;

    /**
     * @see #saveInsurancePal(ReadOnlyInsurancePal)
     */
    void saveInsurancePal(ReadOnlyInsurancePal insurancePal, Path filePath) throws IOException;

}
