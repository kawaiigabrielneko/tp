package seedu.insurancepal.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.insurancepal.commons.exceptions.IllegalValueException;

public class InsuranceTest {
    @Test
    public void of_invalidInsurance_throwsIllegalArgumentException() {
        assertThrows(IllegalValueException.class, () -> Insurance.of("Money", ""));
        assertThrows(IllegalValueException.class, () -> Insurance.of("Hospital", "x"));
    }

    @Test
    public void of() throws Exception {
        // Compare constructor and of
        assertEquals(new Insurance(InsuranceType.GENERAL, "Manulife"),
                Insurance.of("General", "Manulife"));
        assertEquals(new Insurance(InsuranceType.LIFE, "Aviva"),
                Insurance.of("Life", "Aviva"));
        assertEquals(new Insurance(InsuranceType.HEALTH, "asdfg"),
                Insurance.of("Health", "asdfg"));

        // Compare constructor and of using hash code
        assertEquals(new Insurance(InsuranceType.GENERAL, "aaaa").hashCode(),
                Insurance.of("General", "aaaa").hashCode());
        assertEquals(new Insurance(InsuranceType.LIFE, "AIA").hashCode(),
                Insurance.of("Life", "AIA").hashCode());
        assertEquals(new Insurance(InsuranceType.HEALTH, "FWD").hashCode(),
                Insurance.of("Health", "FWD").hashCode());

        // Insurance type should be the same regardless of case
        assertEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("General", "ABC"));
        assertEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("GENERAL", "ABC"));
        assertEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("general", "ABC"));
        assertEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("gENeRal", "ABC"));

        // Insurance should be different if the brand is slightly different
        assertNotEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("General", "abc"));
        assertNotEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("General", "ABc"));
        assertNotEquals(new Insurance(InsuranceType.GENERAL, "ABC"),
                Insurance.of("General", "ABC "));

        assertThrows(IllegalValueException.class, () -> Insurance.of(
                "UnknownInsurance", "somebrand"));

        assertThrows(IllegalValueException.class, () -> Insurance.of(
                "General", "Loooooooooooooooong brand"));
    }
}
