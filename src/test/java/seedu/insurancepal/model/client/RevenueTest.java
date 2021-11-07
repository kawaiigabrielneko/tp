package seedu.insurancepal.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.core.Money;

public class RevenueTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Revenue(null));
    }

    @Test
    public void addRevenue_addingValidRevenue_success() {
        Revenue originalRevenue = new Revenue(new Money(100.21f));
        Revenue expectedRevenue = new Revenue(new Money(350.00f));
        Revenue updatedRevenue = originalRevenue.addRevenue(new Revenue(new Money(249.79f)));
        assertEquals(updatedRevenue, expectedRevenue);
    }
}
