package com.faith.dao;


import com.faith.entity.Employee;
import com.faith.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO {

    // Create
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO employees (name, department, salary) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, employee.getName());
            stmt.setString(2, employee.getDepartment());
            stmt.setDouble(3, employee.getSalary());
            stmt.executeUpdate();
            System.out.println("Employee added successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read
    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        String sql = "SELECT * FROM employees";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Employee emp = new Employee();
                emp.setId(rs.getInt("id"));
                emp.setName(rs.getString("name"));
                emp.setDepartment(rs.getString("department"));
                emp.setSalary(rs.getDouble("salary"));
                employees.add(emp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Update
    public void updateEmployeeSalary(int id, double newSalary) {
        String sql = "UPDATE employees SET salary = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDouble(1, newSalary);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Employee salary updated." : "Employee not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete
    public void deleteEmployee(int id) {
        String sql = "DELETE FROM employees WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println(rows > 0 ? "Employee deleted." : "Employee not found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

