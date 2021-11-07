package seedu.insurancepal.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.insurancepal.model.client.Client;
import seedu.insurancepal.model.client.UniqueClientList;

/**
 * Wraps all data at the address-book level
 * Duplicates are not allowed (by .isSamePerson comparison)
 */
public class InsurancePal implements ReadOnlyInsurancePal {

    private final UniqueClientList persons;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        persons = new UniqueClientList();
    }

    public InsurancePal() {}

    /**
     * Creates an InsurancePal using the Persons in the {@code toBeCopied}
     */
    public InsurancePal(ReadOnlyInsurancePal toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the person list with {@code persons}.
     * {@code persons} must not contain duplicate persons.
     */
    public void setPersons(List<Client> clients) {
        this.persons.setPersons(clients);
    }

    /**
     * Resets the existing data of this {@code InsurancePal} with {@code newData}.
     */
    public void resetData(ReadOnlyInsurancePal newData) {
        requireNonNull(newData);

        setPersons(newData.getPersonList());
    }

    //// person-level operations

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean hasPerson(Client client) {
        requireNonNull(client);
        return persons.contains(client);
    }

    /**
     * Adds a person to the address book.
     * The person must not already exist in the address book.
     */
    public void addPerson(Client p) {
        persons.add(p);
    }

    /**
     * Replaces the given person {@code target} in the list with {@code editedPerson}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPerson} must not be the same as another existing person in the address book.
     */
    public void setPerson(Client target, Client editedClient) {
        requireNonNull(editedClient);

        persons.setPerson(target, editedClient);
    }

    /**
     * Removes {@code key} from this {@code InsurancePal}.
     * {@code key} must exist in the address book.
     */
    public void removePerson(Client key) {
        persons.remove(key);
    }

    //// util methods

    @Override
    public String toString() {
        return persons.asUnmodifiableObservableList().size() + " persons";
        // TODO: refine later
    }

    @Override
    public ObservableList<Client> getPersonList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof InsurancePal // instanceof handles nulls
                && persons.equals(((InsurancePal) other).persons));
    }

    @Override
    public int hashCode() {
        return persons.hashCode();
    }
}
