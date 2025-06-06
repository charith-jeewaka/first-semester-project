package lk.ijse.florist_pos.final_project.controller;
import lk.ijse.florist_pos.final_project.DBConnect.DBConnection;
import lk.ijse.florist_pos.final_project.model.OrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;


public class ReportGenerator {

    public void generateTodaySalesReport() {
        try {
            // 1. Get today's total income from model
            BigDecimal todayIncome = OrderModel.getTodayTotalSales();
            String todayIncomeString = todayIncome.toString();
            System.out.println(todayIncomeString);

            // 2. Load compiled Jasper file (.jasper)
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResource("/Reports/tsales.jasper")
            );

            // 3. Prepare parameters
            HashMap<String, Object> params = new HashMap<>();
            params.put("TotalIncome", todayIncome); // Make sure your JRXML has a parameter with this exact name

            // 4. Fill the report
            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            // 5. Show report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    public void generateYesterdaySalesReport() {
        try {
            BigDecimal yesterdayIncome = OrderModel.getYesterdayTotalSales();
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResource("/Reports/ysales.jasper")
            );

            HashMap<String, Object> params = new HashMap<>();
            params.put("YTotalIncome", yesterdayIncome); // Make sure your JRXML has a parameter with this exact name

            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }

    public void generateBill(String balance) {
        try {

            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(
                    getClass().getResource("/Reports/Bill.jasper")
            );

            HashMap<String, Object> params = new HashMap<>();
            params.put("Balance", balance); // Make sure your JRXML has a parameter with this exact name

            Connection connection = DBConnection.getInstance().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, connection);

            JasperViewer.viewReport(jasperPrint, false);

        } catch (SQLException | JRException e) {
            e.printStackTrace();
        }
    }
}
