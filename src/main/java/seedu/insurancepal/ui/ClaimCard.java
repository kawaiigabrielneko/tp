package seedu.insurancepal.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.person.Name;

/**
 * An UI component that displays information of a {@code Claim}.
 */
public class ClaimCard extends UiPart<Region> {

    private static final String FXML = "ClaimListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on InsurancePal level 4</a>
     */

    public final Claim claim;

    public final Name name;

    @FXML
    private HBox cardPane;
    @FXML
    private Label nameToDisplay;
    @FXML
    private Label title;
    @FXML
    private Label description;
    @FXML
    private Label status;
    @FXML
    private VBox cardBox;


    /**
     * Creates a {@code ClaimCard} with the given {@code Claim} and index to display.
     */
    public ClaimCard (Claim claim, Name name) {
        super(FXML);
        this.claim = claim;
        this.name = name;
        nameToDisplay.setText("Person: " + name.fullName);
        title.setText(claim.getTitle().toString());
        description.setText("Description: " + claim.getDescription().toString());
        status.setText(claim.getStatus().toString());
    }



    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ClaimCard)) {
            return false;
        }

        // state check
        ClaimCard card = (ClaimCard) other;
        return claim.equals(card.claim)
                && name.equals(card.name);
    }
}
