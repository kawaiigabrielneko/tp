package seedu.insurancepal.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.insurancepal.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.ModelManager;
import seedu.insurancepal.model.UserPrefs;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.testutil.PersonBuilder;

public class ScheduleCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Person personToMeet = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Appointment sampleAppointment = new Appointment("04-Feb-2021 05:00");
        ScheduleCommand scheduleCommand = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

        Person newAppointmentPerson = new PersonBuilder(personToMeet).withAppointment(sampleAppointment).build();
        expectedModel.setPerson(personToMeet, newAppointmentPerson);

        String expectedMessage = String.format(ScheduleCommand.MESSAGE_MEET_PERSON_SUCCESS, newAppointmentPerson);

        assertCommandSuccess(scheduleCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Appointment sampleAppointment = new Appointment("04-Feb-2021 05:00");
        ScheduleCommand scheduleCommand = new ScheduleCommand(outOfBoundIndex, sampleAppointment);

        assertCommandFailure(scheduleCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        Appointment sampleAppointment1 = new Appointment("04-Feb-2021 05:00");
        Appointment sampleAppointment2 = new Appointment("05-Feb-2021 05:00");
        ScheduleCommand scheduleFirstCommand = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment1);
        ScheduleCommand scheduleSecondCommand = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment2);
        ScheduleCommand scheduleThirdCommand = new ScheduleCommand(INDEX_SECOND_PERSON, sampleAppointment1);

        // same object -> returns true
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommand));

        // same values -> returns true
        Appointment sampleAppointment1Copy = new Appointment("04-Feb-2021 05:00");
        ScheduleCommand scheduleFirstCommandCopy = new ScheduleCommand(INDEX_FIRST_PERSON, sampleAppointment1Copy);
        assertTrue(scheduleFirstCommand.equals(scheduleFirstCommandCopy));

        // different types -> returns false
        assertFalse(scheduleFirstCommand.equals(1));

        // null -> returns false
        assertFalse(scheduleFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleSecondCommand));

        // different index -> returns false
        assertFalse(scheduleFirstCommand.equals(scheduleThirdCommand));
    }

}
