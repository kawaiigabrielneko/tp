package seedu.insurancepal.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.insurancepal.commons.exceptions.IllegalValueException;
import seedu.insurancepal.model.InsurancePal;
import seedu.insurancepal.model.ReadOnlyInsurancePal;
import seedu.insurancepal.model.person.Person;

/**
 * An Immutable InsurancePal that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableInsurancePal {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableInsurancePal} with the given persons.
     */
    @JsonCreator
    public JsonSerializableInsurancePal(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyInsurancePal} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableInsurancePal}.
     */
    public JsonSerializableInsurancePal(ReadOnlyInsurancePal source) {
        persons.addAll(source.getPersonList().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code InsurancePal} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public InsurancePal toModelType() throws IllegalValueException {
        InsurancePal insurancePal = new InsurancePal();
        for (JsonAdaptedPerson jsonAdaptedPerson : persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (insurancePal.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            insurancePal.addPerson(person);
        }
        return insurancePal;
    }

}
