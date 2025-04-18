package lk.ijse.florist_pos.final_project;

import lk.ijse.florist_pos.final_project.dto.CustomerDto;

public class AppInitializer {
    public static void main(String[] args) {
        CustomerDto customerDto = new CustomerDto();
        String string = customerDto.toString();
        System.out.println(string);

    }
}
