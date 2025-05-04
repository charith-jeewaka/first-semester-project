package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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



    public boolean saveCustomer(CustomerDto customerDTO) throws SQLException {

        return CrudUtil.execute(
                "insert into customer values (?,?,?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getMobileNumber(),
                customerDTO.getEmail(),
                customerDTO.getCustomerAddress(),
                customerDTO.getRegisteredTime()
        );
    }

    public ArrayList<CustomerDto> getAllCustomer() throws SQLException {
//        Connection connection = DBConnection.getInstance().getConnection();
//        PreparedStatement pst = connection.prepareStatement("select * from customer");
        ResultSet resultSet = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDto> customerDTOArrayList = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDto customerDTO = new CustomerDto(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            customerDTOArrayList.add(customerDTO);
        }

        return customerDTOArrayList;
    }
}
