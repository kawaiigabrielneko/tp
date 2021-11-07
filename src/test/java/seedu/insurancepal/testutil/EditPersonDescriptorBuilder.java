package seedu.insurancepal.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.insurancepal.model.client.Address;
import seedu.insurancepal.model.client.Client;
import seedu.insurancepal.model.client.Email;
import seedu.insurancepal.model.client.Insurance;
import seedu.insurancepal.model.client.Name;
import seedu.insurancepal.model.client.Note;
import seedu.insurancepal.model.client.Phone;
import seedu.insurancepal.model.client.Revenue;
import seedu.insurancepal.model.tag.Tag;

/**
 * A utility class to help with building EditPersonDescriptor objects.
 */
public class EditPersonDescriptorBuilder {

    private EditPersonDescriptor descriptor;

    public EditPersonDescriptorBuilder() {
        descriptor = new EditPersonDescriptor();
    }

    public EditPersonDescriptorBuilder(EditPersonDescriptor descriptor) {
        this.descriptor = new EditPersonDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPersonDescriptor} with fields containing {@code person}'s details
     */
    public EditPersonDescriptorBuilder(Client client) {
        descriptor = new EditPersonDescriptor();
        descriptor.setName(client.getName());
        descriptor.setPhone(client.getPhone());
        descriptor.setEmail(client.getEmail());
        descriptor.setRevenue(client.getRevenue());
        descriptor.setAddress(client.getAddress());
        descriptor.setTags(client.getTags());
        descriptor.setInsurances(client.getInsurances());
        descriptor.setNote(client.getNote());
    }

    /**
     * Sets the {@code Name} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code Revenue} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withRevenue(String revenue) {
        Money money = new Money(Float.valueOf(revenue));
        descriptor.setRevenue(new Revenue(money));
        return this;
    }
    /**
     * Sets the {@code Address} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withAddress(String address) {
        descriptor.setAddress(new Address(address));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditPersonDescriptor}
     * that we are building.
     */
    public EditPersonDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Parses the {@code insurances} into a {@code Set<Insurance>} and set it to the
     * {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withInsurances(Insurance... insurances) {
        Set<Insurance> insuranceSet = new HashSet<>(Arrays.asList(insurances));
        descriptor.setInsurances(insuranceSet);
        return this;
    }

    /**
     * Parses the {@code Note} of the {@code EditPersonDescriptor} that we are building.
     */
    public EditPersonDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    public EditPersonDescriptor build() {
        return descriptor;
    }
}
