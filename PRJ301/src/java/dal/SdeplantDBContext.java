/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.PlanCampain;
import model.Sdeplant;

/**
 *
 * @author Laptop Acer
 */
public class SdeplantDBContext extends DBContext<Sdeplant> {

    @Override
    public void insert(Sdeplant model) {
        try {
            String sql_insert = "INSERT INTO [Sdeplant_campain] (comid, date, K, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement stm_insert = connection.prepareStatement(sql_insert);
            stm_insert.setInt(1, model.getPlanCampain().getId());  // Lấy comid từ PlanCampain
            stm_insert.setDate(2, new java.sql.Date(model.getDate().getTime()));  // Chuyển đổi java.util.Date sang java.sql.Date
            stm_insert.setString(3, model.getK());
            stm_insert.setInt(4, model.getQuantity());
            stm_insert.executeUpdate();
            stm_insert.close();
        } catch (SQLException ex) {
            Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void update(Sdeplant model) {
        throw new UnsupportedOperationException("Not supported yet.");

    }

    @Override
    public void delete(Sdeplant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Sdeplant> list() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Sdeplant get(int scid) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
