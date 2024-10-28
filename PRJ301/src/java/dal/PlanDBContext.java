/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Plan;
import model.PlanCampain;

/**
 *
 * @author Laptop Acer
 */
public class PlanDBContext extends DBContext<Plan> {

    @Override
    public void insert(Plan model) {
        try {
            connection.setAutoCommit(false);
            String sql_insert_plan = "INSERT INTO [Plan]\n"
                    + "           ([start]\n"
                    + "           ,[endd]\n"
                    + "           ,[did])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            String sql_select_plan = "SELECT @@IDENTITY as plid";
            String sql_insert_campain = "INSERT INTO [plan_compain]\n"
                    + "           ([plid]\n"
                    + "           ,[pid]\n"
                    + "           ,[Quantity]\n"
                    + "           ,[estimatedeffort])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_plan = connection.prepareStatement(sql_insert_plan);
            stm_insert_plan.setDate(1, model.getStart());
            stm_insert_plan.setDate(2, model.getEnd());
            stm_insert_plan.setInt(3, model.getDept().getId());
            stm_insert_plan.executeUpdate();

            PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
            ResultSet rs = stm_select_plan.executeQuery();
            if (rs.next()) {
                model.setId(rs.getInt("plid"));
            }
            for (PlanCampain campain : model.getCampains()) {
                PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                stm_insert_campain.setInt(1, model.getId());
                stm_insert_campain.setInt(2, campain.getProduct().getId());
                stm_insert_campain.setInt(3, campain.getQuantity());
                stm_insert_campain.setFloat(4, campain.getEstimatedeffort());
                stm_insert_campain.executeUpdate();
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
    public void update(Plan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Plan model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
         ProductDBContext productDB = new ProductDBContext(); 
         DepartmentDBContext departmentDB = new DepartmentDBContext();
    try {
        String sql_select_plan = "SELECT * FROM [Plan]";
        String sql_select_campain = "SELECT * FROM [plan_compain] WHERE plid = ?";
        
        PreparedStatement stm_select_plan = connection.prepareStatement(sql_select_plan);
        ResultSet rs_plan = stm_select_plan.executeQuery();
        
        while (rs_plan.next()) {
            Plan plan = new Plan();
            plan.setId(rs_plan.getInt("id"));
            plan.setStart(rs_plan.getDate("start"));
            plan.setEnd(rs_plan.getDate("endd"));
            int deptId = rs_plan.getInt("did");
            
            // Đặt thông tin về phòng ban của plan (giả sử bạn có phương thức lấy phòng ban theo ID)
            Department dept = departmentDB.get(deptId);
            plan.setDept(dept);
            
            // Truy vấn các chiến dịch liên quan trong bảng plan_compain
            PreparedStatement stm_select_campain = connection.prepareStatement(sql_select_campain);
            stm_select_campain.setInt(1, plan.getId());
            ResultSet rs_campain = stm_select_campain.executeQuery();
            
            ArrayList<PlanCampain> campains = new ArrayList<>();
            while (rs_campain.next()) {
                PlanCampain campain = new PlanCampain();
                campain.setProduct(productDB.get(rs_campain.getInt("pid"))); // Lấy thông tin sản phẩm
                campain.setQuantity(rs_campain.getInt("Quantity"));
                campain.setEstimatedeffort(rs_campain.getFloat("estimatedeffort"));
                campains.add(campain);
            }
            plan.setCampains(campains);
            plans.add(plan);
        }
    } catch (SQLException ex) {
        Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    return plans;
    }

    @Override
    public Plan get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
