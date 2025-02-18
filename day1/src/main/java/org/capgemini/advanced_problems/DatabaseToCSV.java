package org.capgemini.advanced_problems;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.sql.*;

// Fetches employee data from the database and writes it to a CSV file
public class DatabaseToCSV {
    public static void main(String[] args) {
        // Database connection details
        String url = "jdbc:mysql://localhost:3306/postgresql";
        String user = "username";
        String password = "password";

        // Output CSV file path
        String csvFilePath = "src/main/resources/employee_report.csv";

        // SQL query to fetch employee records
        String query = "SELECT id, name, department, salary FROM employees";

        try (
                // Establish database connection
                Connection connection = DriverManager.getConnection(url, user, password);
                PreparedStatement statement = connection.prepareStatement(query);
                ResultSet resultSet = statement.executeQuery();

                // Create CSV writer
                CSVWriter csvWriter = new CSVWriter(new FileWriter(csvFilePath))
        ) {
            // Write header
            String[] header = {"Employee ID", "Name", "Department", "Salary"};
            csvWriter.writeNext(header);

            // Write employee records
            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                String salary = resultSet.getString("salary");

                String[] row = {id, name, department, salary};
                csvWriter.writeNext(row);
            }

            System.out.println("CSV report generated successfully");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

