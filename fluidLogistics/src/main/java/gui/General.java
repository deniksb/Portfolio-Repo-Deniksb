package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import java.util.LinkedHashMap;


public abstract class General extends Application {

    /**
     * Set up the general structure of each site, where someone is logged in.
     * @param headerText Text, the header should display
     * @return A BorderPane Layout with a header and sidebar.
     */
    public BorderPane setUpGeneralStructure(String headerText) {
        //Create components
        VBox header = createHeader(headerText);
        VBox sideBar = createSideBar();

        //add Components to a BorderPane Layout
        BorderPane generalStructure = new BorderPane();

        generalStructure.topProperty().setValue(header);
        generalStructure.leftProperty().setValue(sideBar);
        generalStructure.setAlignment(sideBar, Pos.BOTTOM_LEFT);

        return generalStructure;
    }

    /**
     * Create a Label that serves as header of the page.
     * @param headerText The Text in the label
     * @return A styled label.
     */
    private VBox createHeader(String headerText) {
        //Create components
        Label header = new Label(headerText);
        Label spacer = new Label("");

        //Style actual header
        Font headerFont = new Font("Arial", 40);
        header.setFont(headerFont);
        header.setStyle("-fx-font-weight: bold");

        Insets headerDown = new Insets(20, 0, 0, 0);
        header.setPadding(headerDown);

        //Position and add spacing
        spacer.setPrefHeight(50);
        VBox spacerContainer = new VBox();
        spacerContainer.getChildren().addAll(header, spacer);

        spacerContainer.setAlignment(Pos.CENTER);
        return spacerContainer;
    }

    /**
     * Create the sidebar and give it functionality.
     * @return A VBox with the sidebar components in it.
     */
    private VBox createSideBar() {
        //Create sidebar components
        LinkedHashMap<String, Label> sidebarComponents = new LinkedHashMap();
        createSideBarComponents(sidebarComponents);

        //Change style of sidebar components
        Font sideBarFont = new Font("Arial", 24);
        setFontLabel(sideBarFont, sidebarComponents);

        Insets sideBarPadding = new Insets(0, 150, 30, 15);
        setPaddingLabel(sideBarPadding, sidebarComponents);

        setBoldLabel(sidebarComponents);

        //Add functionality
        setFunctionality(sidebarComponents);

        //Set vertical layout for sidebar
        VBox sideBar = new VBox();
        sideBar.getChildren().addAll(sidebarComponents.values());

        return sideBar;
    }

    /**
     * Create all the sidebar components and put them in the given HashMap.
     * @param sidebarComponents HashMap to receive the components.
     */
    private void createSideBarComponents(LinkedHashMap<String, Label> sidebarComponents) {
        sidebarComponents.put("dashboard", new Label("Dashboard"));
        sidebarComponents.put("documents", new Label("Documents"));
        //Todo: My orders shouldn't be seen by everyone.
        //this.addCustomComponents(sidebarComponents);
        sidebarComponents.put("myOrders", new Label("My Orders"));
        sidebarComponents.put("settings", new Label("Settings"));
        sidebarComponents.put("logout", new Label("Logout"));
    }

    /**
     * Add given padding to given Labels.
     * @param padding Amount of padding.
     * @param guiComponents Labels to receive padding.
     */
    private void setPaddingLabel(Insets padding, LinkedHashMap<String, Label> guiComponents) {
        for(Label l : guiComponents.values()) {
            l.setPadding(padding);
        }
    }

    /**
     * Apply given font to given Labels.
     * @param font Font to be applied.
     * @param guiComponents Labels to receive font.
     */
    private void setFontLabel(Font font, LinkedHashMap<String, Label> guiComponents) {
        for(Label l : guiComponents.values()) {
            l.setFont(font);
        }
    }

    /**
     * Give every given Label a bold font
     * @param guiComponents Labels to receive bold font.
     */
    private void setBoldLabel(LinkedHashMap<String, Label> guiComponents) {
        for(Label l : guiComponents.values()) {
            l.setStyle("-fx-font-weight: bold");
        }
    }

    private void setFunctionality(LinkedHashMap<String, Label> guiComponents) {
        //todo: Add the functionality
    }
    /*
    private void addCustomComponents(LinkedHashMap<String, Label> sidebarComponents){
    bla bla usergroup check
    }
     */
}
