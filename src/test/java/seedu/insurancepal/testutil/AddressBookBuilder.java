package seedu.insurancepal.testutil;

import seedu.insurancepal.model.ClientBook;
import seedu.insurancepal.model.person.Person;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code InsurancePal ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private ClientBook addressBook;

    public AddressBookBuilder() {
        addressBook = new ClientBook();
    }

    public AddressBookBuilder(ClientBook addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code InsurancePal} that we are building.
     */
    public AddressBookBuilder withPerson(Person person) {
        addressBook.addPerson(person);
        return this;
    }

    public ClientBook build() {
        return addressBook;
    }
}
