/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.account.AccountDirectory;
import business.employee.EmployeeDirectory;
import business.role.Role;
import business.work.WorkQueue;
import java.util.List;

/**
 *
 * @author Hardik
 */
public abstract class Organization {

    private String name;
    private AccountDirectory accountDirectory;
    private EmployeeDirectory employeeDirectory;
    private WorkQueue workQueue;
    private int id;
    private static int count = 1;

    public enum OrganizationType {
        CONSTRUCTIONADMIN("Construction Admin"), GOVERNMENTADMINISTRATION("Government Administration"), FACILITIES("Facilities"), STATEWELFARE("State Welfare"), ELECTRICIAN("Electrician"), PLUMBER("Plumber"), SEWAGE("Sewage"), TRANSPORT("Transport"), TENANT("Tenant");
        private String value;

        private OrganizationType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }

    public Organization(String name) {
        this.name = name;
        accountDirectory = new AccountDirectory();
        employeeDirectory = new EmployeeDirectory();
        workQueue = new WorkQueue();
        id = count++;
    }

    public abstract List<Role> getSupportedRoles();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountDirectory getAccountDirectory() {
        return accountDirectory;
    }

    public void setAccountDirectory(AccountDirectory accountDirectory) {
        this.accountDirectory = accountDirectory;
    }

    public EmployeeDirectory getEmployeeDirectory() {
        return employeeDirectory;
    }

    public void setEmployeeDirectory(EmployeeDirectory employeeDirectory) {
        this.employeeDirectory = employeeDirectory;
    }

    public WorkQueue getWorkQueue() {
        return workQueue;
    }

    public void setWorkQueue(WorkQueue workQueue) {
        this.workQueue = workQueue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
