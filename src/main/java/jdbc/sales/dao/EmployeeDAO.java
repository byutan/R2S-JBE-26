package jdbc.sales.dao;

import jdbc.sales.entities.Employee;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import static java.sql.Date.valueOf;

public class EmployeeDAO {
    private Connection conn;

    public EmployeeDAO(Connection conn) {
        this.conn = conn;
    }

    public boolean insert(Employee employee) {
        if(conn == null) {
            return false;
        }

        boolean isInsert = false;
        String callInsertProcedure = "call sp_add_employee(?, ?, ?, ?)";
        int index = 1;

        try(CallableStatement cs = conn.prepareCall(callInsertProcedure);) {
            cs.setString(index++, employee.getLastName());
            cs.setString(index++, employee.getFirstName());

            String[] parts = employee.getBirthdate().split("-");

            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int date = Integer.parseInt(parts[2]);
            LocalDate localDate = LocalDate.of(year, month, date);

            cs.setDate(index++, valueOf(localDate));
            cs.setInt(index++, employee.getSupervisor());

            if(cs.executeUpdate() > 0) {
                isInsert = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isInsert;
    }

    public boolean update(int id, Employee employee) {
        if(conn == null) {
            return false;
        }

        boolean isUpdated = false;
        String callInsertProcedure = "call sp_update_employee(?, ?, ?, ?, ?)";
        int index = 1;

        try(CallableStatement cs = conn.prepareCall(callInsertProcedure);) {
            cs.setInt(index++, id);
            cs.setString(index++, employee.getLastName());
            cs.setString(index++, employee.getFirstName());

            String[] parts = employee.getBirthdate().split("-");

            int year = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int date = Integer.parseInt(parts[2]);
            LocalDate localDate = LocalDate.of(year, month, date);

            cs.setDate(index++, valueOf(localDate));
            cs.setInt(index++, employee.getSupervisor());

            if(cs.executeUpdate() > 0) {
                isUpdated = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isUpdated;
    }

    public boolean delete(int id) {
        if(conn == null) {
            return false;
        }

        boolean isDelete = false;
        String callDeleteProcedure = "call sp_delete_employee(?)";
        int index = 1;

        try(CallableStatement cs = conn.prepareCall(callDeleteProcedure)) {
            cs.setInt(index++, id);
            if(cs.executeUpdate() > 0) {
                isDelete = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isDelete;
    }

    public ArrayList<Employee> selectAll() {
        if(conn == null) {
            return null;
        }
        ArrayList<Employee> employees = new ArrayList<>();
        String callSelectAllProcedure = "call sp_select_all_employees";
        try(CallableStatement cs = conn.prepareCall(callSelectAllProcedure);
            ResultSet rs = cs.executeQuery();) {
            while (rs.next()) {
                Employee employee = new Employee();

                employee.setId(rs.getInt("employee_id"));
                employee.setLastName(rs.getString("last_name"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setBirthdate(rs.getString("birth_date"));
                employee.setSupervisor(rs.getInt("supervisor_id"));

                employees.add(employee);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return employees;
    }
    private static int getIntFromString(Employee employee, int beginIndex, int endIndex) throws NumberFormatException {
        return Integer.parseInt(employee.getBirthdate().substring(beginIndex, endIndex));
    }
}
