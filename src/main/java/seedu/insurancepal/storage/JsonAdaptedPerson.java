package seedu.insurancepal.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.commons.exceptions.IllegalValueException;
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

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String revenue;
    private final String address;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<JsonAdaptedClaim> claims = new ArrayList<>();
    private final List<JsonAdaptedInsurance> insurances = new ArrayList<>();
    private final String appointment;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    @JsonIgnoreProperties(ignoreUnknown = true)
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("revenue") String revenue,
            @JsonProperty("address") String address, @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
            @JsonProperty("insurances") List<JsonAdaptedInsurance> insurances,
            @JsonProperty("note") String note, @JsonProperty("appointment") String appointment,
            @JsonProperty("claims") List<JsonAdaptedClaim> claims) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.revenue = revenue;
        this.address = address;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (claims != null) {
            this.claims.addAll(claims);
        }
        if (insurances != null) {
            this.insurances.addAll(insurances);
        }
        this.note = note;
        this.appointment = appointment;
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        revenue = String.valueOf(source.getRevenue().value.getInDollars());
        address = source.getAddress().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        insurances.addAll(source.getInsurances().stream()
                .map(JsonAdaptedInsurance::new)
                .collect(Collectors.toList()));
        note = source.getNote().value;
        appointment = source.getAppointment().getValue();
        claims.addAll(source.getClaims().stream()
                .map(JsonAdaptedClaim::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }

        final List<Claim> personClaims = new ArrayList<>();
        for (JsonAdaptedClaim claim : claims) {
            personClaims.add(claim.toModelType());
        }

        final List<Insurance> personInsurances = new ArrayList<>();
        for (JsonAdaptedInsurance insurance : insurances) {
            personInsurances.add(insurance.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        final Revenue modelRevenue;
        if (revenue == null) {
            modelRevenue = new Revenue(new Money(0));
        } else {
            modelRevenue = new Revenue(new Money(Float.parseFloat(revenue)));
        }

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Claim> modelClaims = new HashSet<>(personClaims);
        final Set<Insurance> modelInsurances = new HashSet<>(personInsurances);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        final Note modelNote = new Note(note);


        if (!Appointment.isValidMeetingTime(appointment)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Appointment modelAppointment = new Appointment(appointment);

        return new Person(modelName, modelPhone, modelEmail, modelRevenue, modelAddress, modelTags,
                modelInsurances, modelNote, modelAppointment, modelClaims);
    }

}
