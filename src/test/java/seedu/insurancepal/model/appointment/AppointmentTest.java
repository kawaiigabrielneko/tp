package seedu.insurancepal.model.appointment;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.insurancepal.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

public class AppointmentTest {
    @Test
    void testToString() {
        String sampleMeetingTime = "05-Feb-2011 05:30";
        Appointment appointment = new Appointment(sampleMeetingTime);

        assertTrue(sampleMeetingTime.equals(appointment.toString()));
    }

    @Test
    void testEquals() {
        String sampleMeetingTime1 = "05-Feb-2011 05:30";
        String sampleMeetingTime2 = "06-Feb-2011 05:30";
        Appointment appointment1 = new Appointment(sampleMeetingTime1);
        Appointment appointment2 = new Appointment(sampleMeetingTime2);

        assertTrue(appointment1.equals(appointment1));

        assertTrue(appointment1.equals(new Appointment(sampleMeetingTime1)));

        assertFalse(appointment1.equals(null));

        assertFalse(appointment1.equals(appointment2));
    }

    @Test
    public void isValidMeetingTime() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Appointment.isValidMeetingTime(null));

        // invalid phone numbers

        assertFalse(Appointment.isValidMeetingTime(" ")); // spaces only
        assertFalse(Appointment.isValidMeetingTime("05 Feb 2021 05:30")); // no dash
        assertFalse(Appointment.isValidMeetingTime("05-Feb-2021")); // no time
        assertFalse(Appointment.isValidMeetingTime("05-02-2021 05:30")); // incorrect month format
        assertFalse(Appointment.isValidMeetingTime("2021-Feb-05 05:30")); // wrong order

        // valid date time
        assertTrue(Appointment.isValidMeetingTime("")); // empty string
        assertTrue(Appointment.isValidMeetingTime("05-Feb-2021 05:30")); // correct format
    }

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null));
    }

    @Test
    public void constructor_invalidDateTime_throwsIllegalArgumentException() {
        String invalidDateTimeString = "blahblah";
        assertThrows(IllegalArgumentException.class, () -> new Appointment(invalidDateTimeString));
    }

    @Test
    public void isUpcoming() {
        Appointment expiredAppointment = new Appointment("05-Jan-1999 05:40");

        LocalDateTime upcomingDate = LocalDateTime.now().plusDays(2);
        Appointment upcomingAppointment = new Appointment(upcomingDate.format(Appointment.FORMATTER));

        // date has passed
        assertFalse(expiredAppointment.isUpcoming());

        // date has yet to pass
        assertTrue(upcomingAppointment.isUpcoming());
    }

    @Test
    public void compareTo() {
        Appointment earlierAppointment = new Appointment("05-Jan-1999 05:40");
        Appointment laterAppointment = new Appointment("05-Jan-2000 05:40");

        assertTrue(earlierAppointment.compareTo(earlierAppointment) == 0);
        assertTrue(earlierAppointment.compareTo(laterAppointment) < 0);
        assertTrue(laterAppointment.compareTo(earlierAppointment) > 0);
    }
}
