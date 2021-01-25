package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import bo.BoFactory;
import bo.custom.CustomerBO;
import dto.CustomerDTO;
import view.tm.CustomerTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class CustomerFormController {
    public TextField txtId;
    public AnchorPane root;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtSalary;
    public TextField txtSearch;
    public TableView<CustomerTM> tableCustomer;
    public TableColumn colCustomerId;
    public TableColumn colCustomerName;
    public TableColumn colAddress;
    public TableColumn colSalary;
    public TableColumn colOperate;
    CustomerBO bo;

    public void initialize() throws Exception {



        bo = BoFactory.getInstance()
                .getBo(BoFactory.BOType.CUSTOMER);

        

        colCustomerId.setCellValueFactory(new PropertyValueFactory("id"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory("salary"));
        colOperate.setCellValueFactory(new PropertyValueFactory("btn"));

        loadAllCustomers();// Alt+ Enter

        //------------------------------ set Listener-------------

        tableCustomer.getSelectionModel().selectedItemProperty().
                addListener((observable, oldValue, newValue) -> {
                    setData(newValue);
                });

        //------------------------------ set Listener-------------
    }



    private void setData(CustomerTM tm) {
        txtId.setText(tm.getId());
        txtName.setText(tm.getName());
        txtAddress.setText(tm.getAddress());
        txtSalary.setText(tm.getSalary()+"");
    }

    private void loadAllCustomers() throws Exception {
        ObservableList<CustomerTM> tmList =
                FXCollections.observableArrayList();
        List<CustomerDTO> allCustomers = bo.getAllCustomers();
        for (CustomerDTO dto : allCustomers
        ) {
            Button btn = new Button("Delete");
            CustomerTM tm = new CustomerTM(dto.getId(), dto.getName(),
                    dto.getAddress(), dto.getSalary(), btn);
            tmList.add(tm);
            btn.setOnAction((e) -> {
                try {

                    ButtonType ok = new ButtonType("OK",
                            ButtonBar.ButtonData.OK_DONE);
                    ButtonType no = new ButtonType("NO",
                            ButtonBar.ButtonData.CANCEL_CLOSE);

                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                            "Are You Sure ?", ok, no);
                    Optional<ButtonType> result = alert.showAndWait();
                    if (result.orElse(no) == ok) {
                        if (bo.deleteCustomer(tm.getId())) {
                            new Alert(Alert.AlertType.CONFIRMATION,
                                    "Deleted", ButtonType.OK).show();
                            loadAllCustomers();
                            return;
                        }
                        new Alert(Alert.AlertType.WARNING,
                                "Try Again", ButtonType.OK).show();
                    } else {
                        //no
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        }
        tableCustomer.setItems(tmList);

    }

    public void newOnAction(ActionEvent actionEvent) {
    }

    public void saveOnAction(ActionEvent actionEvent) throws Exception {

        boolean isSaved = bo.saveCustomer(
                new CustomerDTO(txtId.getText(),
                        txtName.getText(),
                        txtAddress.getText(),
                        Double.parseDouble(txtSalary.getText()))
        );
        System.out.println(isSaved);
    }

    public void backToHomeOnAction(MouseEvent mouseEvent) throws IOException {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/MainForm.fxml"))));
    }


    public void getDataOnAction(ActionEvent actionEvent) throws Exception {

        CustomerDTO dto = bo.getCustomer(txtId.getText());
        if (dto != null) {
            txtId.setText(dto.getId());
            txtName.setText(dto.getName());
            txtAddress.setText(dto.getAddress());
            txtSalary.setText(dto.getSalary() + "");
        }

    }
}
