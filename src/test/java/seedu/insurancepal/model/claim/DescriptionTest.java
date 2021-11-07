package seedu.insurancepal.model.claim;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DescriptionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Description(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new Description(invalidDescription));
    }

    @Test
    public void isValidDescription() {
        // Empty Description
        assertFalse(Description.isValidDescription(""));
        // Only spaces
        assertFalse(Description.isValidDescription(" "));
        // Normal Description
        assertTrue(Description.isValidDescription("Description"));
        // Description with spaces
        assertTrue(Description.isValidDescription("Description title"));
    }

    @Test
    public void toStringTest() {
        // Proper toString behavior
        assertTrue(new Description("Description 1").toString().equals("Description 1"));
        // Improper toString behavior
        assertFalse(new Description("Description 1").toString().equals("Description 2"));
    }

    @Test
    public void equals() {
        // Different titles
        assertFalse(new Description("Description 1").equals(new Description("Description 2")));
        // Same titles
        assertTrue(new Description("Description 1").equals(new Description("Description 1")));
    }
}
