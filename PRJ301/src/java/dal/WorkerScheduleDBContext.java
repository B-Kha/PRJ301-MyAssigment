/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;
import com.sun.jdi.connect.spi.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import model.WorkerSchedule;

/**
 *
 * @author Laptop Acer
 */
public class WorkerScheduleDBContext extends DBContext<WorkerSchedule>{

    @Override
    public void insert(WorkerSchedule model) {
        try {
            String sql = "INSERT INTO Worker_Schedule (scid, eid, quantity) VALUES (?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getScid());
            stm.setInt(2, model.getEid());
            stm.setInt(3, model.getQuantity());
            stm.executeUpdate();
            stm.close();

            // Log success message
            System.out.println("Insert thành công: scid=" + model.getScid() + ", eid=" + model.getEid() + ", quantity=" + model.getQuantity());
        } catch (SQLException ex) {
            ex.printStackTrace(); // Print stack trace for detailed error
            Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(WorkerSchedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(WorkerSchedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<WorkerSchedule> list() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public WorkerSchedule get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
