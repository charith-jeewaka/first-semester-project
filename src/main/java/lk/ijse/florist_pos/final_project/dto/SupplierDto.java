package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDto {
    private int supplierId;
    private String supplierName;
    private String supplierEmail;
    private String contact;
}
