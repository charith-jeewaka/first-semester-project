package lk.ijse.florist_pos.final_project.controller;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import lk.ijse.florist_pos.final_project.model.FlowerModel;
import lk.ijse.florist_pos.final_project.model.OrderModel;
import lk.ijse.florist_pos.final_project.model.PlantModel;
import lk.ijse.florist_pos.final_project.model.StaffModel;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {
    public AnchorPane ancHome;
    public ImageView imageView;
    public Label lblYesterdaySale;
    public Label lblTodaySale;
    public Label lblTotalFlowers;
    public Label lblTotalPlants;
    public Label lblEmployees;
    public BarChart<String, Number> barChart;
    public CategoryAxis barDates;
    public NumberAxis barSales;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imageView.fitWidthProperty().bind(ancHome.widthProperty());
        imageView.fitHeightProperty().bind(ancHome.heightProperty());
        try {
            double yesterdaySale = OrderModel.getYesterdayTotalSale();
            lblYesterdaySale.setText(String.valueOf(yesterdaySale));
            double todaySale = OrderModel.getTodayTotalSale();
            lblTodaySale.setText(String.valueOf(todaySale));
            int totalPlants = PlantModel.getTotalPlantQty();
            lblTotalPlants.setText(String.valueOf(totalPlants));
            int totalEmployee = StaffModel.getTotalEmployees();
            lblEmployees.setText(String.valueOf(totalEmployee));
            int totalFlowers = FlowerModel.getTotalFlowerQty();
            lblTotalFlowers.setText(String.valueOf(totalFlowers));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
