package seedu.insurancepal.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class InsuranceTypeTest {
    @Test
    public void getTypeName() {
        assertEquals("Life", InsuranceType.LIFE.getTypeName());
        assertEquals("General", InsuranceType.GENERAL.getTypeName());
        assertEquals("Health", InsuranceType.HEALTH.getTypeName());
    }
}
