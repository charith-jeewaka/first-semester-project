package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.dto.SentEmailDto;
import lk.ijse.florist_pos.final_project.model.SentEmailModel;
import lk.ijse.florist_pos.final_project.model.SupplierModel;
import lk.ijse.florist_pos.final_project.util.MailConfigLoader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

public class SendMailPageController {

    @FXML
    public JFXComboBox<String> cmbTemplates;
    @FXML
    public JFXComboBox<String> cmbSubject;
    @FXML
    public JFXComboBox<String> cmbSupplierEmail;
    @FXML
    public JFXButton btnClear;
    @FXML
    public AnchorPane ancMailSend;
    @FXML
    public JFXButton btnHistory;
    public ImageView imageview;
    @FXML
    private JFXButton btnSend;
    @FXML
    private TextArea txaBody;
    @FXML
    private TextField txtSubject;
    @FXML
    private TextField txtTo;
    @FXML
    private ProgressIndicator stkLoadingEffect;

    public ObservableList<String> sampleSubjects = FXCollections.observableArrayList(
            "Request Supply","Thank You for Timely Delivery","Notice of Price Adjustment","Request for Quotation"
            ,"Request for Product Samples","Invitation to Supplier Meeting","Updated Terms and Conditions"
    );

    public void initialize() {
        imageview.fitWidthProperty().bind(ancMailSend.widthProperty());
        imageview.fitHeightProperty().bind(ancMailSend.heightProperty());

        stkLoadingEffect.setVisible(false);

        ancMailSend.setOnKeyPressed(event -> {
            if (event.getCode() == javafx.scene.input.KeyCode.ENTER) {
                btnSend.fire();
                event.consume();
            }
        });

        try {
            List<String> emails = SupplierModel.getAllSupplierEmails();
            cmbSupplierEmail.setItems(FXCollections.observableArrayList(emails));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cmbSupplierEmail.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtTo.setText(newValue);
            }
        });

        cmbSubject.setItems(sampleSubjects);

        cmbSubject.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtSubject.setText(newValue.toString());
                txaBody.setText(getTemplateForSubject(newValue.toString()));
            }
        });

    }

    public void btnSendOnAction(ActionEvent actionEvent) {

        Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to send this email?");
        Optional<ButtonType> result = confirmation.showAndWait();
        if (result.isEmpty() || result.get() != ButtonType.OK) {
            return;
        }

        stkLoadingEffect.setVisible(true);
        btnSend.setDisable(true);

        // Run sending in a background thread to avoid freezing UI
        new Thread(() -> {
            try {
                String fromEmail = MailConfigLoader.getEmail();
                String password = MailConfigLoader.getPassword();

                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(fromEmail, password);
                    }
                });

                Message mimeMessage = new MimeMessage(session);
                mimeMessage.setFrom(new InternetAddress(fromEmail));
                mimeMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(txtTo.getText()));
                mimeMessage.setSubject(txtSubject.getText());
                mimeMessage.setText(txaBody.getText());

                Transport.send(mimeMessage);

                // On success: update UI on JavaFX Application Thread
                Platform.runLater(() -> {
                    stkLoadingEffect.setVisible(false);
                    btnSend.setDisable(false);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Email Sent");
                    alert.setHeaderText(null);
                    alert.setContentText("The email has been sent successfully.");
                    SentEmailDto emailDTO = new SentEmailDto(
                            txtTo.getText(),
                            txtSubject.getText(),
                            txaBody.getText(),
                            null

                    );
                    SentEmailModel.saveEmail(emailDTO);
                    alert.showAndWait();

                    txtSubject.clear();
                    txtTo.clear();
                    txaBody.clear();
                });

            } catch (Exception e) {
                e.printStackTrace();

                Platform.runLater(() -> {
                    stkLoadingEffect.setVisible(false);
                    btnSend.setDisable(false);
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Failed to send email.");
                    alert.setContentText(e.getMessage());
                    alert.showAndWait();
                    txtTo.clear();
                    txtSubject.clear();
                    txaBody.clear();
                });
            }
        }).start();
    }

    public void setEmail(String email) {
        txtTo.setText(email);
    }

    private String getTemplateForSubject(String subject) {
        return switch (subject) {
            case "Request Supply" -> """
            Dear [Supplier Name],

            We kindly request the supply of the following items:

            - Item Name: [Insert Item]
            - Quantity: [Insert Quantity]

            Kindly confirm availability and expected delivery time.

            Best regards,
            [Your Name]
            [Your Company Name]
            """;

            case "Thank You for Timely Delivery" -> """
            Dear [Supplier Name],

            We appreciate your prompt and timely delivery of our recent order.

            Your consistent service is highly valued.

            Thank you once again.

            Sincerely,
            [Your Name]
            [Your Company Name]
            """;

            case "Notice of Price Adjustment" -> """
            Dear [Supplier Name],

            Please note that there has been a recent adjustment in pricing due to market changes.

            Attached you’ll find the updated price list.

            Thank you for your understanding.

            Best regards,
            [Your Name]
            [Your Company Name]
            """;

            case "Request for Quotation" -> """
            Dear [Supplier Name],

            We are currently preparing to place an order and would appreciate a quotation for the following:

            - Item: [Insert Item]
            - Quantity: [Insert Quantity]

            Please include your best prices, payment terms, and delivery timeline.

            Regards,
            [Your Name]
            [Your Company Name]
            """;

            case "Request for Product Samples" -> """
            Dear [Supplier Name],

            We are interested in evaluating your products and would like to request the following samples:

            - Product: [Insert Product]

            Kindly advise if there are any costs involved.

            Best regards,
            [Your Name]
            [Your Company Name]
            """;

            case "Invitation to Supplier Meeting" -> """
            Dear [Supplier Name],

            You are invited to attend a supplier meeting on:

            - Date: [Insert Date]
            - Time: [Insert Time]
            - Location: [Insert Location or Zoom Link]

            The agenda will include upcoming requirements and collaboration goals.

            Kindly confirm your participation.

            Best regards,
            [Your Name]
            [Your Company Name]
            """;

            case "Updated Terms and Conditions" -> """
            Dear [Supplier Name],

            Please find attached the updated terms and conditions applicable from [Effective Date].

            Kindly review and acknowledge your acceptance.

            Thank you.

            Best regards,
            [Your Name]
            [Your Company Name]
            """;

            default -> "";
        };
    }


    public void clearTxaOnAction(ActionEvent actionEvent) {
        txaBody.clear();
    }

    public void emailHistoryOnAction(ActionEvent actionEvent) throws IOException {

        try {
            ancMailSend.getChildren().clear();

            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/View/MailRecords.fxml"));

            anchorPane.prefWidthProperty().bind(ancMailSend.widthProperty());
            anchorPane.prefHeightProperty().bind(ancMailSend.heightProperty());

            ancMailSend.getChildren().add(anchorPane);
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
        }

    }
}
