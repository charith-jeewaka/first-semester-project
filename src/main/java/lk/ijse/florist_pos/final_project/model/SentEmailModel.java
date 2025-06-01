package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import lk.ijse.florist_pos.final_project.dto.SentEmailDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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

    public static ArrayList<SentEmailDto> getAllSentEmails() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM sent_emails");
        ArrayList<SentEmailDto> sentEmailDtoArrayList = new ArrayList<>();
        while (resultSet.next()) {
            SentEmailDto sentEmailDto = new SentEmailDto(
                    resultSet.getString("recipient_email"),
                    resultSet.getString("subject"),
                    resultSet.getString("body"),
                    resultSet.getString("sent_at")
            );
            sentEmailDtoArrayList.add(sentEmailDto);
        }
        return sentEmailDtoArrayList;
    }

    public static boolean deleteSentEmail(String timeStamp) throws SQLException {
        return CrudUtil.execute("DELETE FROM sent_emails WHERE sent_at = ?", timeStamp);
    }
}
