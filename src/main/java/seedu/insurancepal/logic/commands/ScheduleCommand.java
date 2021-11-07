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
import seedu.insurancepal.model.client.Address;
import seedu.insurancepal.model.client.Email;
import seedu.insurancepal.model.client.Insurance;
import seedu.insurancepal.model.client.Name;
import seedu.insurancepal.model.client.Note;
import seedu.insurancepal.model.client.Client;
import seedu.insurancepal.model.client.Phone;
import seedu.insurancepal.model.client.Revenue;
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
        List<Client> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Client clientToMeet = lastShownList.get(index.getZeroBased());
        Client newAppointmentClient = scheduleAppointment(clientToMeet, this.newAppointment);

        model.setPerson(clientToMeet, newAppointmentClient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_MEET_PERSON_SUCCESS, newAppointmentClient));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Client scheduleAppointment(Client clientToMeet, Appointment desiredAppointment) {
        requireNonNull(clientToMeet);

        Name originalName = clientToMeet.getName();
        Phone originalPhone = clientToMeet.getPhone();
        Email originalEmail = clientToMeet.getEmail();
        Revenue originalRevenue = clientToMeet.getRevenue();
        Address originalAddress = clientToMeet.getAddress();
        Set<Tag> originalTags = clientToMeet.getTags();
        Set<Insurance> originalInsurances = clientToMeet.getInsurances();
        Note originalNote = clientToMeet.getNote();
        Set<Claim> originalClaims = clientToMeet.getClaims();
        return new Client(originalName, originalPhone, originalEmail, originalRevenue,
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

