/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Tenant;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Vaibhavi
 */
public class Building {

    private ArrayList<Tenant> totalTenants;
    private int buildingId;
    private int floors;
    private int flatPerFloor;
    private boolean canBeDemolished;
    private boolean workInProgress;
    private boolean parkingAvailable;
    private ArrayList<Flat> flats;
    //private static int count = 1;

    public Building() {

        totalTenants = new ArrayList<Tenant>();
        flats = new ArrayList<Flat>();
        //buildingId = count++;
    }

    public ArrayList<Tenant> getTotalTenants() {
        return totalTenants;
    }

    public int getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(int buildingId) {
        this.buildingId = buildingId;
    }

    public boolean isWorkInProgress() {
        return workInProgress;
    }

    public void setWorkInProgress(boolean workInProgress) {
        this.workInProgress = workInProgress;
    }

    public Tenant addTenant() {
        Tenant tenant = new Tenant();
        totalTenants.add(tenant);
        return tenant;
    }

    public void removeTenant(Tenant tenant) {
        totalTenants.remove(tenant);
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public int getFlatPerFloor() {
        return flatPerFloor;
    }

    public void setFlatPerFloor(int flatPerFloor) {
        this.flatPerFloor = flatPerFloor;
    }

    public boolean isCanBeDemolished() {
        return canBeDemolished;
    }

    public void setCanBeDemolished(boolean canBeDemolished) {
        this.canBeDemolished = canBeDemolished;
    }

    public boolean isParkingAvailable() {
        return parkingAvailable;
    }

    public void setParkingAvailable(boolean parkingAvailable) {
        this.parkingAvailable = parkingAvailable;
    }

    public ArrayList<Flat> getFlats() {
        return flats;
    }

    public void setFlats(ArrayList<Flat> flats) {
        this.flats = flats;
    }

    public Tenant searchTenant(String tenantName) {
        for (Tenant tenant : totalTenants) {
            if (tenant.getTenantName().equalsIgnoreCase(tenantName)) {
                return tenant;
            }
        }
        return null;
    }

    public void addFlatToBuilding(int noOfRooms, String roomNo) {
        Flat flat = new Flat();
        flat.setNoOfRooms(noOfRooms);
        flat.setRoomNo(roomNo);
        flats.add(flat);

    }

    @Override
    public String toString() {
        return String.valueOf(this.buildingId);
    }

    @Override
    public Building clone() {
        Building building = new Building();
        building.setBuildingId(this.buildingId);
        building.setCanBeDemolished(this.canBeDemolished);
        building.setFlatPerFloor(this.flatPerFloor);
        building.setFloors(this.floors);
        building.setParkingAvailable(this.parkingAvailable);
        ArrayList<Flat> fList = new ArrayList<>();
        for (Flat flat : this.flats) {
            fList.add(flat.clone());
        }
        building.setFlats(fList);
        return building;
    }

}
