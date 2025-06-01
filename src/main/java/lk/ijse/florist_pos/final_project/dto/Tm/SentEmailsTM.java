package lk.ijse.florist_pos.final_project.dto.Tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class SentEmailsTM {
    private String recipientEmail;
    private String subject;
    private String body;
    private String timeStamp;
    private JFXButton viewButton;
    private JFXButton deleteButton;
}
