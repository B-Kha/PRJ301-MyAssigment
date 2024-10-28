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
public class SdeplantDBContext extends DBContext<Sdeplant>{

    @Override
    public void insert(Sdeplant model) {
           if (model.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }

        try {
            connection.setAutoCommit(false);

            String sql_insert_Sdeplant = "INSERT INTO [Sdeplant_campain] "
                    + "([comid], [date], [K], [Quantity]) "
                    + "VALUES (?, ?, ?, ?)";

            String sql_select_Sdepplant = "SELECT @@IDENTITY as scid";

            PreparedStatement stm_insert_Sdeplant = connection.prepareStatement(sql_insert_Sdeplant);
            stm_insert_Sdeplant.setInt(1, model.getPlanCampain().getId());
            stm_insert_Sdeplant.setDate(2, new java.sql.Date(model.getDate().getTime()));
            stm_insert_Sdeplant.setString(3, model.getK());
            stm_insert_Sdeplant.setInt(4, model.getQuantity());
            stm_insert_Sdeplant.executeUpdate();

            PreparedStatement stm_select_Sdepplant = connection.prepareStatement(sql_select_Sdepplant);
            ResultSet rs = stm_select_Sdepplant.executeQuery();
            if(rs.next()) {
                model.setId(rs.getInt("scid"));
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
 

    @Override
    public void update(Sdeplant model) {
         try {
            connection.setAutoCommit(false);

            String sql_update_Sdeplant = "UPDATE [Sdeplant_campain] "
                    + "SET [comid] = ?, [date] = ?, [K] = ?, [Quantity] = ? "
                    + "WHERE [scid] = ?";

            PreparedStatement stm_update_Sdeplant = connection.prepareStatement(sql_update_Sdeplant);
            stm_update_Sdeplant.setInt(1, model.getPlanCampain().getId());
            stm_update_Sdeplant.setDate(2, new java.sql.Date(model.getDate().getTime()));
            stm_update_Sdeplant.setString(3, model.getK());
            stm_update_Sdeplant.setInt(4, model.getQuantity());
            stm_update_Sdeplant.setInt(5, model.getId());
            stm_update_Sdeplant.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(Sdeplant model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Sdeplant> list() {
        {
        ArrayList<Sdeplant> sdeplants = new ArrayList<>();
        try {
            String sql = "SELECT scid, comid, date, K, Quantity FROM [Sdeplant_campain]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Sdeplant sdeplant = new Sdeplant();
                sdeplant.setId(rs.getInt("scid"));
                
                PlanCampain planCampain = new PlanCampain();
                planCampain.setId(rs.getInt("comid"));
                sdeplant.setPlanCampain(planCampain);

                sdeplant.setDate(rs.getDate("date"));
                sdeplant.setK(rs.getString("K"));
                sdeplant.setQuantity(rs.getInt("Quantity"));

                sdeplants.add(sdeplant);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(SdeplantDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sdeplants; 
    }
    }
    @Override
    public Sdeplant get(int scid) {
    Sdeplant sdeplant = null;
    try {
        String sql = "SELECT scid, comid, date, K, Quantity FROM [Sdeplant_campain] WHERE scid = ?";
        PreparedStatement stm = connection.prepareStatement(sql);
        stm.setInt(1, scid);
        ResultSet rs = stm.executeQuery();
        if (rs.next()) {
            sdeplant = new Sdeplant();
            sdeplant.setId(rs.getInt("scid"));

            PlanCampain planCampain = new PlanCampain();
            planCampain.setId(rs.getInt("comid"));
            sdeplant.setPlanCampain(planCampain);

            sdeplant.setDate(rs.getDate("date"));
            sdeplant.setK(rs.getString("K"));
            sdeplant.setQuantity(rs.getInt("Quantity"));
        }
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

  

  

