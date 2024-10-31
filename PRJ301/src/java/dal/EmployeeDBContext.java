/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employee;
import java.sql.Date;

/**
 *
 * @author Laptop Acer
 */
public class EmployeeDBContext extends DBContext<Employee> {

    @Override
    public void insert(Employee employee) {
        try {
            // Câu lệnh SQL để chèn dữ liệu vào bảng Employee
            String sql = "INSERT INTO Employee (ename, edob, salary, job_Title, did, Address) VALUES (?, ?, ?, ?, ?, ?)";

            // Khởi tạo PreparedStatement
            PreparedStatement stm = connection.prepareStatement(sql);

            // Thiết lập các giá trị cho từng tham số trong câu lệnh SQL
            stm.setString(1, employee.getEname()); // Thiết lập tên nhân viên
            Date sqlDate = new Date(employee.getEdob().getTime());
            stm.setDate(2, sqlDate); // Ngày sinh của nhân viên
            stm.setBigDecimal(3, employee.getSalary()); // Mức lương của nhân viên
            stm.setString(4, employee.getJobTitle()); // Chức vụ của nhân viên
            stm.setInt(5, employee.getDid()); // Mã phòng ban của nhân viên
            stm.setString(6, employee.getAddress()); // Địa chỉ của nhân viên

            // Thực thi câu lệnh SQL
            stm.executeUpdate();

            // Đóng PreparedStatement
            stm.close();
            System.out.println("Employee inserted successfully!");
        } catch (SQLException ex) {
            // Ghi log chi tiết lỗi SQL
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            // Đảm bảo đóng kết nối trong khối finally
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(Employee employee) {
        try {
            String sql = "UPDATE Employee SET ename = ?, edob = ?, salary = ?, job_Title = ?, did = ?, Address = ? WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, employee.getEname());
            stm.setDate(2, new java.sql.Date(employee.getEdob().getTime()));
            stm.setBigDecimal(3, employee.getSalary());
            stm.setString(4, employee.getJobTitle());
            stm.setInt(5, employee.getDid());
            stm.setString(6, employee.getAddress());
            stm.setInt(7, employee.getEid());
            stm.executeUpdate();
            stm.close();
            System.out.println("Employee updated successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Employee employee) {
         try {
            String sql = "DELETE FROM Employee WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, employee.getEid());
            stm.executeUpdate();
            stm.close();
            System.out.println("Employee deleted successfully!");
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public ArrayList<Employee> list() {
        ArrayList<Employee> employees = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Employee";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Employee employee = new Employee();
                employee.setEid(rs.getInt("eid"));
                employee.setEname(rs.getString("ename"));
                employee.setEdob(rs.getDate("edob"));
                employee.setSalary(rs.getBigDecimal("salary"));
                employee.setJobTitle(rs.getString("job_Title"));
                employee.setDid(rs.getInt("did"));
                employee.setAddress(rs.getString("Address"));
                employees.add(employee);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employees;
    }

    @Override
    public Employee get(int id) {
        Employee employee = null;
        try {
            String sql = "SELECT * FROM Employee WHERE eid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                employee = new Employee();
                employee.setEid(rs.getInt("eid"));
                employee.setEname(rs.getString("ename"));
                employee.setEdob(rs.getDate("edob"));
                employee.setSalary(rs.getBigDecimal("salary"));
                employee.setJobTitle(rs.getString("job_Title"));
                employee.setDid(rs.getInt("did"));
                employee.setAddress(rs.getString("Address"));
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return employee;
    }
}
