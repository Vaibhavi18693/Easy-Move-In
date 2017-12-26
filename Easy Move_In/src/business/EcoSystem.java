/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.network.Network;
import business.organization.Organization;
import business.role.Role;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vaibhavi
 */
public class EcoSystem extends Organization {

    private static EcoSystem ecoSystem;
    private ArrayList<Network> networkList;
    private ArrayList<FeedBackForm> feedBackList;

    public EcoSystem(String name) {
        super(name);
        networkList = new ArrayList<>();
        feedBackList = new ArrayList<>();
    }

    public static EcoSystem getInstance() {
        if (ecoSystem == null) {
            ecoSystem = new EcoSystem("Relocation Application");
        }
        return ecoSystem;
    }

    @Override
    public List<Role> getSupportedRoles() {
        return null;
    }

    public ArrayList<Network> getNetworkList() {
        return networkList;
    }

    public Network addNetwork(String state) {
        Network network = new Network(state);
        networkList.add(network);
        return network;
    }

    public void deleteNetwork(Network network) {
        networkList.remove(network);
    }

    public ArrayList<FeedBackForm> getFeedBackList() {
        return feedBackList;
    }

    public void setFeedBackList(ArrayList<FeedBackForm> feedBackList) {
        this.feedBackList = feedBackList;
    }
    
    public FeedBackForm addFeedback(){
        FeedBackForm feedBack = new FeedBackForm();
        feedBackList.add(feedBack);
        return feedBack;
    }
    
}
