package seedu.insurancepal.logic.commands;

import static seedu.insurancepal.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.insurancepal.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Person;

//@author xianlinc-reused
//Reused from https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html

/**
 * Edits a note of an existing person in the InsurancePal
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the note of the person identified "
            + "by the index number used in the last person listing. "
            + "Existing note will be overwritten by the input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[no/NOTE]\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_NOTE
            + "Enjoys western cuisine";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Note: %2$s";
    public static final String MESSAGE_ADD_NOTE_SUCCESS = "Added note to Person: %1$s";
    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Removed note from Person: %1$s";

    private final Index index;
    private final Note note;

    /**
     * @param index of the person in the filtered person list to add or edit the note
     * @param note of the person to be updated to
     */
    public NoteCommand(Index index, Note note) {
        requireAllNonNull(index, note);

        this.index = index;
        this.note = note;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person editedPerson = new Person(
                personToEdit.getName(), personToEdit.getPhone(), personToEdit.getEmail(),
                personToEdit.getAddress(), personToEdit.getTags(), personToEdit.getInsurances(),
                note, personToEdit.getAppointment(), personToEdit.getClaims());

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof NoteCommand)) {
            return false;
        }

        // state check
        NoteCommand e = (NoteCommand) other;
        return index.equals(e.index)
                && note.equals(e.note);
    }
    /**
     * Generates a command execution success message based on whether
     * the note is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Person personToEdit) {
        String message = !note.value.isEmpty() ? MESSAGE_ADD_NOTE_SUCCESS : MESSAGE_DELETE_NOTE_SUCCESS;
        return String.format(message, personToEdit);
    }
}
