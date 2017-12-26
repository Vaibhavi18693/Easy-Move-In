/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.project.Project;
import business.role.ConstructionAdminRole;
import business.role.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class ConstructionAdminOrganization extends Organization{

    private List<Project> projects;

    public ConstructionAdminOrganization(String name) {
        super(name);
        projects = new ArrayList<>();
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }
        
    @Override
    public List<Role> getSupportedRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new ConstructionAdminRole());
        return roles;
    }
    
}
