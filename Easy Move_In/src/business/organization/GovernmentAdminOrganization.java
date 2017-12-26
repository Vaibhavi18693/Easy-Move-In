/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.organization;

import business.event.Event;
import business.role.GovernmentAdminRole;
import business.role.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Hardik
 */
public class GovernmentAdminOrganization extends Organization {

    private List<Event> eventList;

    public GovernmentAdminOrganization(String name) {
        super(name);
        eventList = new ArrayList<>();
    }

    public List<Event> getEventList() {
        return eventList;
    }

    public void setEventList(List<Event> eventList) {
        this.eventList = eventList;
    }

    @Override
    public List<Role> getSupportedRoles() {
        List<Role> roles = new ArrayList<>();
        roles.add(new GovernmentAdminRole());
        return roles;
    }

}
