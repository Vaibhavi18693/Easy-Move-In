/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.enterprise;

import business.role.Role;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class GovernmentEnterprise extends Enterprise {

    public GovernmentEnterprise(String name) {
        super(name, EnterpriseType.GOVERNMENT);
    }

    @Override
    public List<Role> getSupportedRoles() {
        return null;
    }
    
}
