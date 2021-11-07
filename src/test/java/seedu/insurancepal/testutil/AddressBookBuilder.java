package seedu.insurancepal.testutil;

import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.client.Client;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code InsurancePal ab = new AddressBookBuilder().withPerson("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private InsurancePal addressBook;

    public AddressBookBuilder() {
        addressBook = new InsurancePal();
    }

    public AddressBookBuilder(InsurancePal addressBook) {
        this.addressBook = addressBook;
    }

    /**
     * Adds a new {@code Person} to the {@code InsurancePal} that we are building.
     */
    public AddressBookBuilder withPerson(Client client) {
        addressBook.addPerson(client);
        return this;
    }

    public InsurancePal build() {
        return addressBook;
    }
}
