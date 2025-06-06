package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.EmployeeDto;
import lk.ijse.florist_pos.final_project.dto.FlowerDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StaffModel {
    public String getNextFlowerId() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select employee_id from employee order by employee_id desc limit 1");
        char tableCharacter = 'E'; // Use any character Ex:- customer table for C, item table for I
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

    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException {
        ResultSet resultSet = CrudUtil.execute("select * from employee");
        ArrayList<EmployeeDto> employeeDtoArrayList = new ArrayList<>();
        while (resultSet.next()){
            EmployeeDto employeeDto = new EmployeeDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            );
            employeeDtoArrayList.add(employeeDto);
        }
        return employeeDtoArrayList;
    }

    public static int getTotalEmployees() throws SQLException {
        String sql = "SELECT COUNT(employee_id) AS employee_count FROM employee";
        ResultSet rs = CrudUtil.execute(sql);
        int employeeCount = 0;
        if (rs.next()) {
            employeeCount = rs.getInt("employee_count");
        }
        System.out.println("Total employees: " + employeeCount);
        return employeeCount;
    }

}
