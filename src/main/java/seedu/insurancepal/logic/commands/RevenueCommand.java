package seedu.insurancepal.logic.commands;

import static seedu.insurancepal.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.insurancepal.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;

import seedu.insurancepal.commons.core.Messages;
import seedu.insurancepal.commons.core.index.Index;
import seedu.insurancepal.logic.commands.exceptions.CommandException;
import seedu.insurancepal.model.Model;
import seedu.insurancepal.model.client.Client;
import seedu.insurancepal.model.client.Revenue;

/**
 * Updates the revenue of an existing client in the address book.
 */
public class RevenueCommand extends Command {

    public static final String COMMAND_WORD = "revenue";

    public static final String MESSAGE_ADD_REVENUE_SUCCESS = "Added revenue to Person: %1$s";
    public static final String MESSAGE_ADD_REVENUE_FAIL_NEGATIVE = "Failed to add revenue to Person: %1$s. "
            + "\nResulting revenue is negative!";
    public static final String MESSAGE_ADD_REVENUE_FAIL_OVERFLOW = "Failed to add revenue to Person: %1$s, "
            + "\nResulting revenue is too large, ensure that total revenue is not more than 19,999,998!";


    private final Index index;
    private final Revenue revenue;

    /**
     * @param index of the person in the filtered person list to edit the remark
     * @param revenue of the person to be updated to
     */
    public RevenueCommand(Index index, Revenue revenue) {
        requireAllNonNull(index, revenue);

        this.index = index;
        this.revenue = revenue;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        List<Client> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Client clientToEdit = lastShownList.get(index.getZeroBased());
        Client editedClient = new Client(
                    clientToEdit.getName(), clientToEdit.getPhone(), clientToEdit.getEmail(),
                    this.revenue.addRevenue(clientToEdit.getRevenue()),
                    clientToEdit.getAddress(), clientToEdit.getTags(),
                    clientToEdit.getInsurances(), clientToEdit.getNote(),
                    clientToEdit.getAppointment(), clientToEdit.getClaims());

        if (editedClient.getRevenue().isMaxRevenue()) {
            throw new CommandException(String.format(MESSAGE_ADD_REVENUE_FAIL_OVERFLOW, clientToEdit.getName()));
        }

        if (!editedClient.getRevenue().isValidResultingRevenue()) {
            throw new CommandException(String.format(MESSAGE_ADD_REVENUE_FAIL_NEGATIVE, clientToEdit.getName()));
        }
        model.setPerson(clientToEdit, editedClient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(generateSuccessMessage(editedClient));
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage(Client clientToEdit) {
        return String.format(MESSAGE_ADD_REVENUE_SUCCESS, clientToEdit);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RevenueCommand // instanceof handles nulls
                && revenue.equals(((RevenueCommand) other).revenue) && index.equals(((RevenueCommand) other).index));
    }
}
