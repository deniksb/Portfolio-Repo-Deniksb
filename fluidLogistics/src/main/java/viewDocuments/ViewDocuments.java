package viewDocuments;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import gui.General;

public class ViewDocuments extends General {
    private TableView documentTable;

    /**
     * Pulls general application layout and delegates the creation of the page.
     * @param stage The main window to be shown.
     */
    @Override
    public void start(Stage stage) {
        //Get general Layout
        String headerText = "Documents";
        BorderPane generalStructure = super.setUpGeneralStructure(headerText);

        //Create and add components for documents page
        BorderPane documentsLayout = new BorderPane();
        createComponents(documentsLayout);
        generalStructure.setCenter(documentsLayout);
        Scene documentScene = new Scene(generalStructure, 200,200);

        //Show Layout
        stage.setScene(documentScene);
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * Manage the creation of the components of the page.
     * @param documentsLayout The BorderPane that gives structure to the whole documents page.
     */
    private void createComponents(BorderPane documentsLayout) {
        createTopComponents(documentsLayout);
        createDocumentsTable(documentsLayout);
        createSidePicker(documentsLayout);
        //Todo: add the documents + layout for documents
    }

    /**
     * Manage the creation of the components at the top of the page.
     * @param documentsLayout The BorderPane that gives structure to the whole documents page.
     */
    private void createTopComponents(BorderPane documentsLayout) {
        HBox upperLeftComponents = createMaxDocumentsSelector();
        HBox upperRightComponents = createSearchBar();

        //Spacer for gap between maxDocuments and search bar
        Label spacer = new Label();
        spacer.setPrefWidth(400);

        GridPane documentsTopLayout = new GridPane();
        documentsTopLayout.add(upperLeftComponents, 0, 0);
        documentsTopLayout.add(spacer, 1, 0);
        documentsTopLayout.add(upperRightComponents, 2, 0);

        documentsLayout.setTop(documentsTopLayout);
    }

    /**
     * Create and align the components for the maximum documents selector.
     * @return HBox containing the components.
     */
    private HBox createMaxDocumentsSelector() {
        //Todo: Add functionality for max documents
        Label maxDocumentsPerSide = new Label("Max documents per side:");
        TextField maxDocumentsPerSideInput = new TextField("100");

        HBox upperLeftComponents = new HBox();
        upperLeftComponents.getChildren().addAll(maxDocumentsPerSide, maxDocumentsPerSideInput);

        return upperLeftComponents;
    }

    /**
     * Create and align the component for the search bar.
     * @return HBox containing the components.
     */
    private HBox createSearchBar() {
        TextField searchBar = new TextField();
        Button startSearch = new Button("Search");
        Button advancedSearch = new Button("Advanced search");

        searchBar.setPromptText("Search");

        //Advanced search functionality Todo: use the selected items
        DropdownAdvancedSearch selector = new DropdownAdvancedSearch();

        advancedSearch.setOnAction( (e) -> {
            selector.display();
        });

        HBox upperRightComponents = new HBox();
        upperRightComponents.getChildren().addAll(searchBar, startSearch, advancedSearch);
        upperRightComponents.setSpacing(0.5);

        return upperRightComponents;
    }

    /**
     * Manage the creation of documents table layout.
     * @param documentsLayout The BorderPane that gives structure to the whole documents page.
     */
    private void createDocumentsTable(BorderPane documentsLayout) {
        documentTable = new TableView();
        createDocumentColumns();
        documentsLayout.setCenter(documentTable);
    }

    /**
     * Creation of the table columns.
     */
    private void createDocumentColumns() {
        TableColumn documentNumber = new TableColumn("Document\nnumber");
        TableColumn orderNumber = new TableColumn("Order\nnumber");
        TableColumn startDate = new TableColumn("Start date");
        TableColumn customer = new TableColumn("Customer");
        TableColumn description = new TableColumn("Description");
        TableColumn status = new TableColumn("Status");

        customer.setMinWidth(150);
        description.setMinWidth(650);
        documentTable.getColumns().addAll(documentNumber, orderNumber, startDate, customer, description, status);
    }

    /**
     * Create the controls at the bottom of the page.
     * @param documentsLayout The BorderPane that gives structure to the whole documents page.
     */
    private void createSidePicker(BorderPane documentsLayout) {
        //Creation of the components
        Button pageBefore = new Button("<");
        TextField enterPageNumber = new TextField("1");
        //Todo: enter custom max Site
        Label maxSites = new Label("/ " + "100");
        Button nextPage = new Button(">");

        //Add the components to the layout
        HBox alignControls = new HBox();
        alignControls.getChildren().addAll(pageBefore, enterPageNumber, maxSites, nextPage);

        documentsLayout.setBottom(alignControls);
    }
}
