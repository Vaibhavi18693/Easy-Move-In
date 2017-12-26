/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.role;

import business.EcoSystem;
import business.account.Account;
import business.enterprise.Enterprise;
import business.network.Network;
import business.organization.Organization;
import javax.swing.JPanel;

/**
 *
 * @author Hardik
 */
public abstract class Role {

    public enum RoleType {
        GOVERNMENTADMIN("Government Admin"),
        ACCOUNTANT("Accountant"),
        CONSTRUCTIONADMIN("Construction Admin"),
        FACILITIESMANAGER("Facilities Manager"),
        ELECTRICIAN("Electrician"),
        PLUMBER("Plumber"),
        SEWAGE("Sewage"),
        TRANSPORT("Transport"),
        WELFAREMANAGER("Welfare Manager"),
        TENANT("Tenant");

        private String value;

        private RoleType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public abstract JPanel createWorkArea(JPanel userProcessContainer,
            Account account,
            Organization organization,
            Enterprise enterprise,
            Network network,
            EcoSystem ecoSystem
            );

}
