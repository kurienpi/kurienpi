package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;

public class ExcelToDatabase {
    // Database credentials
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Bhopal";

    public static void main(String[] args) {
        String excelFilePath = "C:\\Java-WS\\JDBCExam1016\\src\\main\\resources\\employee.xlsx";

        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Database driver loaded successfully!");
            // Database connection
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);

            // Create table if not exists
            createTable(connection);

            // Read Excel file
            FileInputStream inputStream = new FileInputStream(excelFilePath);
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet firstSheet = workbook.getSheetAt(0);

            // Prepare SQL statement
            String sql = """
                INSERT INTO employees (
                    emp_id, emp_name, emp_address, emp_salary, 
                    emp_mobile, department, date_of_joining, policy_id
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?)
            """;
            PreparedStatement statement = connection.prepareStatement(sql);

            // Iterate through rows
            int rowCount = 0;
            for (Row row : firstSheet) {
                // Skip header row
                if (rowCount == 0) {
                    rowCount++;
                    continue;
                }

                try {
                    // Read cell values
                    int empId = (int) row.getCell(0).getNumericCellValue();
                    String empName = getCellValueAsString(row.getCell(1));
                    String empAddress = getCellValueAsString(row.getCell(2));
                    double empSalary = row.getCell(3).getNumericCellValue();
                    String empMobile = getCellValueAsString(row.getCell(4));
                    String department = getCellValueAsString(row.getCell(5));
                    Date dateOfJoining = new Date(row.getCell(6).getDateCellValue().getTime());
                    int policyId = (int) row.getCell(7).getNumericCellValue();

                    // Set parameters
                    statement.setInt(1, empId);
                    statement.setString(2, empName);
                    statement.setString(3, empAddress);
                    statement.setDouble(4, empSalary);
                    statement.setString(5, empMobile);
                    statement.setString(6, department);
                    statement.setDate(7, dateOfJoining);
                    statement.setInt(8, policyId);

                    // Execute insert
                    statement.executeUpdate();
                    System.out.println("Inserted employee with ID: " + empId);

                } catch (Exception e) {
                    System.err.println("Error processing row " + rowCount + ": " + e.getMessage());
                    continue; // Skip to next row if there's an error
                }

                rowCount++;
            }

            // Close resources
            workbook.close();
            inputStream.close();
            statement.close();
            connection.close();

            System.out.println("Data import completed successfully!");
            System.out.println("Total rows processed: " + (rowCount - 1));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTable(Connection connection) throws Exception {
        String createTableSQL = """
            CREATE TABLE IF NOT EXISTS employees (
                emp_id INT PRIMARY KEY,
                emp_name VARCHAR(100),
                emp_address VARCHAR(200),
                emp_salary DECIMAL(10,2),
                emp_mobile VARCHAR(15),
                department VARCHAR(50),
                date_of_joining DATE,
                policy_id INT,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf((long) cell.getNumericCellValue());
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            default:
                return "";
        }
    }
}