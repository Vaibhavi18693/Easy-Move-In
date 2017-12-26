/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.employee;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class EmployeeDirectory {

    private List<Employee> employeeDirectory;

    public EmployeeDirectory() {
        employeeDirectory = new ArrayList<>();
    }

    public List<Employee> getEmployeeDirectory() {
        return employeeDirectory;
    }

    public void setEmployeeDirectory(List<Employee> employeeDirectory) {
        this.employeeDirectory = employeeDirectory;
    }
    
    public Employee addEmployee() {
        Employee employee = new Employee();
        employeeDirectory.add(employee);
        return employee;
    }
    public void removeEmployee(Employee employee){
         employeeDirectory.remove(employee);
     }
}
