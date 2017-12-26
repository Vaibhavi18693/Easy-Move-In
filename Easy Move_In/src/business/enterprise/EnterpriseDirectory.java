/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.enterprise;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class EnterpriseDirectory {

    private List<Enterprise> enterpriseDirectory;

    public EnterpriseDirectory() {
        enterpriseDirectory = new ArrayList<>();
    }

    public List<Enterprise> getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public void setEnterpriseDirectory(List<Enterprise> enterpriseDirectory) {
        this.enterpriseDirectory = enterpriseDirectory;
    }

    public Enterprise getEnterprise(Class clazz) {
        for (Enterprise enterprise : enterpriseDirectory) {
            if (enterprise.getClass().equals(clazz)) {
                return enterprise;
            }
        }
        return null;
    }
    public GovernmentEnterprise getGovernmentEnterprise() {
        for(Enterprise enterprise : enterpriseDirectory)
            if(enterprise instanceof GovernmentEnterprise)
                return (GovernmentEnterprise) enterprise;
        return null;
    }

    public Enterprise addEnterprise(String name, Enterprise.EnterpriseType enterpriseType) {
        Enterprise enterprise = null;
        if (enterpriseType == Enterprise.EnterpriseType.GOVERNMENT) {
            enterprise = new GovernmentEnterprise(name);
            enterpriseDirectory.add(enterprise);
            return enterprise;
        }
        if (enterpriseType == Enterprise.EnterpriseType.CONSTRUCTION) {
            enterprise = new ConstructionEnterprise(name);
            enterpriseDirectory.add(enterprise);
            return enterprise;
        }
        
        if(enterpriseType == Enterprise.EnterpriseType.FACILITIES) {
            enterprise = new AmenitiesEnterprise(name);
            enterpriseDirectory.add(enterprise);
            return enterprise;
        }

        return enterprise;
    }

    public void removeEnterprise(Enterprise enterpriseType) {
        enterpriseDirectory.remove(enterpriseType);
    }

}
