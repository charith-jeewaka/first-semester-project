package lk.ijse.florist_pos.final_project.dto;

import javafx.fxml.FXML;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SentEmailDto {
        private String recipientEmail;
        private String subject;
        private String body;
        private String timeStamp;
    }

