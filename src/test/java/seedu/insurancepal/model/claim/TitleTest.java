package seedu.insurancepal.model.claim;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TitleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Title(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidTitle = "";
        assertThrows(IllegalArgumentException.class, () -> new Title(invalidTitle));
    }

    @Test
    public void isValidTitle() {
        // Empty Title
        assertFalse(Title.isValidTitle(""));
        // Only spaces
        assertFalse(Title.isValidTitle(" "));
        // Normal Title
        assertTrue(Title.isValidTitle("Title"));
        // Title with spaces
        assertTrue(Title.isValidTitle("Title title"));
    }

    @Test
    public void equals() {
        // Different titles
        assertNotEquals(new Title("Title 2"), new Title("Title 1"));
        // Same titles
        assertEquals(new Title("Title 1"), new Title("Title 1"));
    }

    @Test
    public void compareTo() {
        // Equal titles
        assertEquals(new Title("a").compareTo(new Title("a")), 0);
        // Less
        assertTrue(new Title("a").compareTo(new Title("b")) < 0);
        // More
        assertTrue(new Title("b").compareTo(new Title("a")) > 0);
    }
}
