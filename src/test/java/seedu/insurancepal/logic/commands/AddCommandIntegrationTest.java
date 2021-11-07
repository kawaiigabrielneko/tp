package seedu.insurancepal.logic.commands;

import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.insurancepal.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.ModelManager;
import seedu.insurancepal.model.UserPrefs;
import seedu.insurancepal.model.client.Client;
import seedu.insurancepal.testutil.PersonBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_newPerson_success() {
        Client validClient = new PersonBuilder().build();

        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.addPerson(validClient);

        assertCommandSuccess(new AddCommand(validClient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validClient), expectedModel);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Client clientInList = model.getAddressBook().getPersonList().get(0);
        assertCommandFailure(new AddCommand(clientInList), model, AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}
