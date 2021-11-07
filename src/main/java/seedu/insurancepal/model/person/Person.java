package seedu.insurancepal.model.person;

import static seedu.insurancepal.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Revenue revenue;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Insurance> insurances = new HashSet<>();
    private final Set<Claim> claims = new HashSet<>();
    private final Note note;
    private final Appointment appointment;

    /**
     * Every field except revenue must be present and not null. Revenue will be set to 0 by default if not stated.
     */
    public Person(Name name, Phone phone, Email email, Address address,
                  Set<Tag> tags, Set<Insurance> insurances, Note note, Appointment appointment, Set<Claim> claims) {
        requireAllNonNull(name, phone, email, address, tags, insurances, note, appointment, claims);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.revenue = new Revenue(new Money(0));
        this.address = address;
        this.tags.addAll(tags);
        this.insurances.addAll(insurances);
        this.note = note;
        this.appointment = appointment;
        this.claims.addAll(claims);
    }

    /**
     * Every field for this case is provided and hence a revenue value will be tagged to the person.
     */
    public Person(Name name, Phone phone, Email email, Revenue revenue, Address address, Set<Tag> tags,
             Set<Insurance> insurances, Note note, Appointment appointment, Set<Claim> claims) {
        requireAllNonNull(name, phone, email, revenue, address, tags, insurances, note, appointment, claims);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.revenue = revenue;
        this.address = address;
        this.tags.addAll(tags);
        this.insurances.addAll(insurances);
        this.note = note;
        this.appointment = appointment;
        this.claims.addAll(claims);
    }

    /**
     * Overridden constructor which takes in a person and overwrites its claims with another set of claims.
     */
    public Person(Person previousPerson, Set<Claim> claims) {
        this(previousPerson.name,
                previousPerson.phone,
                previousPerson.email,
                previousPerson.address,
                previousPerson.tags,
                previousPerson.insurances,
                previousPerson.note,
                previousPerson.appointment,
                claims);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Revenue getRevenue() {
        return revenue;
    }

    public Address getAddress() {
        return address;
    }

    public Note getNote() {
        return note;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable claims set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Claim> getClaims() {
        return Collections.unmodifiableSet(this.claims);
    }

    /**
     * Returns an immutable insurance set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Insurance> getInsurances() {
        return Collections.unmodifiableSet(insurances);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if the appointment scheduled with this person is happening in the future.
     */
    public boolean hasUpcomingAppointment() {
        return this.appointment.isUpcoming();
    }

    public boolean hasClaims() {
        return !this.getClaims().isEmpty();
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getRevenue().equals(getRevenue())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getClaims().equals(getClaims())
                && otherPerson.getInsurances().equals(getInsurances())
                && otherPerson.getNote().equals(getNote())
                && otherPerson.getAppointment().equals(getAppointment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, revenue, address, tags, insurances, note, claims, appointment);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Revenue: ")
                .append(getRevenue())
                .append("; Address: ")
                .append(getAddress())
                .append("; Note: ")
                .append(getNote())
                .append("; Meeting: ")
                .append(getAppointment());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        Set<Insurance> insurances = getInsurances();
        if (!insurances.isEmpty()) {
            builder.append("; Insurances: ");
            insurances.forEach(builder::append);
        }
        if (!claims.isEmpty()) {
            builder.append("; Claims: ");
            claims.forEach(builder::append);
        }
        return builder.toString();
    }

}
