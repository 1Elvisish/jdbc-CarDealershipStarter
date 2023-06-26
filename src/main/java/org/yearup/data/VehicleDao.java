package org.yearup.data;

import org.yearup.models.Vehicle;

import java.sql.SQLException;
import java.util.List;

public interface VehicleDao
{
    // R - Read
    List<Vehicle> getAllVehicles();

    // C - Create
    Vehicle create(Vehicle vehicle);

    // U - Update
    void update(String vehicleVin,Vehicle dealership);

    // D - Delete
    void delete(String vehicleVin);

    List<Vehicle> listByCategory(int categoryId) throws SQLException;
}
