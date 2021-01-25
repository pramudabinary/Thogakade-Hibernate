package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class MainFormController {
    public AnchorPane root;

//    private void setUI(String location) throws IOException {
//        Stage stage = (Stage) root.getScene().getWindow();
//        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/" + location))));
//    }

    public void openCustomerForm(ActionEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("view/CustomerForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void openItemForm(ActionEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("view/ItemForm.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void openOrderForm(MouseEvent mouseEvent) {
    }

    public void openPlaceOrderForm(MouseEvent mouseEvent) {
    }



}
