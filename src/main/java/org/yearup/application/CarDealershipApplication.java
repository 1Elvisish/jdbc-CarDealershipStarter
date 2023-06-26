package org.yearup.application;

import org.yearup.data.MySqlVehicleDao;
import org.yearup.data.VehicleDao;
import org.yearup.models.Dealership;
import org.yearup.models.Vehicle;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class CarDealershipApplication {

    private Scanner userInput = new Scanner(System.in);
    private VehicleDao vehicleDao;

    public CarDealershipApplication(VehicleDao vehicleDao) {
        this.vehicleDao = vehicleDao;
    }

    public List<Vehicle> getAllVehicles() {
        return vehicleDao.getAllVehicles();
    }
    public void displayAllVehicles(){
        List<Vehicle> vehicles = getAllVehicles();
        for (var v: vehicles){
            System.out.println(v.getVin());
            System.out.println(v.getMake());
            System.out.println(v.getModel());
            System.out.println(v.getMiles());
            System.out.println(v.getPrice());
            System.out.println(v.getYear());
            System.out.println(v.isSold());
            System.out.println();
        }

    }

    public void run(){
        while(true){

            System.out.println();
            System.out.println("1) Display all vehicles");
            System.out.println("Exit");
            System.out.print("Please make a selection");

            String choice = userInput.nextLine();

            switch (choice){

                case "1":
                    displayAllVehicles();
                    break;

            }
        }

    }
}


