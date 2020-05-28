package viewDocuments;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.HashMap;

public class DropdownAdvancedSearch {
    private RadioButton documentNumber;
    private RadioButton orderNumber;
    private RadioButton creationDate;
    private RadioButton startDate;
    private RadioButton customer;
    private RadioButton description;

    /**
     * Initializes radio buttons and adds text to them.
     */
    public DropdownAdvancedSearch() {
        this.documentNumber = new RadioButton("Document number");
        this.orderNumber = new RadioButton("Order number");
        this.creationDate = new RadioButton("Creation date");
        this.startDate = new RadioButton("Start date");
        this.customer = new RadioButton("Customer");
        this.description = new RadioButton("Description");
    }

    /**
     * Open a new stage that allows to select 6 search parameters.
     */
    public void display() {
        //Create stage and specify that it needs to be closed before a previous stage can be used again.
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        Button confirm = new Button("confirm");
        confirm.setOnAction((e) -> {
            stage.close();
        });

        //Todo: save when closed

        VBox choiceContainer = new VBox();
        choiceContainer.getChildren().addAll(documentNumber, orderNumber, creationDate, startDate, customer, description, confirm);

        Scene popup = new Scene(choiceContainer, 200, 150);

        stage.setScene(popup);
        stage.showAndWait();
    }

    public HashMap<String, Boolean> getSelections() {
        HashMap<String, Boolean> selections = new HashMap();

        selections.put("documentNumber", documentNumber.isSelected());
        selections.put("orderNumber", orderNumber.isSelected());
        selections.put("creationDate", creationDate.isSelected());
        selections.put("startDate", startDate.isSelected());
        selections.put("customer", customer.isSelected());
        selections.put("description", description.isSelected());

        return selections;
    }
}
