package lk.ijse.florist_pos.final_project.dto;

public class EmployeeDto {
    private int employeeId;
    private String employeeName;
    private String employeeSalary;
    private String employeeBOD;
    private String employeePosition;
    private String employeeAddress;
    public EmployeeDto() {
    }

    public EmployeeDto(int employeeId,String employeeName, String employeeSalary, String employeeBOD,
                       String employeePosition, String employeeAddress) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.employeeSalary = employeeSalary;
        this.employeeBOD = employeeBOD;
        this.employeePosition = employeePosition;
        this.employeeAddress = employeeAddress;

    }
    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeSalary() {
        return employeeSalary;
    }
    public void setEmployeeSalary(String employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public String getEmployeeBOD() {
        return employeeBOD;
    }
    public void setEmployeeBOD(String employeeBOD) {
        this.employeeBOD = employeeBOD;
    }

    public String getEmployeePosition() {
        return employeePosition;
    }
    public void setEmployeePosition(String employeePosition) {
        this.employeePosition = employeePosition;
    }

    public String getEmployeeAddress() {
        return employeeAddress;
    }
    public void setEmployeeAddress(String employeeAddress) {
        this.employeeAddress = employeeAddress;
    }

    @Override
    public String toString() {
        return EmployeeDto.class.getSimpleName() + " [employeeId=" + employeeId + ", employeeName=" + employeeName
                + ", employeeSalary=" + employeeSalary
                + ", employeeBOD=" + employeeBOD + ", employeePosition=" + employeePosition
                + ", employeeAddress=" + employeeAddress + "]";
    }



}
