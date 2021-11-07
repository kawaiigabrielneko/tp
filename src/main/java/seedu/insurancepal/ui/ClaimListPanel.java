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
import seedu.insurancepal.model.client.Client;

/**
 * Panel containing the list of Claims and Names.
 */
public class ClaimListPanel extends UiPart<Region> {
    private static final String FXML = "ClaimListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ClaimListPanel.class);
    private ObservableList<Client> clientList;
    private ObservableList<Pair<Claim, Client>> claimList;

    @FXML
    private ListView<Pair<Claim, Client>> claimListView;

    /**
     * Creates a {@code ClaimsListPanel} with the given {@code ObservableList}.
     */
    public ClaimListPanel(ObservableList<Client> clientList) {
        super(FXML);
        this.clientList = clientList;
        ObservableList<Pair<Claim, Client>> claimList = FXCollections.observableArrayList();
        this.claimList = claimList;
        clientList.addListener(new ListChangeListener<Client>() {
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
    class ClaimListViewCell extends ListCell<Pair<Claim, Client>> {
        @Override
        protected void updateItem(Pair<Claim, Client> claim, boolean empty) {
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
        clientList.stream().forEach(
            person -> person.getClaims().forEach(
                claim -> claimList.add(new Pair<>(claim, person))
            ));
        claimList = claimList.sorted(new ClaimPersonPairComparator());
        claimListView.setItems(claimList);
        claimListView.setCellFactory(listView -> new ClaimListViewCell());
    }

    private class ClaimPersonPairComparator implements Comparator<Pair<Claim, Client>> {

        @Override
        public int compare(Pair<Claim, Client> firstPair, Pair<Claim, Client> secondPair) {
            if (firstPair.getKey().getStatus().toString().equals("PENDING")
                    && secondPair.getKey().getStatus().toString().equals("COMPLETED")) {
                return 0;
            }
            return 1;
        }
    }

}
