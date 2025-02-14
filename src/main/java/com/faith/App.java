package com.faith;

import com.faith.dao.EmployeeDAO;
import com.faith.entity.Employee;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        EmployeeDAO dao = new EmployeeDAO();

        // Create
        dao.addEmployee(new Employee("John Doe", "IT", 70000));
        dao.addEmployee(new Employee("Jane Smith", "HR", 60000));

        // Read
        List<Employee> employees = dao.getAllEmployees();
        employees.forEach(System.out::println);

        // Update
        dao.updateEmployeeSalary(1, 75000);
        System.out.println("After salary update:");
        dao.getAllEmployees().forEach(System.out::println);

        // Delete
        dao.deleteEmployee(2);
        System.out.println("After deletion:");
        dao.getAllEmployees().forEach(System.out::println);
    }
}
