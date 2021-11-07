package seedu.insurancepal.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.commons.core.index.Index.fromZeroBased;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_NOTE_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.insurancepal.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.insurancepal.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.insurancepal.testutil.TypicalPersons.ALICE;
import static seedu.insurancepal.testutil.TypicalPersons.BOB;
import static seedu.insurancepal.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.ModelManager;
import seedu.insurancepal.model.UserPrefs;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.testutil.PersonBuilder;

//@author xianlinc-reused
//Reused from https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html

class NoteCommandTest {

    private static final String Note_STUB = "Some Note";

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_addNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withNote(Note_STUB).build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(editedPerson.getNote().value));

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new InsurancePal(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNoteUnfilteredList_success() {
        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(firstPerson).withNote("").build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON,
                new Note(editedPerson.getNote().toString()));

        String expectedMessage = String.format(NoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new InsurancePal(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_filteredList_success() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);

        Person firstPerson = model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased());
        Person editedPerson = new PersonBuilder(model.getFilteredPersonList().get(INDEX_FIRST_PERSON.getZeroBased()))
                .withNote(Note_STUB).build();

        NoteCommand noteCommand = new NoteCommand(INDEX_FIRST_PERSON, new Note(editedPerson.getNote().value));

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS, editedPerson);

        Model expectedModel = new ModelManager(new InsurancePal(model.getAddressBook()), new UserPrefs());
        expectedModel.setPerson(firstPerson, editedPerson);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidPersonIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPersonList().size() + 1);
        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Edit filtered list where index is larger than size of filtered list,
     * but smaller than size of InsurancePal
     */
    @Test
    public void execute_invalidPersonIndexFilteredList_failure() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        Index outOfBoundIndex = INDEX_SECOND_PERSON;
        // ensures that outOfBoundIndex is still in bounds of InsurancePal list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getAddressBook().getPersonList().size());

        NoteCommand noteCommand = new NoteCommand(outOfBoundIndex, new Note(VALID_NOTE_BOB));

        assertCommandFailure(noteCommand, model, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Check if the equals method of NoteCommand is behaving as expected
     */
    @Test
    public void equals() {
        Index index0 = fromZeroBased(0);
        Index index1 = fromZeroBased(1);

        NoteCommand aliceIndex0 = new NoteCommand(index0, ALICE.getNote());
        NoteCommand aliceIndex1 = new NoteCommand(index1, ALICE.getNote());
        NoteCommand bobIndex0 = new NoteCommand(index0, BOB.getNote());
        NoteCommand bobIndex1 = new NoteCommand(index1, BOB.getNote());

        // same Index and Note -> returns true
        assertTrue(aliceIndex0.equals(aliceIndex0));

        // different Index and Same Note -> returns false
        assertFalse(aliceIndex0.equals(aliceIndex1));

        // same Index and different Note -> returns false
        assertFalse(aliceIndex0.equals(bobIndex0));

        // different Index and Note -> returns false
        assertFalse(aliceIndex0.equals(bobIndex1));
    }
}
