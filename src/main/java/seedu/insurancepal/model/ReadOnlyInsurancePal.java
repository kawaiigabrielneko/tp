package seedu.insurancepal.model;

import javafx.collections.ObservableList;
import seedu.insurancepal.model.client.Client;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyInsurancePal {

    /**
     * Returns an unmodifiable view of the persons list.
     * This list will not contain any duplicate persons.
     */
    ObservableList<Client> getPersonList();

}
