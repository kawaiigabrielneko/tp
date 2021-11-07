package seedu.insurancepal.model.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.insurancepal.commons.core.Money;
import seedu.insurancepal.model.ClientBook;
import seedu.insurancepal.model.ReadOnlyClientBook;
import seedu.insurancepal.model.appointment.Appointment;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.claim.Description;
import seedu.insurancepal.model.claim.Status;
import seedu.insurancepal.model.claim.Title;
import seedu.insurancepal.model.person.Address;
import seedu.insurancepal.model.person.Email;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.InsuranceType;
import seedu.insurancepal.model.person.Name;
import seedu.insurancepal.model.person.Note;
import seedu.insurancepal.model.person.Person;
import seedu.insurancepal.model.person.Phone;
import seedu.insurancepal.model.person.Revenue;
import seedu.insurancepal.model.tag.Tag;

/**
 * Contains utility methods for populating {@code InsurancePal} with sample data.
 */
public class SampleDataUtil {

    public static final Revenue ZERO_REVENUE = new Revenue(new Money(0));

    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Revenue(new Money(0)), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("friends"), getInsuranceSet("Life"), new Note("Likes chicken"),
                new Appointment("12-Feb-2022 12:12"),
                ofValidClaimSet("Heart Surgery", "by Dr. Chan Keng Song at TTSH", "pending")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("colleagues", "friends"), getInsuranceSet("Health"),
                new Note("Enjoys beef"), new Appointment("13-Feb-2022 12:13"),
                ofValidClaimSet("Car Accident at Thompson Road",
                        "Other party at fault",
                        "Completed")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("neighbours"), getInsuranceSet("Life", "Health"),
                new Note("Eats chinese food"), new Appointment(""),
                ofValidClaimSet("Root Canel", "At Happy Tooth Clinic", "pending")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("family"), getInsuranceSet("General"),
                new Note("Does not eat pork"), new Appointment("14-Feb-2022 12:12"),
                ofValidClaimSet("Lost of luggage", "By Tiger Airways", "pending")),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492022"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("classmates"), getInsuranceSet("Life", "General"),
                new Note("Does not like cake"), new Appointment("10-Feb-2022 12:12"),
                ofValidClaimSet("Flooded basement", "Covered under home insurance", "completed")),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("colleagues"), getInsuranceSet(), new Note("Does not like coffee"),
                    new Appointment("20-Feb-2011 15:12"), new HashSet<>())
        };
    }

    public static ReadOnlyClientBook getSampleAddressBook() {
        ClientBook sampleAb = new ClientBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns an Insurance, assumed to be valid
     */
    public static Insurance ofValidInsurance(String insuranceName) {
        for (InsuranceType type : InsuranceType.values()) {
            if (type.getTypeName().equalsIgnoreCase(insuranceName)) {
                return new Insurance(type, "");
            }
        }
        assert false; // This shouldn't happen in testing
        return null;
    }

    /**
     * Returns a Claim, assumed to be valid
     *
     * @param title A valid title
     * @param description A valid description
     * @param status A valid status
     */
    public static HashSet<Claim> ofValidClaimSet(String title, String description, String status) {
        HashSet<Claim> claimSet = new HashSet<>();
        claimSet.add(new Claim(new Title(title), new Description(description), new Status(status)));
        return claimSet;
    }

    /**
     * Returns an insurance set containing the list of insurances given.
     */
    public static Set<Insurance> getInsuranceSet(String... insurances) {
        return Arrays.stream(insurances)
                .map(SampleDataUtil::ofValidInsurance)
                .collect(Collectors.toSet());
    }

}
