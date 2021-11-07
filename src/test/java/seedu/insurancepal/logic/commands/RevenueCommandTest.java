package seedu.insurancepal.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_REVENUE_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.insurancepal.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.insurancepal.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.ModelManager;
import seedu.insurancepal.model.UserPrefs;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.person.Revenue;
import seedu.insurancepal.testutil.PersonBuilder;

public class RevenueCommandTest {

    private static final String REVENUE_STUB = "100.21f";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addRevenueUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withRevenue(REVENUE_STUB).build();

        RevenueCommand revenueCommand = new RevenueCommand(INDEX_FIRST_PERSON,
                new Revenue(editedPerson.getRevenue().value));

        String expectedMessage = String.format(RevenueCommand.MESSAGE_ADD_REVENUE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new InsurancePal(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(revenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addRevenueFilteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withRevenue(REVENUE_STUB).build();

        RevenueCommand revenueCommand = new RevenueCommand(INDEX_FIRST_PERSON,
                new Revenue(editedPerson.getRevenue().value));

        String expectedMessage = String.format(RevenueCommand.MESSAGE_ADD_REVENUE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new InsurancePal(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(revenueCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        Money moneyOfBob = new Money(Float.valueOf(VALID_REVENUE_BOB));
        RevenueCommand revenueCommand = new RevenueCommand(outOfBoundIndex, new Revenue(moneyOfBob));

        assertCommandFailure(revenueCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        Money moneyOfBob = new Money(Float.valueOf(VALID_REVENUE_BOB));
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        RevenueCommand revenueCommand = new RevenueCommand(outOfBoundIndex, new Revenue(moneyOfBob));

        assertCommandFailure(revenueCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    @Test
    public void execute_resultingRevenueNegativeUnfilteredList_failure() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());

        Money negativeMoneyOfBob = new Money((-1) * Float.valueOf(REVENUE_STUB));
        RevenueCommand revenueCommand = new RevenueCommand(INDEX_FIRST_PERSON, new Revenue(negativeMoneyOfBob));
        String errorMessage = String.format(RevenueCommand.MESSAGE_ADD_REVENUE_FAIL_NEGATIVE, firstPerson.getName());

        assertCommandFailure(revenueCommand, model, errorMessage);
    }
}
