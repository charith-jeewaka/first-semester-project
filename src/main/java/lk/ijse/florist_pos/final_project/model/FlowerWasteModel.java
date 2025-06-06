package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.FlowerWasteDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FlowerWasteModel {

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
