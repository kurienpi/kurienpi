import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;


public class WriteToExcel {
    private static final Properties properties = new Properties();

    public static Connection getConnection() throws SQLException {
        try {
            // Load properties from application.properties


                try {
                    // Load the MySQL JDBC driver
                    Class.forName("com.mysql.cj.jdbc.Driver");
                } catch (ClassNotFoundException e) {
                    System.err.println("MySQL JDBC Driver not found. Include it in your library path!");
                    e.printStackTrace();
                }

            properties.load(new FileInputStream("G:\\myJAVA\\JDBCExam1016\\src\\main\\resources\\application.properties"));

            String url = properties.getProperty( "url");


            String username = properties.getProperty("username");
            String password = properties.getProperty("password");

            return DriverManager.getConnection(url, username, password);
        } catch (IOException e) {
            throw new SQLException("Could not load database properties", e);
        }
    }

    public static void importEmployeeData(String excelFilePath) {
        String insertQuery = "INSERT INTO employee (emp_id, emp_name, emp_address, emp_salary, " +
                "emp_mobile, dept_name, doj) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(insertQuery);
             FileInputStream fis = new FileInputStream(excelFilePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);

            // Skip header row if exists
            int startRow = 1;

            for (int rowIndex = startRow; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row != null) {
                    try {
                        // Read cell values and set parameters
                        pstmt.setInt(1, (int) row.getCell(0).getNumericCellValue());
                        pstmt.setString(2, row.getCell(1).getStringCellValue());
                        pstmt.setString(3, row.getCell(2).getStringCellValue());
                        pstmt.setBigDecimal(3, new java.math.BigDecimal(row.getCell(3).getNumericCellValue()));
                        pstmt.setInt(4, (int) row.getCell(4).getNumericCellValue());
                        pstmt.setString(5, row.getCell(5).getStringCellValue());
                        pstmt.setTimestamp(6, new Timestamp(row.getCell(6).getDateCellValue().getTime()));

                        pstmt.executeUpdate();
                    } catch (SQLException e) {
                        System.err.println("Error inserting row " + rowIndex + ": " + e.getMessage());
                        // Continue with next row instead of stopping the whole process
                        continue;
                    } catch (IllegalStateException | NumberFormatException e) {
                        System.err.println("Error reading data from Excel row " + rowIndex + ": " + e.getMessage());
                        continue;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading Excel file: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String excelFilePath = "G:\\myJAVA\\JDBCExam1016\\src\\main\\resources\\employee.xlsx";
        importEmployeeData(excelFilePath);
    }
}
