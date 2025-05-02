package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerModel {
    public String getNextCustomerId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select customer_id from customer order by customer_id desc limit 1");
        char tableCharacter = 'C'; // Use any character Ex:- customer table for C, item table for I
        if (resultSet.next()) {
            String lastId = resultSet.getString(1); // "C001"
            String lastIdNumberString = lastId.substring(1); // "001"
            int lastIdNumber = Integer.parseInt(lastIdNumberString); // 1
            int nextIdNUmber = lastIdNumber + 1; // 2
            String nextIdString = String.format(tableCharacter + "%03d", nextIdNUmber); // "C002"
            return nextIdString;
        }
        // No data recode in table so return initial primary key
        return tableCharacter + "001";
    }
}
