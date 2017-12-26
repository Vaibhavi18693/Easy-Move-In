/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.Tenant.Address;
import business.Tenant.Area;
import business.Tenant.Street;
import business.Tenant.Tenant;
import business.account.Account;
import business.account.TenantAccount;
import business.employee.Employee;
import business.enterprise.Enterprise;
import business.enterprise.GovernmentEnterprise;
import business.network.Network;
import business.organization.Organization;
import business.organization.OrganizationDirectory;
import business.role.ConstructionAdminRole;
import business.role.ElectricianRole;
import business.role.FacilitiesRole;
import business.role.GovernmentAdminRole;
import business.role.PlumberRole;
import business.role.SewageRole;
import business.role.SystemAdminRole;
import business.role.TransportRole;
import business.work.FacilitiesWorkRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Vaibhavi
 */
public class ConfigureABusiness {

    private static final String EXCEL_FILE_LOCATION = "S:\\NEU\\Assignments\\AED\\AED_LAB_DEMO\\aed_final_project\\AEDFinalProject\\Data\\NetworkData.xlsx";

    public static EcoSystem configure() {
        EcoSystem ecoSystem = new EcoSystem("Relocation Application");
        readExcelData(ecoSystem);
        
        Employee employee = ecoSystem.getEmployeeDirectory().addEmployee();
        employee.setFirstName("Arjun");
        employee.setLastName("Jaiswal");
        employee.setAge("36");
        
        Account sysAccount = ecoSystem.getAccountDirectory().createAccount("sysadmin", "sysadmin", employee, new SystemAdminRole());

//        Network network = ecoSystem.addNetwork("MA");
//        Area area = new Area();
//        Building b1 = area.addBuilding(900);
//        Building b2 = area.addBuilding(900);
//        Building b3 = area.addBuilding(900);
//        Building b4 = area.addBuilding(900);
//        Building b5 = area.addBuilding(900);
//        Building b6 = area.addBuilding(900);
//        Building b7 = area.addBuilding(900);
//        Building b8 = area.addBuilding(900);
//        network.getAreaList().add(area);
        

        Employee employee2 = ecoSystem.getEmployeeDirectory().addEmployee();
        employee2.setFirstName("Hardik");
        employee2.setLastName("Jain");
        employee2.setAge("23");

        Employee employee3 = ecoSystem.getEmployeeDirectory().addEmployee();
        employee3.setFirstName("Shilman");
        employee3.setLastName("Hall");
        employee3.setAge("36");
        
        Employee employee4 = ecoSystem.getEmployeeDirectory().addEmployee();
        employee4.setFirstName("John");
        employee4.setLastName("Bleak");
        employee4.setAge("36");
        
        Employee employee5 = ecoSystem.getEmployeeDirectory().addEmployee();
        employee5.setFirstName("Keshav");
        employee5.setLastName("Shah");
        employee5.setAge("36");
        
        Address a1 = new Address();
        a1.setApartmentId(413);
        a1.setBuildingId(1);
        a1.setStreet("75 Peterborough street");
        a1.setZipCode("02215");
        a1.setCity("MA");
        
        Address a2 = new Address();
        a2.setApartmentId(24);
        a2.setBuildingId(2);
        a2.setStreet("Germain street");
        a2.setZipCode("02215");
        a2.setCity("MA");
        
        Address a3 = new Address();
        a3.setApartmentId(56);
        a3.setBuildingId(3);
        a3.setStreet("Burney street");
        a3.setZipCode("02215");
        a3.setCity("MA");
        
        Address a4 = new Address();
        a4.setApartmentId(89);
        a4.setBuildingId(4);
        a4.setStreet("Newbury street");
        a4.setZipCode("02215");
        a4.setCity("MA");
        
        Tenant tenant1 = new Tenant();
        tenant1.setTenantName("James");
        tenant1.setPermanentAddress(a1);
        tenant1.setTempAddress(a2);
        
        Tenant tenant2 = new Tenant();
        tenant2.setTenantName("Pinak");
        tenant2.setPermanentAddress(a3);
        tenant2.setTempAddress(a4);
        
        TenantAccount accTenant1 = new TenantAccount();
        accTenant1.setTenant(tenant1);
        accTenant1.setUsername("t1");
        accTenant1.setPassword("t1");
        
        TenantAccount accTenant2 = new TenantAccount();
        accTenant2.setTenant(tenant2);
        accTenant2.setUsername("t2");
        accTenant2.setPassword("t2");
        
        for (Network network : ecoSystem.getNetworkList()) {
            Enterprise enterprise = network.getEnterpriseDirectory().addEnterprise("DB Realty", Enterprise.EnterpriseType.CONSTRUCTION);
            Organization newOrganization = enterprise.getOrganizationDirectory().addOrganization("DB ConstructionAdmin", Organization.OrganizationType.CONSTRUCTIONADMIN);

            Account ac1 = newOrganization.getAccountDirectory().addAccount();
            ac1.setUsername("constr");
            ac1.setPassword("constr");
            ac1.setRole(new ConstructionAdminRole());
//        Account userAccount = ecoSystem.getAccountDirectory().createAccount("sysadmin", "sysadmin", null, new SystemAdminRole());
//        Account enterpriseAdmin = enterprise.getAccountDirectory().createAccount("enterpriseadmin", "enterpriseadmin", null, new EnterpriseAdminRole());

            Enterprise govEnterprise = network.getEnterpriseDirectory().getEnterprise(GovernmentEnterprise.class);
            OrganizationDirectory organizationDirectory = govEnterprise.getOrganizationDirectory();
            Organization org = organizationDirectory.getGovernmentAdminOrganization();
            org.getAccountDirectory().createAccount("govadmin", "govadmin", null, new GovernmentAdminRole());
//        Account governmentAdmin = govEnterprise.getAccountDirectory().createAccount("govadmin", "govadmin", employee3, new GovernmentAdminRole());

          Enterprise amenEnterprise = network.getEnterpriseDirectory().addEnterprise("Amenitites", Enterprise.EnterpriseType.FACILITIES);
          Organization facOragnization = amenEnterprise.getOrganizationDirectory().addOrganization("Facilities Organization", Organization.OrganizationType.FACILITIES);
          Organization elecOrganization = amenEnterprise.getOrganizationDirectory().addOrganization("Electrician Organization", Organization.OrganizationType.ELECTRICIAN);
          Organization sewageOrganization = amenEnterprise.getOrganizationDirectory().addOrganization("Sewage Organization", Organization.OrganizationType.SEWAGE);
          Organization plumOrganization = amenEnterprise.getOrganizationDirectory().addOrganization("Plumber Organization", Organization.OrganizationType.PLUMBER);
          Organization transOrganization = amenEnterprise.getOrganizationDirectory().addOrganization("Transport Organization", Organization.OrganizationType.TRANSPORT);
                  
          Account acc = facOragnization.getAccountDirectory().addAccount();
          acc.setUsername("fac");
          acc.setPassword("fac");
          acc.setRole(new FacilitiesRole());
          acc.setEmployee(employee);
          
          Account accElec = elecOrganization.getAccountDirectory().addAccount();
          accElec.setUsername("elec");
          accElec.setPassword("elec");
          accElec.setRole(new ElectricianRole());
          accElec.setEmployee(employee2);
          
          Account accPlum = plumOrganization.getAccountDirectory().addAccount();
          accPlum.setUsername("plum");
          accPlum.setPassword("plum");
          accPlum.setRole(new PlumberRole());
          accPlum.setEmployee(employee3);
          
          Account accSewage = sewageOrganization.getAccountDirectory().addAccount();
          accSewage.setUsername("sew");
          accSewage.setPassword("sew");
          accSewage.setRole(new SewageRole());
          accSewage.setEmployee(employee4);
          
          Account accTransport = transOrganization.getAccountDirectory().addAccount();
          accTransport.setUsername("tran");
          accTransport.setPassword("tran");
          accTransport.setRole(new TransportRole());
          accTransport.setEmployee(employee5);
          
          FacilitiesWorkRequest facRequest = new FacilitiesWorkRequest();
          facRequest.setSender(accTenant1);
          facRequest.setStatus("Sent");
          facRequest.setTenantAccount(accTenant1);
          facOragnization.getWorkQueue().getWorkQueue().add(facRequest);
          
          FacilitiesWorkRequest facRequest2 = new FacilitiesWorkRequest();
          facRequest2.setSender(accTenant2);
          facRequest2.setStatus("Sent");
          facRequest2.setTenantAccount(accTenant2);
          facOragnization.getWorkQueue().getWorkQueue().add(facRequest2);
                  
//        OrganizationDirectory organizationDirectory = new OrganizationDirectory();
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.TRANSPORT);
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.GOVERNMENTADMINISTRATION);
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.SEWAGE);
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.PLUMBING);
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.FACILITIES);
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.STATEWELFARE);
//        organizationDirectory.addOrganization("MA", Organization.OrganizationType.ELECTRICITY);
//        Account account = ecoSystem.getAccountDirectory().addAccount();
//        account.setUsername("Abc");
//        account.setPassword("Abc");
        }
        return ecoSystem;
    }

    private static void readExcelData(EcoSystem ecoSystem) {
        Network network = ecoSystem.addNetwork("MA");

        FileInputStream excelFile;
        try {
            excelFile = new FileInputStream(new File(EXCEL_FILE_LOCATION));
            DataFormatter formatter = new DataFormatter();
            Workbook workbook = new XSSFWorkbook(excelFile);

            Sheet dataTypeSheet = workbook.getSheet("Area");
            readExcelDataForArea(dataTypeSheet, formatter, network);

            dataTypeSheet = workbook.getSheet("Street");
            readExcelDataForStreet(dataTypeSheet, formatter, network);

            dataTypeSheet = workbook.getSheet("Building");
            readExcelDataForBuilding(dataTypeSheet, formatter, network);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ConfigureABusiness.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ConfigureABusiness.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private static void readExcelDataForArea(Sheet dataTypeSheet, DataFormatter formatter, Network network) {
        Iterator<Row> rowIterator = dataTypeSheet.iterator();
        rowIterator.hasNext();
        int row = 0;
        Area area;
        String cityName;
        Map<String, List<Area>> freeSpaceAvailable = new HashMap<String, List<Area>>();
        List<Area> areaList;

        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            row = currentRow.getRowNum();
            if (row == 0) { // Skipping Header
                continue;
            }
            area = new Area();
            cityName = formatter.formatCellValue(currentRow.getCell(0)).toLowerCase();
            area.setZipcode(formatter.formatCellValue(currentRow.getCell(1)));

            if (freeSpaceAvailable.containsKey(cityName)) {
                freeSpaceAvailable.get(cityName).add(area);
            } else {
                areaList = new ArrayList<Area>();
                areaList.add(area);
                freeSpaceAvailable.put(cityName, areaList);
            }
        }
        network.setFreeSpaceAvailable(freeSpaceAvailable);

    }

    private static void readExcelDataForBuilding(Sheet dataTypeSheet, DataFormatter formatter, Network network) {
        Iterator<Row> rowIterator = dataTypeSheet.iterator();
        rowIterator.hasNext();
        int row = 0;
        int noOfRooms = 0;

        Area area;
        String streetName;
        String zipCode;
        int buildingNo, floors, floorsPerFlat;
        boolean canBeDemolished, isParkingAvailable;
        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            row = currentRow.getRowNum();
            if (row == 0) { // Skipping Header
                continue;
            }
            zipCode = formatter.formatCellValue(currentRow.getCell(0));
            streetName = formatter.formatCellValue(currentRow.getCell(1));
            area = network.getAreaByZipCode(zipCode);
            
            Street street = area.findStreet(streetName);
            buildingNo = Integer.parseInt(formatter.formatCellValue(currentRow.getCell(2)));
            floors = Integer.parseInt(formatter.formatCellValue(currentRow.getCell(3)));
            floorsPerFlat = Integer.parseInt(formatter.formatCellValue(currentRow.getCell(4)));

            canBeDemolished = formatter.formatCellValue(currentRow.getCell(5)).equalsIgnoreCase("Y") ? true : false;
            isParkingAvailable = formatter.formatCellValue(currentRow.getCell(7)).equalsIgnoreCase("Y") ? true : false;

            noOfRooms = Integer.parseInt(formatter.formatCellValue(currentRow.getCell(6)));
            street.addBuilding(buildingNo, floors, floorsPerFlat, canBeDemolished, noOfRooms, isParkingAvailable);

        }
    }

    private static void readExcelDataForStreet(Sheet dataTypeSheet, DataFormatter formatter, Network network) {
        Iterator<Row> rowIterator = dataTypeSheet.iterator();
        rowIterator.hasNext();
        int row = 0;

        String zipCode;
        String streetName;
        Map<String, List<Area>> freeSpaceAvailable = network.getFreeSpaceAvailable();
        List<Area> areaList;

        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            row = currentRow.getRowNum();
            if (row == 0) { // Skipping Header
                continue;
            }
            zipCode = formatter.formatCellValue(currentRow.getCell(0));
            streetName = formatter.formatCellValue(currentRow.getCell(1)).toLowerCase();

            for (Map.Entry<String, List<Area>> entry : freeSpaceAvailable.entrySet()) {
                areaList = entry.getValue();
                for (Area area : areaList) {
                    if (area.getZipcode().equalsIgnoreCase(zipCode)) {
                        area.addStreet(streetName);
                    }
                }
            }
        }
    }
}
