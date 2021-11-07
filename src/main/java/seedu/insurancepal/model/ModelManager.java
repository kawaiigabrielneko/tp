package seedu.insurancepal.model;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.insurancepal.commons.core.GuiSettings;
import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.model.client.Client;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final InsurancePal addressBook;
    private final UserPrefs userPrefs;
    private final FilteredList<Client> filteredClients;

    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     */
    public ModelManager(ReadOnlyInsurancePal addressBook, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(addressBook, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new InsurancePal(addressBook);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredClients = new FilteredList<>(this.addressBook.getPersonList());
    }

    public ModelManager() {
        this(new InsurancePal(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    //=========== InsurancePal ================================================================================

    @Override
    public void setAddressBook(ReadOnlyInsurancePal addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public ReadOnlyInsurancePal getAddressBook() {
        return addressBook;
    }

    @Override
    public boolean hasPerson(Client client) {
        requireNonNull(client);
        return addressBook.hasPerson(client);
    }

    @Override
    public void deletePerson(Client target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Client client) {
        addressBook.addPerson(client);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Client target, Client editedClient) {
        requireAllNonNull(target, editedClient);

        addressBook.setPerson(target, editedClient);
    }

    //=========== Filtered Person List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Client> getFilteredPersonList() {
        return filteredClients;
    }

    /**
     * Retrieves a list of claims of the persons in the filteredList
     */
    @Override
    public ObservableList<Client> getPersonsWithClaims() {
        return filteredClients.filtered(Client::hasClaims);
    }

    /**
     * Retrieves sorted list of people whose appointment are still upcoming.
     *
     * @return the aforementioned sorted list of people.
     */
    @Override
    public ObservableList<Client> getAppointmentList() {
        return filteredClients.filtered(Client::hasUpcomingAppointment)
                .sorted(Comparator.comparing(Client::getAppointment));
    }

    @Override
    public void updateFilteredPersonList(Predicate<Client> predicate) {
        requireNonNull(predicate);
        filteredClients.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredClients.equals(other.filteredClients);
    }

}
