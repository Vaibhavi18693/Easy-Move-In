/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Tenant;

import java.util.ArrayList;

/**
 *
 * @author Shruti
 */
public class Street {

    private String streetName;
    private ArrayList<Building> buildingList;

    public Street() {
        buildingList = new ArrayList<>();
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public ArrayList<Building> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(ArrayList<Building> buildingList) {
        this.buildingList = buildingList;
    }

    public void addBuilding(int buildingNo, int floors, int flatsPerFloor, boolean canBeDemolished, int noOfRooms, boolean parkingAvailable) {
        Building build = new Building();
        build.setBuildingId(buildingNo);
        build.setFloors(floors);
        build.setFlatPerFloor(flatsPerFloor);
        build.setCanBeDemolished(canBeDemolished);
        build.setParkingAvailable(parkingAvailable);
        calculateFlatNo(build, floors, flatsPerFloor, noOfRooms);
        buildingList.add(build);

    }

    public void deleteBuilding(int buildingId) {
        buildingList.remove(buildingId);
    }

    @Override
    public String toString() {
        return streetName;
    }

    private void calculateFlatNo(Building build, int floors, int flatsPerFloor, int noOfRooms) {
        String roomNo = "";
        for (int i = 1; i <= floors; i++) {
            for (int j = 1; j <= flatsPerFloor; j++) {
                if (j == 10) {
                    roomNo = (String.valueOf(i)).concat(String.valueOf(j));
                } else {
                    roomNo = (String.valueOf(i)).concat("0").concat(String.valueOf(j));
                }
                build.addFlatToBuilding(noOfRooms, roomNo);
            }

        }
    }

    @Override
    public Street clone() {
        Street street = new Street();
        street.setStreetName(this.streetName);
        ArrayList<Building> bList = new ArrayList<>();
        for (Building building : this.buildingList) {
            bList.add(building.clone());
        }
        street.setBuildingList(bList);
        return street;
    }

}
