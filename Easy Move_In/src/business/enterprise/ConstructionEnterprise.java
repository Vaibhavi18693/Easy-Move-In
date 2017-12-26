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
public class ConstructionEnterprise extends Enterprise{

    public ConstructionEnterprise(String name) {
        super(name,EnterpriseType.CONSTRUCTION);
    }

    @Override
    public List<Role> getSupportedRoles() {
        return null;
    }
    
}
