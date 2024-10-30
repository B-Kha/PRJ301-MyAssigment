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
            String sql = "INSERT INTO Sdeplant_campain (comid, date, K, Quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getPlanCampain().getId()); // Thiết lập comid từ PlanCampain
            stm.setDate(2, new java.sql.Date(model.getDate().getTime())); // Chuyển đổi java.util.Date sang java.sql.Date
            stm.setString(3, model.getK());
            stm.setInt(4, model.getQuantity());
            stm.executeUpdate();
            stm.close();
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
        try {
            String sql = "UPDATE Sdeplant_campain SET comid = ?, date = ?, K = ?, Quantity = ? WHERE scid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getPlanCampain().getId());
            stm.setDate(2, new java.sql.Date(model.getDate().getTime()));
            stm.setString(3, model.getK());
            stm.setInt(4, model.getQuantity());
            stm.setInt(5, model.getId());
            stm.executeUpdate();
            stm.close();
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
    public void delete(Sdeplant model) {
        try {
            String sql = "DELETE FROM Sdeplant_campain WHERE scid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, model.getId());
            stm.executeUpdate();
            stm.close();
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
    public ArrayList<Sdeplant> list() {
        ArrayList<Sdeplant> sdeplants = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Sdeplant_campain";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sdeplant sdeplant = new Sdeplant();
                sdeplant.setId(rs.getInt("scid"));
                sdeplant.setDate(rs.getDate("date"));
                sdeplant.setK(rs.getString("K"));
                sdeplant.setQuantity(rs.getInt("Quantity"));

                PlanCampain planCampain = new PlanCampain();
                planCampain.setId(rs.getInt("comid"));
                sdeplant.setPlanCampain(planCampain);

                sdeplants.add(sdeplant);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sdeplants;
    }

    @Override
    public Sdeplant get(int scid) {
        Sdeplant sdeplant = null;
        try {
            String sql = "SELECT * FROM Sdeplant_campain WHERE scid = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, scid);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                sdeplant = new Sdeplant();
                sdeplant.setId(rs.getInt("scid"));
                sdeplant.setDate(rs.getDate("date"));
                sdeplant.setK(rs.getString("K"));
                sdeplant.setQuantity(rs.getInt("Quantity"));

                PlanCampain planCampain = new PlanCampain();
                planCampain.setId(rs.getInt("comid"));
                sdeplant.setPlanCampain(planCampain);
            }
            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sdeplant;
    }

}
