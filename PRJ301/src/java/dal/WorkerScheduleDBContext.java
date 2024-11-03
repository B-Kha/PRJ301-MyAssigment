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
import model.Employee;
import model.PlanCampain;
import model.Sdeplant;
import model.WorkerSchedule;

/**
 *
 * @author Laptop Acer
 */
public class WorkerScheduleDBContext extends DBContext<WorkerSchedule> {

    @Override
    public void insert(WorkerSchedule model) {
        try {
            connection.setAutoCommit(false);

            String sql_insert_workerSchedule = "INSERT INTO [Worker_Schedul] "
                    + "([scid], [eid], [quantity]) "
                    + "VALUES (?, ?, ?)";

            PreparedStatement stm_insert_workerSchedule = connection.prepareStatement(sql_insert_workerSchedule);
            stm_insert_workerSchedule.setInt(1, model.getSdeplant().getId());
            stm_insert_workerSchedule.setInt(2, model.getEmpolyee().getEid());
            stm_insert_workerSchedule.setInt(3, model.getQuantity());
            stm_insert_workerSchedule.executeUpdate();

            String sql_select_workerSchedule = "SELECT @@IDENTITY as wsid";
            PreparedStatement stm_select_workerSchedule = connection.prepareStatement(sql_select_workerSchedule);
            ResultSet rs = stm_select_workerSchedule.executeQuery();
            if (rs.next()) {
                model.setWsid(rs.getInt("wsid"));
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @Override
    public void update(WorkerSchedule model) {
        try {
            connection.setAutoCommit(false);

            String sql_update_workerSchedule = "UPDATE [Worker_Schedul] "
                    + "SET [scid] = ?, [eid] = ?, [quantity] = ? "
                    + "WHERE [wsid] = ?";

            PreparedStatement stm_update_workerSchedule = connection.prepareStatement(sql_update_workerSchedule);
            stm_update_workerSchedule.setInt(1, model.getSdeplant().getId());
            stm_update_workerSchedule.setInt(2, model.getEmpolyee().getEid());
            stm_update_workerSchedule.setInt(3, model.getQuantity());
            stm_update_workerSchedule.setInt(4, model.getWsid());

            stm_update_workerSchedule.executeUpdate();

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void delete(WorkerSchedule model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<WorkerSchedule> list() {
        ArrayList<WorkerSchedule> workerSchedules = new ArrayList<>();
        try {
            String sql_list_workerSchedule = "SELECT ws.wsid, ws.scid, ws.eid, ws.quantity "
                    + "FROM Worker_Schedul ws";
            PreparedStatement stm = connection.prepareStatement(sql_list_workerSchedule);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                WorkerSchedule ws = new WorkerSchedule();
                ws.setWsid(rs.getInt("wsid"));

                Sdeplant sc = new Sdeplant();
                sc.setId(rs.getInt("scid"));
                ws.setSdeplant(sc);

                Employee e = new Employee();
                e.setEid(rs.getInt("eid"));
                ws.setEmpolyee(e);

                ws.setQuantity(rs.getInt("quantity"));

                workerSchedules.add(ws);
            }
        } catch (SQLException ex) {
            Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return workerSchedules;
    }

    @Override
    public WorkerSchedule get(int id) {
   WorkerSchedule ws = null;
    try {
        String sql_get_workerSchedule = "SELECT ws.wsid, ws.scid, ws.eid, ws.quantity "
                + "FROM Worker_Schedul ws "
                + "WHERE ws.wsid = ?";
        PreparedStatement stm = connection.prepareStatement(sql_get_workerSchedule);
        stm.setInt(1, id);
        ResultSet rs = stm.executeQuery();
        
        if (rs.next()) {
            ws = new WorkerSchedule();
            ws.setWsid(rs.getInt("wsid"));
            
            Sdeplant sc = new Sdeplant();
            sc.setId(rs.getInt("scid"));
            ws.setSdeplant(sc);
            
            Employee e = new Employee();
            e.setEid(rs.getInt("eid"));
            ws.setEmpolyee(e);
            
            ws.setQuantity(rs.getInt("quantity"));
        }
    } catch (SQLException ex) {
        Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(WorkerScheduleDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return ws;
    }

}
