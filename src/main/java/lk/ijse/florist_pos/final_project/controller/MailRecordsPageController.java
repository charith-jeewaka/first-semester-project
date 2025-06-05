package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import lk.ijse.florist_pos.final_project.dto.SentEmailDto;
import lk.ijse.florist_pos.final_project.dto.Tm.SentEmailsTM;
import lk.ijse.florist_pos.final_project.model.SentEmailModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class MailRecordsPageController implements Initializable {

    public TableView<SentEmailsTM> tblMailLog;
    public TableColumn<SentEmailsTM, String> colRecipientMail;
    public TableColumn<SentEmailsTM, String> colSubject;
    public TableColumn<SentEmailsTM, String> colBody;
    public TableColumn<SentEmailsTM, String> colSentAt;
    public TableColumn<SentEmailsTM, JFXButton> colMailView;
    public TableColumn<SentEmailsTM, JFXButton> colMailDelete;
    public AnchorPane ancMailLog;
    public ImageView imageView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        imageView.fitWidthProperty().bind(ancMailLog.widthProperty());
//        imageView.fitHeightProperty().bind(ancMailLog.heightProperty());
        setCellValues();
        loadAllEmails();
    }

    private void setCellValues() {
        colRecipientMail.setCellValueFactory(new PropertyValueFactory<>("recipientEmail"));
        colSubject.setCellValueFactory(new PropertyValueFactory<>("subject"));
        colBody.setCellValueFactory(new PropertyValueFactory<>("body"));
        colSentAt.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));
        colMailView.setCellValueFactory(new PropertyValueFactory<>("viewButton"));
        colMailDelete.setCellValueFactory(new PropertyValueFactory<>("deleteButton"));
    }

    private void loadAllEmails() {
        ObservableList<SentEmailsTM> tmList = FXCollections.observableArrayList();

        try {
            List<SentEmailDto> allEmails = SentEmailModel.getAllSentEmails();
            for (SentEmailDto dto : allEmails) {
                JFXButton viewBtn = new JFXButton("View");
                viewBtn.setStyle("-fx-background-color: #2ed573; -fx-text-fill: #ffffff;");
                viewBtn.setOnAction(e -> showEmailPopup(dto.getBody()));

                JFXButton deleteBtn = new JFXButton("Delete");
                deleteBtn.setStyle("-fx-background-color: #ff6b81; -fx-text-fill: white;");
                deleteBtn.setOnAction(e -> {
                    try {
                        boolean isDeleted = SentEmailModel.deleteSentEmail(dto.getTimeStamp());
                        if (isDeleted) loadAllEmails();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                });

                SentEmailsTM tm = new SentEmailsTM(
                        dto.getRecipientEmail(),
                        dto.getSubject(),
                        dto.getBody(),
                        dto.getTimeStamp(),
                        viewBtn,
                        deleteBtn
                );
                tmList.add(tm);
            }

            tblMailLog.setItems(tmList);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void showEmailPopup(String body) {
        Stage popupStage = new Stage();
        popupStage.initModality(Modality.APPLICATION_MODAL);
        popupStage.setTitle("Email Body");

        TextArea textArea = new TextArea(body);
        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setStyle("-fx-font-size: 14; -fx-control-inner-background: #f1f2f6;");

        VBox layout = new VBox(textArea);
        layout.setSpacing(10);
        layout.setStyle("-fx-padding: 10;");

        Scene scene = new Scene(layout, 400, 300);
        popupStage.setScene(scene);
        popupStage.showAndWait();
    }
}
