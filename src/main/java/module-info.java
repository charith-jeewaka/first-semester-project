module lk.ijse.florist_pos.final_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires lombok;



    opens lk.ijse.florist_pos.final_project.controller to javafx.fxml;
    exports lk.ijse.florist_pos.final_project;
}