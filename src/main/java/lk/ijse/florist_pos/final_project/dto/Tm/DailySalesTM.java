package lk.ijse.florist_pos.final_project.dto.Tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class DailySalesTM {
    private String orderId;
    private String customerName;
    private double total;
    }
