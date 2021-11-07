package seedu.insurancepal.model.person;

public enum InsuranceType {
    LIFE("Life"),
    HEALTH("Health"),
    GENERAL("General");

    private String typeName;
    private InsuranceType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }
}
