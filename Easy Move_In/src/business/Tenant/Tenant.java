/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Tenant;

import business.role.Role;

/**
 *
 * @author Vaibhavi
 */
public class Tenant {
    
    private String tenantName;
    private boolean parkingNeeded;
    private Address permanentAddress;
    private Address tempAddress;

    public Tenant()
    {
        permanentAddress = new Address();
        tempAddress = new Address();
    }
    
    public String getTenantName() {
        return tenantName;
    }

    public void setTenantName(String tenantName) {
        this.tenantName = tenantName;
    }
    
    public boolean isParkingNeeded() {
        return parkingNeeded;
    }

    public void setParkingNeeded(boolean parkingNeeded) {
        this.parkingNeeded = parkingNeeded;
    }

    public Address getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(Address permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public Address getTempAddress() {
        return tempAddress;
    }

    public void setTempAddress(Address tempAddress) {
        this.tempAddress = tempAddress;
    }
    
}
