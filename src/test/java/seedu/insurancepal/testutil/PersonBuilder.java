package seedu.insurancepal.testutil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.person.Phone;
import seedu.insurancepal.model.person.Revenue;
import seedu.insurancepal.model.tag.Tag;
import seedu.insurancepal.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final Money DEFAULT_REVENUE = new Money(0);
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_NOTE = "";
    public static final String DEFAULT_MEETING = "";

    private Name name;
    private Phone phone;
    private Email email;
    private Revenue revenue;
    private Address address;
    private Set<Tag> tags;
    private Set<Insurance> insurances;
    private Note note;
    private Appointment appointment;
    private Set<Claim> claims;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        revenue = new Revenue(DEFAULT_REVENUE);
        address = new Address(DEFAULT_ADDRESS);
        tags = new HashSet<>();
        insurances = new HashSet<>();
        note = new Note(DEFAULT_NOTE);
        appointment = new Appointment(DEFAULT_MEETING);
        claims = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        revenue = personToCopy.getRevenue();
        address = personToCopy.getAddress();
        tags = new HashSet<>(personToCopy.getTags());
        insurances = new HashSet<>(personToCopy.getInsurances());
        note = personToCopy.getNote();
        appointment = personToCopy.getAppointment();
        claims = new HashSet<>(personToCopy.getClaims());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Revenue} of the {@code Person} that we are building.
     */
    public PersonBuilder withRevenue(String revenue) {
        float revenueInFloat = Float.valueOf(revenue);
        this.revenue = new Revenue(new Money(revenueInFloat));
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Insurances} of the {@code Insurance} that we are building.
     */
    public PersonBuilder withInsurances(Insurance... insurances) {
        this.insurances = new HashSet<Insurance>(Arrays.asList(insurances));
        return this;
    }
    /**
     * Sets the {@code Note} of the {@code Person} that we are building.
     */
    public PersonBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    /**
     * Sets the {@code Appointment} of the {@code Person} that we are building.
     */
    public PersonBuilder withAppointment(Appointment appointment) {
        this.appointment = appointment;
        return this;
    }

    /**
     * Sets the {@code Claim} of the {@code Person} that we are building.
     */
    public PersonBuilder withClaim(ClaimBuilder ... claims) {
        this.claims = Arrays.stream(claims)
                .map(claim -> claim.buildClaim())
                .collect(Collectors.toSet());
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, revenue, address, tags, insurances, note, appointment, claims);
    }

}
