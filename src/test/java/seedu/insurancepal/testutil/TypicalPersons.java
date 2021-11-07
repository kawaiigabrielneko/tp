package seedu.insurancepal.testutil;

import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_REVENUE_AMY;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_REVENUE_BOB;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.insurancepal.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.person.Insurance;
import seedu.insurancepal.model.person.InsuranceType;
import seedu.insurancepal.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withRevenue("0.00f").withTags("friends")
            .withInsurances(new Insurance(InsuranceType.LIFE, "AAA"))
            .withNote("Has asthma").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withRevenue("0.00f")
            .withTags("owesMoney", "friends").withInsurances(new Insurance(InsuranceType.LIFE, "AIA"),
                    new Insurance(InsuranceType.HEALTH, "AIA"), new Insurance(InsuranceType.GENERAL, "AIA"))
            .withNote("Has cancer")
            .withClaim(new ClaimBuilder().withTitle("Knee Surgery")
                    .withDescription("Caused by a fall")
                    .withStatus("completed"))
            .build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz").withPhone("95352563")
            .withRevenue("0.00f").withEmail("heinz@example.com").withAddress("wall street")
            .withNote("Has dementia").build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier").withPhone("87652533")
            .withRevenue("0.00f").withEmail("cornelia@example.com").withAddress("10th street")
            .withTags("friends").withNote("Has diabetes").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer").withPhone("9482224")
            .withRevenue("0.00f").withEmail("werner@example.com").withAddress("michegan ave")
            .withNote("Has wrist pain").build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withRevenue("0.00f").withEmail("lydia@example.com").withAddress("little tokyo")
            .withNote("Has migraines").build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best").withPhone("9482442")
            .withRevenue("0.00f").withEmail("anna@example.com").withAddress("4th street")
            .withInsurances(new Insurance(InsuranceType.HEALTH, "Prudential")).withNote("Has covid").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withEmail("stefan@example.com").withAddress("little india").withNote("Has heart disease").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withEmail("hans@example.com").withAddress("chicago ave").withNote("Has had a hip replacement").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY).withRevenue(VALID_REVENUE_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).withNote("").build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB).withRevenue(VALID_REVENUE_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).withNote("").build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code InsurancePal} with all the typical persons.
     */
    public static InsurancePal getTypicalAddressBook() {
        InsurancePal ab = new InsurancePal();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
