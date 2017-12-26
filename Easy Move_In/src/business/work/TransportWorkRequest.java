/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.work;

import business.account.TenantAccount;

/**
 *
 * @author Vaibhavi
 */
public class TransportWorkRequest extends WorkRequest {
    
    private TenantAccount tenantAccount;

    public TenantAccount getTenantAccount() {
        return tenantAccount;
    }

    public void setTenantAccount(TenantAccount tenantAccount) {
        this.tenantAccount = tenantAccount;
    }
    
}
