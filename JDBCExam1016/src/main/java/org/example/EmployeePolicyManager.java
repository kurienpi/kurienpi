package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.Properties;

public class EmployeePolicyManager {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    private static final String USER = "root";
    private static final String PASSWORD = "Bhopal";

    public static void main(String[] args) {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Database driver loaded successfully!");
            Connection conn = getConnection();

            // 1. Create emp_policy table and load data from second sheet
            createPolicyTable(conn);
            loadPolicyData(conn, "src/main/resources/employee.xlsx");

            // 2. Add policy_id foreign key to employee table
            addPolicyForeignKey(conn);

            // 3 & 4. Add emp_premium column
            addPremiumColumn(conn);

            // 5 & 6. Create and call stored procedure
            createStoredProcedures(conn);
            callUpdatePremiumProcedure(conn);

            // 7. Display all employee data
            displayEmployeeData(conn);

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Connection getConnection() throws SQLException {
        Properties props = new Properties();
        props.setProperty("user", USER);
        props.setProperty("password", PASSWORD);
        props.setProperty("useSSL", "false");
        return DriverManager.getConnection(URL, props);
    }

    private static void createPolicyTable(Connection conn) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS emp_policy (
                policy_id INT PRIMARY KEY,
                policy_name VARCHAR(100),
                sum_assured DECIMAL(12,2),
                rate_of_interest DECIMAL(5,2),
                policy_duration INT
            )
        """;
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void loadPolicyData(Connection conn, String excelPath) throws Exception {
        FileInputStream fis = new FileInputStream(excelPath);
        Workbook workbook = new XSSFWorkbook(fis);
        Sheet policySheet = workbook.getSheetAt(1); // Second sheet

        String sql = "INSERT INTO emp_policy VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            for (Row row : policySheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                pstmt.setInt(1, (int) row.getCell(0).getNumericCellValue());
                pstmt.setString(2, row.getCell(1).getStringCellValue());
                pstmt.setDouble(3, row.getCell(2).getNumericCellValue());
                pstmt.setDouble(4, row.getCell(3).getNumericCellValue());
                pstmt.setInt(5, (int) row.getCell(4).getNumericCellValue());

                pstmt.executeUpdate();
            }
        }
        workbook.close();
        fis.close();
    }

    private static void addPolicyForeignKey(Connection conn) throws SQLException {
        String[] sqls = {
            "ALTER TABLE employees ADD COLUMN IF NOT EXISTS policy_id INT",
            "ALTER TABLE employees ADD FOREIGN KEY (policy_id) REFERENCES emp_policy(policy_id)"
        };

        try (Statement stmt = conn.createStatement()) {
            for (String sql : sqls) {
                stmt.execute(sql);
            }
        }
    }

    private static void addPremiumColumn(Connection conn) throws SQLException {
        String sql = "ALTER TABLE employees ADD COLUMN IF NOT EXISTS emp_premium DECIMAL(12,2)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        }
    }

    private static void createStoredProcedures(Connection conn) throws SQLException {
        // Create Premium Calculation Function
        String createFunction = """
            CREATE OR REPLACE FUNCTION calculate_premium(
                p_emp_id INT,
                p_join_date DATE,
                p_sum_assured DECIMAL(12,2),
                p_rate_of_interest DECIMAL(5,2),
                p_policy_duration INT
            ) 
            RETURNS DECIMAL(12,2)
            DETERMINISTIC
            BEGIN
                DECLARE v_experience INT;
                DECLARE v_adjusted_sum DECIMAL(12,2);
                
                SET v_experience = YEAR(CURDATE()) - YEAR(p_join_date);
                SET v_adjusted_sum = p_sum_assured;
                
                IF v_experience <= 5 THEN
                    SET v_adjusted_sum = v_adjusted_sum - 50000;
                ELSEIF v_experience <= 10 THEN
                    SET v_adjusted_sum = v_adjusted_sum - 20000;
                ELSE
                    SET v_adjusted_sum = v_adjusted_sum - 50000;
                END IF;
                
                RETURN v_adjusted_sum / (p_rate_of_interest/100) * p_policy_duration;
            END
        """;

        // Create Stored Procedure
        String createProcedure = """
            CREATE OR REPLACE PROCEDURE update_employee_premium()
            BEGIN
                DECLARE done INT DEFAULT FALSE;
                DECLARE v_emp_id INT;
                DECLARE v_join_date DATE;
                DECLARE v_policy_id INT;
                DECLARE v_premium DECIMAL(12,2);
                
                DECLARE cur CURSOR FOR 
                    SELECT e.emp_id, e.date_of_joining, e.policy_id
                    FROM employees e
                    JOIN emp_policy p ON e.policy_id = p.policy_id;
                
                DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
                
                OPEN cur;
                
                read_loop: LOOP
                    FETCH cur INTO v_emp_id, v_join_date, v_policy_id;
                    IF done THEN
                        LEAVE read_loop;
                    END IF;
                    
                    SELECT calculate_premium(
                        v_emp_id,
                        v_join_date,
                        p.sum_assured,
                        p.rate_of_interest,
                        p.policy_duration
                    ) INTO v_premium
                    FROM emp_policy p
                    WHERE p.policy_id = v_policy_id;
                    
                    UPDATE employees 
                    SET emp_premium = v_premium 
                    WHERE emp_id = v_emp_id;
                END LOOP;
                
                CLOSE cur;
            END
        """;

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(createFunction);
            stmt.execute(createProcedure);
        }
    }

    private static void callUpdatePremiumProcedure(Connection conn) throws SQLException {
        try (CallableStatement cstmt = conn.prepareCall("{CALL update_employee_premium()}")) {
            cstmt.execute();
        }
    }

    private static void displayEmployeeData(Connection conn) throws SQLException {
        String sql = """
            SELECT e.*, p.policy_name, p.sum_assured, p.rate_of_interest, p.policy_duration
            FROM employees e
            JOIN emp_policy p ON e.policy_id = p.policy_id
            ORDER BY e.emp_id
        """;

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("\nEmployee Details with Policy Information:");
            System.out.println("=================================================================");

            while (rs.next()) {
                System.out.printf("""
                    
                    Employee ID: %d
                    Name: %s
                    Address: %s
                    Salary: %.2f
                    Mobile: %s
                    Department: %s
                    Join Date: %s
                    Policy Details:
                        Policy ID: %d
                        Policy Name: %s
                        Sum Assured: %.2f
                        Rate of Interest: %.2f%%
                        Duration: %d years
                    Premium Amount: %.2f
                    =================================================================
                    """,
                    rs.getInt("emp_id"),
                    rs.getString("emp_name"),
                    rs.getString("emp_address"),
                    rs.getDouble("emp_salary"),
                    rs.getString("emp_mobile"),
                    rs.getString("department"),
                    rs.getDate("date_of_joining"),
                    rs.getInt("policy_id"),
                    rs.getString("policy_name"),
                    rs.getDouble("sum_assured"),
                    rs.getDouble("rate_of_interest"),
                    rs.getInt("policy_duration"),
                    rs.getDouble("emp_premium")
                );
            }
        }
    }
}