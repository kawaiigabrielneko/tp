package seedu.insurancepal.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.insurancepal.commons.core.GuiSettings;
import seedu.insurancepal.logic.commands.CommandResult;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.ReadOnlyInsurancePal;
import seedu.insurancepal.model.person.Person;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the InsurancePal.
     *
     * @see seedu.insurancepal.model.Model#getAddressBook()
     */
    ReadOnlyInsurancePal getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Person> getFilteredPersonList();

    /** Returns an unmodifiable view of the claims in the filtered list of persons */
    ObservableList<Person> getPersonsWithClaims();

    /** Returns an unmodifiable view of the Appointments in the filtered list of persons */
    ObservableList<Person> getAppointmentList();

    /**
     * Returns the user prefs' address book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
