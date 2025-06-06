package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {

    public static List<String> getAllSupplierEmails() throws SQLException {
        String sql = "SELECT supplier_e_mail FROM supplier";
        List<String> emails = new ArrayList<>();

        Connection con = DBConnection.getInstance().getConnection();
        try (PreparedStatement pstm = con.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                emails.add(rs.getString("supplier_e_mail"));
            }
        }

        return emails;
    }
}
