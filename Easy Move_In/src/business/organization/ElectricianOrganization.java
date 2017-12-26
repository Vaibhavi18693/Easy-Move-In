/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.role.ElectricianRole;
import business.role.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vaibhavi
 */
public class ElectricianOrganization extends Organization {
    
    public ElectricianOrganization(String name)
    {
        super(name);
    }

    @Override
    public List<Role> getSupportedRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new ElectricianRole());
        return roles;
    }

}
