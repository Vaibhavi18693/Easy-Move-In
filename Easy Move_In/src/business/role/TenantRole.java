/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.role;

import business.EcoSystem;
import business.account.Account;
import business.account.TenantAccount;
import business.enterprise.Enterprise;
import business.event.Event;
import business.network.Network;
import business.organization.Organization;
import javax.swing.JPanel;
import userInterface.Tenant.TenantWorkAreaJPanel;

/**
 *
 * @author Hardik
 */
public class TenantRole extends Role{

    @Override
    public JPanel createWorkArea(JPanel userProcessContainer, Account account, Organization organization, Enterprise enterprise, Network network, EcoSystem ecoSystem) {
        throw new UnsupportedOperationException("Call the overloading method!!");
    }
    
    public JPanel createWorkAreaJPanel(JPanel userProcessContainer, Event event, Account tenantAccount, EcoSystem ecoSystem) {
        return new TenantWorkAreaJPanel(userProcessContainer, (TenantAccount)tenantAccount, event, ecoSystem);
    }
    
}
