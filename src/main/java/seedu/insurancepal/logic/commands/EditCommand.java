package seedu.insurancepal.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_INSURANCE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_REVENUE;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.insurancepal.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.commons.util.CollectionUtil;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.client.Address;
import seedu.insurancepal.model.client.Email;
import seedu.insurancepal.model.client.Insurance;
import seedu.insurancepal.model.client.Name;
import seedu.insurancepal.model.client.Note;
import seedu.insurancepal.model.client.Client;
import seedu.insurancepal.model.client.Phone;
import seedu.insurancepal.model.client.Revenue;
import seedu.insurancepal.model.tag.Tag;

/**
 * Edits the details of an existing person in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_REVENUE + "REVENUE] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TAG + "TAG]..."
            + "[" + PREFIX_INSURANCE + "INSURANCE_TYPE BRAND]..."
            + "[" + PREFIX_NOTE + "NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final EditPersonDescriptor editPersonDescriptor;

    /**
     * @param index of the person in the filtered person list to edit
     * @param editPersonDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPersonDescriptor editPersonDescriptor) {
        requireNonNull(index);
        requireNonNull(editPersonDescriptor);

        this.index = index;
        this.editPersonDescriptor = new EditPersonDescriptor(editPersonDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Client> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = createEditedPerson(clientToEdit, editPersonDescriptor);

        if (!clientToEdit.isSamePerson(editedClient) && model.hasPerson(editedClient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(clientToEdit, editedClient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedClient));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Client createEditedPerson(Client clientToEdit, EditPersonDescriptor editPersonDescriptor) {
        assert clientToEdit != null;

        Name updatedName = editPersonDescriptor.getName().orElse(clientToEdit.getName());
        Phone updatedPhone = editPersonDescriptor.getPhone().orElse(clientToEdit.getPhone());
        Email updatedEmail = editPersonDescriptor.getEmail().orElse(clientToEdit.getEmail());
        Revenue updatedRevenue = editPersonDescriptor.getRevenue().orElse(clientToEdit.getRevenue());
        Address updatedAddress = editPersonDescriptor.getAddress().orElse(clientToEdit.getAddress());
        Set<Tag> updatedTags = editPersonDescriptor.getTags().orElse(clientToEdit.getTags());
        Set<Insurance> updatedInsurances = editPersonDescriptor.getInsurances()
                .orElse(clientToEdit.getInsurances());
        Set<Claim> originalClaims = clientToEdit.getClaims();
        Note updatedNote = editPersonDescriptor.getNote().orElse(clientToEdit.getNote());
        Appointment originalAppointment = clientToEdit.getAppointment();

        return new Client(updatedName, updatedPhone, updatedEmail, updatedRevenue, updatedAddress,
                updatedTags, updatedInsurances, updatedNote, originalAppointment, originalClaims);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editPersonDescriptor.equals(e.editPersonDescriptor);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPersonDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Revenue revenue;
        private Address address;
        private Set<Tag> tags;
        private Set<Insurance> insurances;
        private Note note;

        public EditPersonDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPersonDescriptor(EditPersonDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setRevenue(toCopy.revenue);
            setAddress(toCopy.address);
            setTags(toCopy.tags);
            setInsurances(toCopy.insurances);
            setNote(toCopy.note);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, revenue, address, tags, insurances, note);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setRevenue(Revenue revenue) {
            this.revenue = revenue;
        }

        public Optional<Revenue> getRevenue() {
            return Optional.ofNullable(revenue);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        /**
         * Sets {@code insurances} to this object's {@code insurances}.
         * A defensive copy of {@code insurances} is used internally.
         */
        public void setInsurances(Set<Insurance> insurances) {
            this.insurances = (insurances != null) ? new HashSet<>(insurances) : null;
        }

        /**
         * Returns an unmodifiable insurance set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code insurances} is null.
         */
        public Optional<Set<Insurance>> getInsurances() {
            return (insurances != null)
                    ? Optional.of(Collections.unmodifiableSet(insurances)) : Optional.empty();
        }
        public void setNote(Note note) {
            this.note = note;
        }

        public Optional<Note> getNote() {
            return Optional.ofNullable(note);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPersonDescriptor)) {
                return false;
            }

            // state check
            EditPersonDescriptor e = (EditPersonDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getRevenue().equals(e.getRevenue())
                    && getAddress().equals(e.getAddress())
                    && getTags().equals(e.getTags())
                    && getInsurances().equals(e.getInsurances())
                    && getNote().equals(e.getNote());
        }
    }
}
