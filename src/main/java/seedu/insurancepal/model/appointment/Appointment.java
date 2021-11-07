package seedu.insurancepal.model.appointment;

import static java.util.Objects.requireNonNull;
import static seedu.insurancepal.commons.util.AppUtil.checkArgument;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

/**
 * Represents a scheduled appointment with an individual.
 * If appointment does not exist, appointment field is null.
 */
public class Appointment implements Comparable<Appointment> {
    public static final String MESSAGE_CONSTRAINTS =
            "Meeting should be in the following format: dd-MMM-yyyy HH:mm.\n"
            + "Please make sure your date is valid as well.";
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter
            .ofPattern("dd-MMM-uuuu HH:mm") // Specific format as described in argument.
            .withResolverStyle(ResolverStyle.STRICT); // Ensure that dates like 30 Feb are invalid.

    private final LocalDateTime appointmentTime;

    /**
     * Creates an appointment that describes
     *
     * @param dateTimeString the string representation of the desired appointment.
     */
    public Appointment(String dateTimeString) {
        requireNonNull(dateTimeString);
        checkArgument(isValidMeetingTime(dateTimeString), MESSAGE_CONSTRAINTS);

        if (dateTimeString.equals("")) {
            this.appointmentTime = null;
        } else {
            this.appointmentTime = Appointment.parseString(dateTimeString);
        }
    }

    @Override
    public String toString() {
        if (appointmentTime == null) {
            return "";
        }
        return this.appointmentTime.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                    && (appointmentTime == null && ((Appointment) other).appointmentTime == null // both no apptmt
                        || appointmentTime.equals(((Appointment) other).appointmentTime))); // state check
    }

    /**
     * Compares this appointment to another appointment based on the time
     *
     * @param other the other appointment to compare to
     * @return negative is this appointment is earlier, positive if it is later and 0 if they are at the same time.
     */
    @Override
    public int compareTo(Appointment other) {
        return this.appointmentTime.compareTo(other.appointmentTime);
    }

    /**
     * Checks whether the string that is supposed to represent a meeting timing is valid.
     *
     * @param meetingDateTime the string to check.
     * @return true if string is parsable, empty or null.
     */
    public static boolean isValidMeetingTime(String meetingDateTime) {
        if (meetingDateTime.equals("")) {
            return true;
        }
        try {
            LocalDateTime.parse(convertMonthToFormat(meetingDateTime), FORMATTER);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        } catch (StringIndexOutOfBoundsException error) {
            return false;
        }
    }

    /**
     * Parses and converts the dateTimeString to LocalDateTime object.
     *
     * @param dateTimeString provided by the user.
     * @return DateTime object representing the date and time of the appointment.
     */
    private static LocalDateTime parseString(String dateTimeString) {
        requireNonNull(dateTimeString);

        return LocalDateTime.parse(convertMonthToFormat(dateTimeString), FORMATTER);
    }

    /**
     * Converts the first letter of the input month to uppercase
     *
     * @param dateTimeString date input by the user.
     * @return String with the first letter for the month in capital letters.
     */
    private static String convertMonthToFormat(String dateTimeString) {
        String convertedDate = dateTimeString.substring(0, 3) + dateTimeString.substring(3, 4).toUpperCase()
                + dateTimeString.substring(4, 6).toLowerCase() + dateTimeString.substring(6, 17);

        return convertedDate;
    }

    public String getValue() {
        if (this.appointmentTime == null) {
            return "";
        }
        return this.appointmentTime.format(FORMATTER);
    }

    /**
     * Returns true if the date and time contained in this appointment is happening in the future.
     */
    public boolean isUpcoming() {
        if (appointmentTime == null) {
            return false;
        }
        return this.appointmentTime.compareTo(LocalDateTime.now()) > 0;
    }
}
