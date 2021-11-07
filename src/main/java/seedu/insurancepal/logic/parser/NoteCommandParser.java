package seedu.insurancepal.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.logic.parser.CliSyntax.PREFIX_NOTE;

import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.logic.commands.NoteCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.person.Note;

//@author xianlinc-reused
//Reused from https://nus-cs2103-ay2122s1.github.io/tp/tutorials/AddRemark.html

public class NoteCommandParser {

    /**
     * Parses the given {@code String} of arguments in the context of the NoteCommand
     * and returns a NoteCommand for execution.
     * @throws seedu.insurancepal.logic.parser.exceptions.ParseException if the user input
     * does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_NOTE);

        Index index;
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (IllegalValueException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    NoteCommand.MESSAGE_USAGE), ive);
        }

        Note note = new Note(argMultimap.getValue(PREFIX_NOTE).orElse(""));

        return new NoteCommand(index, note);
    }
}
