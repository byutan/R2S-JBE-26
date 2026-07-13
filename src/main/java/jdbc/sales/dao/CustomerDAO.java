package jdbc.sales.dao;

import jdbc.sales.entities.Customer;

import java.sql.*;
import java.util.ArrayList;

public class CustomerDAO {
    private Connection conn;

    public CustomerDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(Customer customer) {
        if(conn == null) {
            return false;
        }

        boolean isInserted = false;
        String insertSt = "insert into customers(customer_name, contact_name, address, city, post_code, country) values (?, ?, ?, ?, ?, ?)";
        int index = 1;

        try (PreparedStatement ps = conn.prepareStatement(insertSt)) {
            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getContact());
            ps.setString(index++, customer.getAddress());
            ps.setString(index++, customer.getCity());
            ps.setString(index++, customer.getPostalCode());
            ps.setString(index++, customer.getCountry());

            if (ps.executeUpdate() > 0) {
                isInserted = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isInserted;
    };

    public boolean update(int id, Customer customer) {
        if(conn == null) {
            return false;
        }

        boolean isUpdated = false;
        String updateSt = "update customers set customer_name = ?, contact_name = ?, address = ?, city = ?, post_code = ?, country = ? where customer_id = ?";
        int index = 1;

        try(PreparedStatement ps = conn.prepareStatement(updateSt)) {
            ps.setString(index++, customer.getName());
            ps.setString(index++, customer.getContact());
            ps.setString(index++, customer.getAddress());
            ps.setString(index++, customer.getCity());
            ps.setString(index++, customer.getPostalCode());
            ps.setString(index++, customer.getCountry());
            ps.setInt(index++, id);

            if(ps.executeUpdate() > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isUpdated;
    };

    public boolean delete(int id)  {
        if(conn == null) {
            return false;
        }

        boolean isDeleted = false;
        String deleteSt = "delete from customers where customer_id = ?";
        int index = 1;

        try(PreparedStatement ps = conn.prepareStatement(deleteSt)) {
            ps.setInt(index++, id)
            ;
            if(ps.executeUpdate() > 0) {
                isDeleted = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isDeleted;
    };

    public ArrayList<Customer> selectAll() {
        if(conn == null) {
            return null;
        }

        String selectAllSt = "select * from customers ";
        ArrayList<Customer> customers = new ArrayList<>();
        try(Statement ps = conn.createStatement();
        ResultSet rs = ps.executeQuery(selectAllSt)) {
            while(rs.next()) {
                Customer customer = new Customer();

                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("customer_name"));
                customer.setContact(rs.getString("contact_name"));
                customer.setAddress(rs.getString("address"));
                customer.setCity(rs.getString("city"));
                customer.setPostalCode(rs.getString("post_code"));
                customer.setCountry(rs.getString("country"));

                customers.add(customer);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customers;
    }
}
