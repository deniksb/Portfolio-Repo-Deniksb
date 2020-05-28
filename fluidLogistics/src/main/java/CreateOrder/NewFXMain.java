/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CreateOrder;


import java.time.LocalDate;
import java.util.Date;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;



public class NewFXMain extends Application {

    Stage window;
    Scene scene1, scene2;

    @Override
    public void start(Stage primaryStage)  {
        
        //FIRST SCENE / CREATE ORDER

        window = primaryStage;
        Button btn = new Button();
        TextField field = new TextField();
        btn.setText("Create Order");
        Label pickupLabel = new Label("Enter Pickup Information");
        TextField town = new TextField("Enter Town");
        TextField country = new TextField("Enter Country");
        TextField postalCode = new TextField("Enter PostalCode");
        TextField street = new TextField("Enter Street");
        TextField housenr = new TextField("Enter House Number");
        TextField addition = new TextField("Enter Addition");

        Label dropoffLabel = new Label("Enter Dropoff Information");
        TextField town2 = new TextField("Enter Town");
        TextField country2 = new TextField("Enter Country");
        TextField postalCode2 = new TextField("Enter PostalCode");
        TextField street2 = new TextField("Enter Street");
        TextField housenr2 = new TextField("Enter House Number");
        TextField addition2 = new TextField("Enter Addition");
        TextField weight = new TextField("Enter Weight");

        DatePicker date = new DatePicker();
        TextField product = new TextField("Enter Product Name");
        ChoiceBox productType = new ChoiceBox(FXCollections.observableArrayList(
                "HAZARDOUS", "NONHAZARDOUS", "FOOD")
        );
        //scene2 declarations
        Label pickUpLabel = new Label("Pickup Address: ");
         Label pickUpLabelText = new Label();
         Label dropOffLabel = new Label("Dropoff Address");
         Label dropOffLabelText = new Label();
         Label nameOfProduct = new Label("Name Of Product");
         Label nameOfProductText = new Label();
         Label typeOfProduct = new Label("Type Of Product");
         Label typeOfProductText = new Label();
         Label dateLabel = new Label("Date");
         Label dateLabelText = new Label();
         Label weightOfProduct = new Label("Weight");
         Label weightOfProductText = new Label();
         
        

        btn.setOnAction((ActionEvent event) -> {
            Address pickupAddress = new Address(country.getText(), town.getText(), postalCode.getText(), street.getText(), housenr.getText(), addition.getText());
            Address dropoffAddress = new Address(country2.getText(), town2.getText(), postalCode2.getText(), street2.getText(), housenr2.getText(), addition2.getText());

            String productName = product.getText();
            String productTyp = (String) productType.getValue();
            ProductType productTypeThing = ProductType.valueOf(productTyp);

            String weightInfo = weight.getText();
            LocalDate dateInfo = date.getValue();

            Order order = new Order(pickupAddress, dropoffAddress, productName, productTypeThing, weightInfo, dateInfo);
            System.out.println(order.toString());
            
            pickUpLabelText.setText(pickupAddress.toString());
            dropOffLabelText.setText(dropoffAddress.toString());
            nameOfProductText.setText(productName);
            typeOfProductText.setText(productTyp);
            dateLabelText.setText(dateInfo.toString());
            weightOfProductText.setText(weightInfo);
            window.setScene(scene2);
        });

        GridPane grid = new GridPane();
        StackPane root = new StackPane(grid);
        GridPane pickup = new GridPane();
        pickup.setVgap(10);
        pickup.setHgap(10);
        GridPane dropoff = new GridPane();
        dropoff.setVgap(10);
        dropoff.setHgap(10);

        pickup.add(pickupLabel, 0, 0);
        pickup.add(country, 0, 1);
        pickup.add(town, 1, 1);
        pickup.add(postalCode, 0, 2);
        pickup.add(street, 1, 2);
        pickup.add(housenr, 0, 3);
        pickup.add(addition, 1, 3);

        dropoff.add(dropoffLabel, 0, 0);
        dropoff.add(country2, 0, 1);
        dropoff.add(town2, 1, 1);
        dropoff.add(postalCode2, 0, 2);
        dropoff.add(street2, 1, 2);
        dropoff.add(housenr2, 0, 3);
        dropoff.add(addition2, 1, 3);

        grid.setVgap(15);
        grid.setHgap(15);
        grid.add(pickup, 0, 0);
        grid.add(dropoff, 0, 1);
        grid.add(date, 0, 2);
        grid.add(product, 0, 3);
        grid.add(productType, 0, 4);
        grid.add(weight, 0, 5);
        grid.add(btn, 0, 6);
        grid.setAlignment(Pos.CENTER);
        StackPane.setAlignment(btn, Pos.BOTTOM_CENTER);

        scene1 = new Scene(root, 1000, 800);

       
        
         //SECOND SCENE / PREVIEW ORDER
         
         Button submitBut = new Button("Submit");
         Button editBut = new Button("Edit");
         Button cancelBut = new Button("Cancel");
         GridPane grid2 = new GridPane();
         grid2.setHgap(10);
         grid2.setVgap(10);
         grid2.add(pickUpLabel, 0, 0);
         grid2.add(pickUpLabelText, 1, 0);
         grid2.add(dropOffLabel, 0, 1);
         grid2.add(dropOffLabelText, 1, 1);
         grid2.add(nameOfProduct, 0, 2);
         grid2.add(nameOfProductText, 1, 2);
         grid2.add(typeOfProduct, 0, 3);
         grid2.add(typeOfProductText, 1, 3);
          grid2.add(dateLabel, 0, 4);
         grid2.add(dateLabelText, 1, 4);
           grid2.add(weightOfProduct, 0, 5);
         grid2.add(weightOfProductText, 1, 5);
         grid2.add(submitBut, 0, 6);
         grid2.add(editBut, 1, 6);
         grid2.add(cancelBut, 2, 6);
          StackPane layout2 = new StackPane();
          layout2.getChildren().add(grid2);
          
          //submitBut.setOnAction(eh);
          
          editBut.setOnAction(e -> window.setScene(scene1));
          cancelBut.setOnAction(e -> window.close());
          
          grid2.setAlignment(Pos.CENTER);
        scene2 = new Scene(layout2, 1000, 800);
        

        primaryStage.setTitle("Create Order");
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}

