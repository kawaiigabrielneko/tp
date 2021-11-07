package seedu.insurancepal.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.insurancepal.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.insurancepal.testutil.Assert.assertThrows;
import static seedu.insurancepal.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.logic.commands.AddCommand;
import seedu.insurancepal.logic.commands.ClearCommand;
import seedu.insurancepal.logic.commands.DeleteCommand;
import seedu.insurancepal.logic.commands.EditCommand;
import seedu.insurancepal.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.insurancepal.logic.commands.ExitCommand;
import seedu.insurancepal.logic.commands.FindCommand;
import seedu.insurancepal.logic.commands.HelpCommand;
import seedu.insurancepal.logic.commands.ListCommand;
import seedu.insurancepal.logic.commands.NoteCommand;
import seedu.insurancepal.logic.commands.RevenueCommand;
import seedu.insurancepal.logic.parser.exceptions.ParseException;
import seedu.insurancepal.model.person.NameContainsKeywordsPredicate;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.testutil.EditPersonDescriptorBuilder;
import seedu.insurancepal.testutil.PersonBuilder;
import seedu.insurancepal.testutil.PersonUtil;

public class InsurancePalParserTest {

    private final InsurancePalParser parser = new InsurancePalParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_note() throws Exception {
        Person person = new PersonBuilder().build();
        NoteCommand command = (NoteCommand) parser.parseCommand(
                NoteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + PersonUtil.getNoteDetails(person));
        assertEquals(new NoteCommand(INDEX_FIRST_PERSON, person.getNote()), command);
    }

    @Test
    public void parseCommand_revenue() throws Exception {
        Person person = new PersonBuilder().build();
        RevenueCommand command = (RevenueCommand) parser.parseCommand(
                RevenueCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased()
                        + " " + CliSyntax.PREFIX_REVENUE + "0");
        assertEquals(new RevenueCommand(INDEX_FIRST_PERSON, person.getRevenue()), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
