package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import lk.ijse.florist_pos.final_project.dto.FlowerWasteDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerWasteModel {
    public String getNextWasteId() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement pst = connection.prepareStatement(
//                "select customer_id from customer order by customer_id desc limit 1"
//        );
//        ResultSet resultSet = pst.executeQuery();
        ResultSet resultSet = CrudUtil.execute("select wasted_id from flower_waste order by wasted_id desc limit 1");
        char tableCharacter = 'W'; // Use any character Ex:- customer table for C, item table for I
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

    public ArrayList<FlowerWasteDto> getAllWFlower() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from flower_waste");
        ArrayList<FlowerWasteDto> flowerWDTOArrayList = new ArrayList<>();
        while (resultSet.next()){
            FlowerWasteDto flowerWDto = new FlowerWasteDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            flowerWDTOArrayList.add(flowerWDto);
        }
        return flowerWDTOArrayList;
    }
}
