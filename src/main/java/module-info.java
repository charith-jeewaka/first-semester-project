module lk.ijse.florist_pos.final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;
    requires com.jfoenix;



    opens lk.ijse.florist_pos.final_project.controller to javafx.fxml;
    opens lk.ijse.florist_pos.final_project.dto.Tm to javafx.base;
    exports lk.ijse.florist_pos.final_project;
}