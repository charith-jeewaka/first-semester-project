package lk.ijse.florist_pos.final_project.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SystemUserDto {
    private int userId;
    private String userName;
    private String password;
    private String userRole;
    private String userMobile;
    private String userEmail;
    private String userNic;

}
