package seedu.insurancepal.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.insurancepal.commons.core.LogsCenter;
import seedu.insurancepal.model.client.Client;

/**
 * Panel containing the list of persons.
 */
public class AppointmentListPanel extends UiPart<Region> {
    private static final String FXML = "AppointmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(AppointmentListPanel.class);

    @FXML
    private ListView<Client> personListView;

    /**
     * Creates a {@code AppointmentListPanel} with the given {@code ObservableList}.
     */
    public AppointmentListPanel(ObservableList<Client> clientList) {
        super(FXML);
        personListView.setItems(clientList);
        personListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code AppointmentCard}.
     */
    class AppointmentListViewCell extends ListCell<Client> {
        @Override
        protected void updateItem(Client client, boolean empty) {
            super.updateItem(client, empty);

            if (empty || client == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(client, getIndex() + 1).getRoot());
            }
        }
    }

}
