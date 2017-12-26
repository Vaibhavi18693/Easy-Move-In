/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business.Tenant;

import java.util.ArrayList;

/**
 *
 * @author Vaibhavi
 */
public class Area {

    private String zipcode;
    private ArrayList<Street> streetList;

    public Area() {
        streetList = new ArrayList<Street>();
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public ArrayList<Street> getStreetList() {
        return streetList;
    }

    public void setStreetList(ArrayList<Street> streetList) {
        this.streetList = streetList;
    }

    public void addStreet(String streetName) {
        Street street = new Street();
        street.setStreetName(streetName);
        streetList.add(street);
    }

    public Street findStreet(String streetName) {
        for (Street street : streetList) {
            if (street.getStreetName().equalsIgnoreCase(streetName)) {
                return street;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return String.valueOf(this.zipcode);
    }

    @Override
    public Area clone() {
        Area area = new Area();
        area.setZipcode(this.zipcode);
        ArrayList<Street> sList = new ArrayList<>();
        for (Street street : this.streetList) {
            sList.add(street.clone());
        }
        area.setStreetList(sList);
        return area;
    }

}
