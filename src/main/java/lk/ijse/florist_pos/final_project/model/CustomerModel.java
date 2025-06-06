package lk.ijse.florist_pos.final_project.model;

import lk.ijse.florist_pos.final_project.dto.CustomerDto;
import lk.ijse.florist_pos.final_project.util.CrudUtil;

import java.math.BigDecimal;
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
        return tableCharacter + "001";
    }

    public ArrayList<CustomerDto> getAllCustomer() throws SQLException {

        ResultSet resultSet = CrudUtil.execute("select * from customer");

        ArrayList<CustomerDto> customerDTOArrayList = new ArrayList<>();
        while (resultSet.next()) {
            CustomerDto customerDTO = new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
            customerDTOArrayList.add(customerDTO);
        }
        System.out.println("hello");

        return customerDTOArrayList;

    }

    public boolean saveCustomer(CustomerDto customerDTO) throws SQLException {
        return CrudUtil.execute(
                "insert into customer (customer_id, name, phone_number, email, address) values (?,?,?,?,?)",
                customerDTO.getCustomerId(),
                customerDTO.getCustomerName(),
                customerDTO.getMobileNumber(),
                customerDTO.getEmail(),
                customerDTO.getCustomerAddress()
        );
    }

    public void deleteCustomer(String customerId) throws SQLException {
        CrudUtil.execute("delete from customer where customer_id=?", customerId);
    }

    public void updateCustomer(CustomerDto customerDTO) throws SQLException {
        CrudUtil.execute(
                "UPDATE customer SET name = ?, phone_number = ?, email = ?, address = ? WHERE customer_id = ?",
                customerDTO.getCustomerName(),
                customerDTO.getMobileNumber(),
                customerDTO.getEmail(),
                customerDTO.getCustomerAddress(),
                customerDTO.getCustomerId()
        );
    }

    public CustomerDto searchCustomer(String number) throws SQLException {
        ResultSet resultSet = CrudUtil.execute("SELECT * FROM customer WHERE phone_number = ?;", number);

        if (resultSet.next()) {
            return new CustomerDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        } else {
            return null;
        }
    }






}
