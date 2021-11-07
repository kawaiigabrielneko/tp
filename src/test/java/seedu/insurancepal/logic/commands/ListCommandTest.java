package seedu.insurancepal.logic.commands;

import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.insurancepal.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.insurancepal.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.ModelManager;
import seedu.insurancepal.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
