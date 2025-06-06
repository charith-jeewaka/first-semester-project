package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import lk.ijse.florist_pos.final_project.dto.PlantDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlantModel {
    public String getNextPlantId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select plant_id from plant order by plant_id desc limit 1");
        char tableCharacter = 'P'; // Use any character Ex:- customer table for C, item table for I
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

    public ArrayList<PlantDto>getAllPlants() throws SQLException{
        ResultSet resultSet = CrudUtil.execute("select * from plant");
        ArrayList<PlantDto> plantDTOArrayList = new ArrayList<>();
        while (resultSet.next()){
            PlantDto plantDTO = new PlantDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
            plantDTOArrayList.add(plantDTO);
        }
        return plantDTOArrayList;
    }

    public boolean savePlant(PlantDto plantDTO) throws SQLException {
        return CrudUtil.execute("insert into plant (plant_id, plant_name, plant_height, plant_price, plant_varient, plant_available_qty) values (?,?,?,?,?,?)",
                plantDTO.getPlantId(),
                plantDTO.getPlantName(),
                plantDTO.getPlantHeight(),
                plantDTO.getPlantPrice(),
                plantDTO.getPlantVarient(),
                plantDTO.getPlantAvailableQty()
                );
    }

    public boolean updateCustomer(PlantDto plantDto) throws SQLException {
        return CrudUtil.execute(
                "UPDATE plant SET plant_id = ?, plant_name = ?, plant_height = ?, plant_price = ?, " +
                        "plant_varient = ?, plant_available_qty = ?, plant_registered_time = ? WHERE plant_id = ?;",
                plantDto.getPlantId(),
                plantDto.getPlantName(),
                plantDto.getPlantHeight(),
                plantDto.getPlantPrice(),
                plantDto.getPlantVarient(),
                plantDto.getPlantAvailableQty(),
                plantDto.getPlantRegisteredTime(),
                plantDto.getPlantId() // This is the WHERE condition ID
        );
    }

    public boolean deletePlant(String plantId) throws SQLException {
        return CrudUtil.execute(
                "delete from plant where plant_id=?",
                plantId
        );
    }

    public boolean reducePlantQty(Connection con, String plantId, int qty) throws SQLException {
        String sql = "UPDATE plant SET plant_available_qty = plant_available_qty - ? WHERE plant_id = ?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setInt(1, qty);
        ps.setString(2, plantId);
        return ps.executeUpdate() > 0;
    }

    public static boolean reduceQty(String plantId, int qtyToReduce, Connection connection) throws SQLException {
        String sql = "UPDATE plant SET plant_available_qty = plant_available_qty - ? WHERE plant_id = ? AND plant_available_qty >= ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setInt(1, qtyToReduce);
        pstm.setString(2, plantId);
        pstm.setInt(3, qtyToReduce);
        return pstm.executeUpdate() > 0;
    }
    public static int getTotalPlantQty() throws SQLException {
        String sql = "SELECT COUNT(plant_id) AS plant_count FROM plant";
        ResultSet rs = CrudUtil.execute(sql);
        int plantCount = 0;
        if (rs.next()) {
            plantCount = rs.getInt("plant_count");
        }
        return plantCount;
    }
}
