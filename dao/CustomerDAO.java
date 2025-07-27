package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.Customer;
import util.DBUtil;

public class CustomerDAO {

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO CUSTOMER (name, phone_number, email) VALUES (?, ?, ?)";
        
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getPhoneNumber());
            stmt.setString(3, customer.getEmail());

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Customer inserted successfully!");
            } else {
                System.out.println("Failed to insert customer.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
