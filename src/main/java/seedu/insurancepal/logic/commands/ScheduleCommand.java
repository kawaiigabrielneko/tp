package seedu.insurancepal.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_MEETING;
import static seedu.insurancepal.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Set;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.person.Phone;
import seedu.insurancepal.model.person.Revenue;
import seedu.insurancepal.model.tag.Tag;

public class ScheduleCommand extends Command {
    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedule an appointment with the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_MEETING + "MEETING (format: dd-MMM-yyyy HH:mm)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_MEETING + "05-Feb-2022 15:00 \n";

    public static final String MESSAGE_MEET_PERSON_SUCCESS = "Meeting updated: %1$s";

    private final Index index;
    private final Appointment newAppointment;

    /**
     * @param index of the person to update appointment with
     * @param newAppointment the new appointment with the person
     */
    public ScheduleCommand(Index index, Appointment newAppointment) {
        requireNonNull(index);

        this.index = index;
        this.newAppointment = newAppointment;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToMeet = lastShownList.get(index.getZeroBased());
        Person newAppointmentPerson = scheduleAppointment(personToMeet, this.newAppointment);

        model.setPerson(personToMeet, newAppointmentPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MEET_PERSON_SUCCESS, newAppointmentPerson));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Person scheduleAppointment(Person personToMeet, Appointment desiredAppointment) {
        requireNonNull(personToMeet);

        Name originalName = personToMeet.getName();
        Phone originalPhone = personToMeet.getPhone();
        Email originalEmail = personToMeet.getEmail();
        Revenue originalRevenue = personToMeet.getRevenue();
        Address originalAddress = personToMeet.getAddress();
        Set<Tag> originalTags = personToMeet.getTags();
        Set<Insurance> originalInsurances = personToMeet.getInsurances();
        Note originalNote = personToMeet.getNote();
        Set<Claim> originalClaims = personToMeet.getClaims();
        return new Person(originalName, originalPhone, originalEmail, originalRevenue,
                originalAddress, originalTags, originalInsurances, originalNote, desiredAppointment,
                originalClaims);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        // state check
        ScheduleCommand e = (ScheduleCommand) other;
        return index.equals(e.index)
                && newAppointment.equals(e.newAppointment);
    }
}

