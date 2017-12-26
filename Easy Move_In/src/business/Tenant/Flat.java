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
public class Flat {
    private int noOfRooms;
    private String roomNo;
    private boolean isBooked;
    private ArrayList<String> flatImages;
    private Tenant tenant;
    
    public Flat(){
        flatImages = new ArrayList<>();
    }

    public int getNoOfRooms() {
        return noOfRooms;
    }

    public void setNoOfRooms(int noOfRooms) {
        this.noOfRooms = noOfRooms;
    }

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public boolean isIsBooked() {
        return isBooked;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    public ArrayList<String> getFlatImages() {
        return flatImages;
    }

    public void setFlatImages(ArrayList<String> flatImages) {
        this.flatImages = flatImages;
    }

    public Tenant getTenant() {
        return tenant;
    }

    public void setTenant(Tenant tenant) {
        this.tenant = tenant;
    }

    @Override
    public String toString() {
        return roomNo;
    }
    
    @Override
    public Flat clone() {
        Flat flat = new Flat();
        flat.setIsBooked(this.isBooked);
        flat.setNoOfRooms(this.noOfRooms);
        flat.setRoomNo(this.roomNo);
        return flat;
    }
    
}
