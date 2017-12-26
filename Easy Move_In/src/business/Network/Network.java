/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.network;

import business.Tenant.Area;
import business.enterprise.Enterprise;
import business.enterprise.EnterpriseDirectory;
import business.enterprise.GovernmentEnterprise;
import business.organization.Organization;
import business.organization.OrganizationDirectory;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hardik
 */
public class Network {

    private String state;
    private EnterpriseDirectory enterpriseDirectory;
    private List<Area> areaList;
    Multimap<String, Area> areaMap = ArrayListMultimap.create();
    private Map<String, List<Area>> freeSpaceAvailable = new HashMap<String, List<Area>>();

    public Network(String state) {
        this.state = state;
        enterpriseDirectory = new EnterpriseDirectory();
        areaList = new ArrayList<>();
        GovernmentEnterprise govEnterprise = (GovernmentEnterprise) enterpriseDirectory.addEnterprise("Government", Enterprise.EnterpriseType.GOVERNMENT);
        OrganizationDirectory organizationDirectory = govEnterprise.getOrganizationDirectory();
        organizationDirectory.addOrganization(state + " Admin", Organization.OrganizationType.GOVERNMENTADMINISTRATION);
        organizationDirectory.addOrganization(state + " Welfare", Organization.OrganizationType.STATEWELFARE);
        organizationDirectory.addOrganization(state + " Facilities", Organization.OrganizationType.FACILITIES);
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public EnterpriseDirectory getEnterpriseDirectory() {
        return enterpriseDirectory;
    }

    public void setEnterpriseDirectory(EnterpriseDirectory enterpriseDirectory) {
        this.enterpriseDirectory = enterpriseDirectory;
    }

    public List<Area> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<Area> areaList) {
        this.areaList = areaList;
    }

    public Area getAreaByZipCode(String zipCode) {
        List<Area> newareaList;
        for (Map.Entry<String, List<Area>> entry : freeSpaceAvailable.entrySet()) {
            newareaList = entry.getValue();
            for (Area area : newareaList) {
                if (area.getZipcode().equalsIgnoreCase(zipCode)) {
                    return area;
                }
            }
        }
         return null;
    }

    

    public Multimap<String, Area> getAreaMap() {
        return areaMap;
    }

    public void setAreaMap(Multimap<String, Area> areaMap) {
        this.areaMap = areaMap;
    }

    public Map<String, List<Area>> getFreeSpaceAvailable() {
        return freeSpaceAvailable;
    }

    public void setFreeSpaceAvailable(Map<String, List<Area>> freeSpaceAvailable) {
        this.freeSpaceAvailable = freeSpaceAvailable;
    }

    @Override
    public String toString() {
        return this.state;
    }

}
