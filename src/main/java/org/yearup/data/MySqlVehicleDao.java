package org.yearup.data;

import org.yearup.models.Vehicle;


import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class  MySqlVehicleDao implements VehicleDao {
    private DataSource dataSource;

    public MySqlVehicleDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    public List<Vehicle> listByCategory(int categoryId) {
        List<Vehicle> vehicles = new ArrayList<>();
        String sql = """
                SELECT vehicleVin as Vin
                    , vehicleMake as Make
                    , vehicleModel as Model
                    , vehicleColor as Color
                    , vehicleYear as Year
                    , vehicleMiles as Miles
                    , unitPrice as Price
                    , vehicleSold as Sold
                FROM vehicle
                WHERE vehicleVin = ?;
                """;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet row = statement.executeQuery();

            while (row.next()) {
                String vin = row.getString("vin");
                String make = row.getString("make");
                String model = row.getString("model");
                String color = row.getString("color");
                int year = row.getInt("year");
                int miles = row.getInt("miles");
                BigDecimal price = row.getBigDecimal("price");
                boolean isSold = row.getBoolean("Sold");
                Vehicle vehicle = new Vehicle() {{
                    setColor(color);
                    setMake(make);
                    setMiles(miles);
                    setPrice(price);
                    setYear(year);
                    setSold(isSold);
                    setVin(vin);
                    setModel(model);
                }};
                vehicles.add(vehicle);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicles()
    {
        List<Vehicle> vehicles = new ArrayList<>();

        String sql = """
                SELECT * 
                FROM vehicles;
                """;

        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();)
        {
            ResultSet row = statement.executeQuery(sql);

            while(row.next())
            {
                Vehicle vehicle = new Vehicle()
                {{
                    setVin(row.getString("vin"));
                    setMake(row.getString("make"));
                    setModel(row.getString("model"));
                    setColor(row.getString("color"));
                    setYear(row.getInt("year"));
                    setMiles(row.getInt("miles"));
                    setPrice(row.getBigDecimal("price"));
                    setSold(row.getBoolean("sold"));
                }};

                vehicles.add(vehicle);
            }
        }
        catch(SQLException ignored){}

        return vehicles;
    }


    @Override
    public Vehicle create(Vehicle vehicle){

        String sql = """
                SELECT vehicleVin as Vin
                    , vehicleMake as Make
                    , vehicleModel as Model
                    , vehicleColor as Color
                    , vehicleYear as Year
                    , vehicleMiles as Miles
                    , unitPrice as Price
                    , vehicleSold as Sold
                FROM vehicle
                WHERE vehicleVin = ?;
                """;
        try(Connection connection = dataSource.getConnection();
        PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){
            statement.setString(1, vehicle.getVin());
            statement.setString(2, vehicle.getMake());
            statement.setString(3, vehicle.getModel());
            statement.setString(4, vehicle.getColor());
            statement.setInt(5, vehicle.getMiles());
            statement.setInt(6, vehicle.getYear());
            statement.setBigDecimal(7, vehicle.getPrice());
            statement.setBoolean(8, vehicle.isSold());

          statement.executeUpdate();

        }
        catch (SQLException ex)
        {

        }

        return null;
    }

    @Override
    public void update(String vehicleVin, Vehicle vehicle)
    {

        String sql = """
                UPDATE products
                SET ProductName = ?
                    , UnitPrice = ?
                    , CategoryID = ?
                WHERE productId = ?;
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setString(1, vehicle.getVin());
            statement.setBigDecimal(2, vehicle.getPrice());
            statement.setString(3, vehicle.getMake());

            statement.executeUpdate();

        }
        catch (SQLException ex)
        {

        }
    }

    @Override
    public void delete(String vehicleVin)
    {

        String sql = """
                DELETE FROM vehicles
                WHERE vehicleVin = ?;
                """;
        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        )
        {
            statement.setString(1, vehicleVin);

            statement.executeUpdate();

        }
        catch (SQLException ex)
        {

        }
    }
}

