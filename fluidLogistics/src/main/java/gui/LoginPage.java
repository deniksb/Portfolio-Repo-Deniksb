package gui;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LoginPage extends General{

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Get general Layout
        String headerText = "Documents";
        BorderPane generalStructure = super.setUpGeneralStructure(headerText);

        //Create and add components for documents page
        BorderPane documentsLayout = new BorderPane();
        super.createComponents(documentsLayout);
        generalStructure.setCenter(documentsLayout);
        Scene documentScene = new Scene(generalStructure, 200,200);

        //Show Layout
        primaryStage.setScene(documentScene);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }
    private void createComponents(BorderPane documentsLayout) {

        //Todo: add the documents + layout for documents
    }
}

