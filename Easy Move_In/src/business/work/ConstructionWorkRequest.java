/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.work;

import business.project.Project;

/**
 *
 * @author Hardik
 */
public class ConstructionWorkRequest extends WorkRequest {

    private Project project;

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @Override
    public String toString() {
        return this.project.toString();
    }

    
}
