package seedu.insurancepal.model.claim;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Status(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidStatus = "";
        assertThrows(IllegalArgumentException.class, () -> new Status(invalidStatus));
    }

    @Test
    public void isValidStatus() {
        // Empty Status
        assertFalse(Status.isValidStatus(""));
        // Only spaces
        assertFalse(Status.isValidStatus(" "));
        // Pending status
        assertTrue(Status.isValidStatus("pending"));
        // Completed status
        assertTrue(Status.isValidStatus("completed"));
        // Status capitalised
        assertTrue(Status.isValidStatus("COMPLETED"));
    }

    @Test
    public void toStringTest() {
        // Proper toString behavior
        assertTrue(new Status("pending").toString().equals("PENDING"));
        // Improper toString behavior
        assertFalse(new Status("pending").toString() == "pending");
    }

    @Test
    public void equals() {
        // Same status
        assertTrue(new Status("pending").equals(new Status("Pending")));
        // Same status
        assertTrue(new Status("pending").equals(new Status("pending")));
        // Different status
        assertFalse(new Status("Pending").equals(new Status("Completed")));
    }


}
