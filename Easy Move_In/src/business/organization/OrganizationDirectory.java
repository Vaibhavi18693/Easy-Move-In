/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class OrganizationDirectory {

    public List<Organization> organizationDirectory;

    public OrganizationDirectory() {
        organizationDirectory = new ArrayList<>();
    }

    public List<Organization> getOrganizationDirectory() {
        return organizationDirectory;
    }

    public void setOrganizationDirectory(List<Organization> organizationDirectory) {
        this.organizationDirectory = organizationDirectory;
    }

    public Organization addOrganization(String name, Organization.OrganizationType organizationType) {
        Organization organization = null;
        if (organizationType == Organization.OrganizationType.GOVERNMENTADMINISTRATION) {
            organization = new GovernmentAdminOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if (organizationType == Organization.OrganizationType.STATEWELFARE) {
            organization = new GovernmentAdminOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if (organizationType == Organization.OrganizationType.FACILITIES) {
            organization = new FacilitiesOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if (organizationType == Organization.OrganizationType.CONSTRUCTIONADMIN) {
            organization = new ConstructionAdminOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if (organizationType == Organization.OrganizationType.ELECTRICIAN) {
            organization = new ElectricianOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if(organizationType == Organization.OrganizationType.PLUMBER) {
            organization = new PlumberOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if(organizationType == Organization.OrganizationType.SEWAGE) {
            organization = new SewageOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        } else if(organizationType == Organization.OrganizationType.TRANSPORT) {
            organization = new TransportOrganization(name);
            organizationDirectory.add(organization);
            return organization;
        }
        return null;
    }

    public GovernmentAdminOrganization getGovernmentAdminOrganization() {
        for (Organization organization : organizationDirectory) {
            if (organization instanceof GovernmentAdminOrganization) {
                return (GovernmentAdminOrganization)organization;
            }
        }
        return null;
    }
    public void removeOrganization(Organization organization){       
        organizationDirectory.remove(organization);
    }

}
