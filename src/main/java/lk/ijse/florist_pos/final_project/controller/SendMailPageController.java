package lk.ijse.florist_pos.final_project.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class SendMailPageController {

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

    @FXML
    public void initialize() {
        stkLoadingEffect.setVisible(false); // Hide spinner initially
    }

    public void btnSendOnAction(ActionEvent actionEvent) {
        // Show spinner
        stkLoadingEffect.setVisible(true);
        btnSend.setDisable(true); // Optional: disable send button while sending

        // Run sending in a background thread to avoid freezing UI
        new Thread(() -> {
            try {
                String fromEmail = "wickramasinghajeewaka@gmail.com";
                String password = "oddl ulsr ttva krqw"; // Your app password here

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
                    alert.showAndWait();

                    txtSubject.clear();
                    txtTo.clear();
                    txaBody.clear();
                });

            } catch (Exception e) {
                e.printStackTrace();

                // On failure: update UI on JavaFX Application Thread
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
}
