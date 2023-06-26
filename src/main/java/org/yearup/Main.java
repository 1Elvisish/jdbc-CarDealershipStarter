package org.yearup;

import org.apache.commons.dbcp2.BasicDataSource;
import org.yearup.application.CarDealershipApplication;
import org.yearup.data.MySqlVehicleDao;
import org.yearup.data.VehicleDao;
//import org.yearup.application.Application;
public class Main
{
    public static void main(String[] args)
    {
        String baseUrl = "jdbc:mysql://localhost:3306/car_dealership";
        String username = "root";
        String password = "P@ssw0rd";

        BasicDataSource dataSource = new BasicDataSource()
        {{
            setUrl(baseUrl);
            setUsername(username);
            setPassword(password);
        }};

        VehicleDao vehicleDao = new MySqlVehicleDao(dataSource);

        CarDealershipApplication app = new CarDealershipApplication(vehicleDao);
        app.run();
    }
}