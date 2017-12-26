/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.event;

import business.Tenant.Flat;
import business.Tenant.Tenant;
import business.account.Account;
import business.account.AccountDirectory;
import business.account.TenantAccount;
import business.project.Project;
import business.role.TenantRole;

/**
 *
 * @author Hardik
 */
public class Event {

    private static int count = 1;
    private int id;
    private Project project;
    private AccountDirectory accountDirectory;

    private void createTenantAccounts() {
        for (Flat flat : project.getBuilding().getFlats()) {
            TenantAccount newAccount = accountDirectory.addTenantAccount();
            newAccount.setUsername(project.getBuilding().getBuildingId() + flat.getRoomNo());
            newAccount.setPassword(project.getBuilding().getBuildingId() + flat.getRoomNo());
            newAccount.setRole(new TenantRole());
            newAccount.setTenant(new Tenant());
        }
    }
    
    public int getApprovalCount() {
        int count = 0;
        for(Account account : accountDirectory.getAccountDirectory()) {
            TenantAccount tenantAccount = (TenantAccount)account;
            if(tenantAccount.getDecision() == Decision.YES)
                count++;
        }
        return count;
    }
    
    public int getDenialCount() {
        int count = 0;
        for(Account account : accountDirectory.getAccountDirectory()) {
            TenantAccount tenantAccount = (TenantAccount)account;
            if(tenantAccount.getDecision() == Decision.NO)
                count++;
        }
        return count;
    }

    public Event(Project project) {
        this.project = project;
        id = count++;
        accountDirectory = new AccountDirectory();
        createTenantAccounts();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public AccountDirectory getAccountDirectory() {
        return accountDirectory;
    }

    public void setAccountDirectory(AccountDirectory accountDirectory) {
        this.accountDirectory = accountDirectory;
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

    public enum Decision {
        YES, NO;
    }
}
