package seedu.insurancepal.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.insurancepal.commons.core.GuiSettings;
import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.logic.commands.Command;
import seedu.insurancepal.logic.commands.CommandResult;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.logic.parser.InsurancePalParser;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.ReadOnlyInsurancePal;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_MESSAGE = "Could not save data to file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final InsurancePalParser insurancePalParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        insurancePalParser = new InsurancePalParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = insurancePalParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveInsurancePal(model.getAddressBook());
        } catch (IOException ioe) {
            throw new CommandException(FILE_OPS_ERROR_MESSAGE + ioe, ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyInsurancePal getAddressBook() {
        return model.getAddressBook();
    }

    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return model.getFilteredPersonList();
    }

    @Override public ObservableList<Person> getPersonsWithClaims() {
        return model.getPersonsWithClaims();
    }

    @Override
    public ObservableList<Person> getAppointmentList() {
        return model.getAppointmentList();
    }

    @Override
    public Path getAddressBookFilePath() {
        return model.getAddressBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}
