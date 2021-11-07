package seedu.insurancepal.ui;

import java.util.Comparator;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.model.claim.Claim;
import seedu.insurancepal.model.person.Person;

/**
 * Panel containing the list of Claims and Names.
 */
public class ClaimListPanel extends UiPart<Region> {
    private static final String FXML = "ClaimListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClaimListPanel.class);
    private ObservableList<Person> personList;
    private ObservableList<Pair<Claim, Person>> claimList;

    @FXML
    private ListView<Pair<Claim, Person>> claimListView;

    /**
     * Creates a {@code ClaimsListPanel} with the given {@code ObservableList}.
     */
    public ClaimListPanel(ObservableList<Person> personList) {
        super(FXML);
        this.personList = personList;
        ObservableList<Pair<Claim, Person>> claimList = FXCollections.observableArrayList();
        this.claimList = claimList;
        personList.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(ListChangeListener.Change change) {
                update();
            }
        });
        update();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Pair<Claim, Name>} using a {@code ClaimCard}.
     */
    class ClaimListViewCell extends ListCell<Pair<Claim, Person>> {
        @Override
        protected void updateItem(Pair<Claim, Person> claim, boolean empty) {
            super.updateItem(claim, empty);
            if (empty || claim == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ClaimCard(
                        claim.getKey(), claim.getValue().getName()).getRoot());
            }
        }
    }

    private void update() {
        claimList = FXCollections.observableArrayList();
        personList.stream().forEach(
            person -> person.getClaims().forEach(
                claim -> claimList.add(new Pair<>(claim, person))
            ));
        claimList = claimList.sorted(new ClaimPersonPairComparator());
        claimListView.setItems(claimList);
        claimListView.setCellFactory(listView -> new ClaimListViewCell());
    }

    private class ClaimPersonPairComparator implements Comparator<Pair<Claim, Person>> {

        @Override
        public int compare(Pair<Claim, Person> firstPair, Pair<Claim, Person> secondPair) {
            if (firstPair.getKey().getStatus().toString().equals("PENDING")
                    && secondPair.getKey().getStatus().toString().equals("COMPLETED")) {
                return 0;
            }
            return 1;
        }
    }

}
