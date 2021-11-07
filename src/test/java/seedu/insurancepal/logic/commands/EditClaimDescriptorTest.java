package seedu.insurancepal.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.logic.commands.CommandTestUtil.CLAIM_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.CLAIM_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_CLAIM_TITLE_AMY;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.logic.commands.ClaimCommand.EditClaimDescriptor;
import seedu.insurancepal.model.claim.Title;
import seedu.insurancepal.testutil.ClaimBuilder;


public class EditClaimDescriptorTest {
    @Test
    public void equals() {
        EditClaimDescriptor editClaimDescriptor = new ClaimBuilder(CLAIM_AMY).buildEditClaimDescriptor();
        EditClaimDescriptor editClaimDescriptorSame = new ClaimBuilder(CLAIM_AMY).buildEditClaimDescriptor();
        EditClaimDescriptor editClaimDescriptorDifferent = new ClaimBuilder(CLAIM_BOB).buildEditClaimDescriptor();

        // same values -> returns true
        assertTrue(editClaimDescriptor.equals(editClaimDescriptorSame));

        // same object -> returns true
        assertTrue(editClaimDescriptor.equals(editClaimDescriptor));

        // null -> returns false
        assertFalse(editClaimDescriptor.equals(null));

        // different types -> returns false
        assertFalse(editClaimDescriptor.equals(5));

        // different values -> returns false
        assertFalse(editClaimDescriptor.equals(editClaimDescriptorDifferent));

        // different name -> returns false
        EditClaimDescriptor editedAmy = new ClaimBuilder(CLAIM_AMY).withTitle("Bobby").buildEditClaimDescriptor();
        assertFalse(DESC_AMY.equals(editedAmy));

        //different description -> return false
        editedAmy = new ClaimBuilder(CLAIM_AMY).withDescription("Other").buildEditClaimDescriptor();
        assertFalse(DESC_AMY.equals(editedAmy));

        // different status -> return false
        editedAmy = new ClaimBuilder(CLAIM_AMY).withDescription("completed").buildEditClaimDescriptor();
        assertFalse(DESC_AMY.equals(editedAmy));
    }

    @Test
    public void isEmpty() {
        // Only title  -> return true
        EditClaimDescriptor emptyClaimDescriptor = new EditClaimDescriptor(new Title(VALID_CLAIM_TITLE_AMY));
        assertTrue(emptyClaimDescriptor.isEmpty());
    }
}
