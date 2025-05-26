package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.dto.SentEmailDto;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class SentEmailModel {

    public static boolean saveEmail(SentEmailDto dto) {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBConnection.getInstance().getConnection();
            String sql = "INSERT INTO sent_emails (recipient_email, subject, body) VALUES (?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, dto.getRecipientEmail());
            stm.setString(2, dto.getSubject());
            stm.setString(3, dto.getBody());
            return stm.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (stm != null) stm.close();
                // Do NOT close `con` here because it's shared!
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
