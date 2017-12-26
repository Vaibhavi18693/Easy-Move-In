/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.role.FacilitiesRole;
import business.role.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vaibhavi
 */
public class FacilitiesOrganization extends Organization {

    public FacilitiesOrganization(String name)
    {
        super(name);
    }
    
    @Override
    public List<Role> getSupportedRoles() {
        List<Role> roles = new ArrayList<Role>();
        roles.add(new FacilitiesRole());
        return roles;
    }
    
    
}
