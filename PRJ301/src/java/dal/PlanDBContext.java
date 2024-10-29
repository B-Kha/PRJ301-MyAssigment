/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Department;
import model.Plan;
import model.PlanCampain;
import model.Product;

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
        try {
            connection.setAutoCommit(false);

            // Cập nhật bảng Plan mà không ảnh hưởng đến bảng plan_compain
            String sql_update_plan = "UPDATE [Plan] SET start = ?, endd = ?, did = ? WHERE plid = ?";
            PreparedStatement stm_update_plan = connection.prepareStatement(sql_update_plan);
            stm_update_plan.setDate(1, model.getStart());
            stm_update_plan.setDate(2, model.getEnd());
            stm_update_plan.setInt(3, model.getDept().getId());
            stm_update_plan.setInt(4, model.getId());
            stm_update_plan.executeUpdate();

            // Chỉ cập nhật các bản ghi trong plan_compain nếu cần thiết
            // Kiểm tra xem có bất kỳ thay đổi nào về các chiến dịch liên quan
            for (PlanCampain campain : model.getCampains()) {
                if (campain.getId() != 0) {
                    // Nếu campain đã có id, nghĩa là nó đã tồn tại, chỉ cập nhật nó
                    String sql_update_campain = "UPDATE [plan_compain] SET Quantity = ?, estimatedeffort = ? WHERE comid = ?";
                    PreparedStatement stm_update_campain = connection.prepareStatement(sql_update_campain);
                    stm_update_campain.setInt(1, campain.getQuantity());
                    stm_update_campain.setFloat(2, campain.getEstimatedeffort());
                    stm_update_campain.setInt(3, campain.getId());
                    stm_update_campain.executeUpdate();
                    stm_update_campain.close();
                } else {
                    // Nếu campain là mới, thêm nó vào bảng plan_compain
                    String sql_insert_campain = "INSERT INTO [plan_compain] (plid, pid, Quantity, estimatedeffort) VALUES (?, ?, ?, ?)";
                    PreparedStatement stm_insert_campain = connection.prepareStatement(sql_insert_campain);
                    stm_insert_campain.setInt(1, model.getId());
                    stm_insert_campain.setInt(2, campain.getProduct().getId());
                    stm_insert_campain.setInt(3, campain.getQuantity());
                    stm_insert_campain.setFloat(4, campain.getEstimatedeffort());
                    stm_insert_campain.executeUpdate();
                    stm_insert_campain.close();
                }
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
    public void delete(Plan model) {
        try {
            connection.setAutoCommit(false);

            // Xóa các chiến dịch liên quan trong bảng plan_compain
            String sql_delete_campain = "DELETE FROM [plan_compain] WHERE plid = ?";
            PreparedStatement stm_delete_campain = connection.prepareStatement(sql_delete_campain);
            stm_delete_campain.setInt(1, model.getId());
            stm_delete_campain.executeUpdate();

            // Xóa bản ghi trong bảng Plan
            String sql_delete_plan = "DELETE FROM [Plan] WHERE plid = ?";
            PreparedStatement stm_delete_plan = connection.prepareStatement(sql_delete_plan);
            stm_delete_plan.setInt(1, model.getId());
            stm_delete_plan.executeUpdate();

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
    public ArrayList<Plan> list() {
        ArrayList<Plan> plans = new ArrayList<>();
        try {
            String sql = "SELECT plid, start, endd, did FROM [Plan]";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Plan plan = new Plan();
                plan.setId(rs.getInt("plid"));
                plan.setStart(rs.getDate("start"));
                plan.setEnd(rs.getDate("endd"));

                Department dept = new Department();
                dept.setId(rs.getInt("did"));
                plan.setDept(dept);

                plans.add(plan);
            }

            rs.close();
            stm.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plans;
    }

    @Override
    public Plan get(int id) {
        Plan plan = null;
        try {
            String sql = "SELECT plid, start, endd, did FROM [Plan] WHERE plid = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {
                plan = new Plan();
                plan.setId(rs.getInt("plid"));
                plan.setStart(rs.getDate("start"));
                plan.setEnd(rs.getDate("endd"));

                Department dept = new Department();
                dept.setId(rs.getInt("did"));
                plan.setDept(dept);

                // Load related campaigns if needed
                ArrayList<PlanCampain> campaigns = new ArrayList<>();
                String sql_campaign = "SELECT pid, Quantity, estimatedeffort FROM [plan_compain] WHERE plid = ?";
                PreparedStatement stm_campaign = connection.prepareStatement(sql_campaign);
                stm_campaign.setInt(1, id);
                ResultSet rs_campaign = stm_campaign.executeQuery();

                while (rs_campaign.next()) {
                    PlanCampain campaign = new PlanCampain();
                    campaign.setQuantity(rs_campaign.getInt("Quantity"));
                    campaign.setEstimatedeffort(rs_campaign.getFloat("estimatedeffort"));

                    // Assuming you have a method to get Product by ID
                    Product product = new Product();
                    product.setId(rs_campaign.getInt("pid"));
                    campaign.setProduct(product);

                    campaigns.add(campaign);
                }
                rs_campaign.close();
                stm_campaign.close();
                plan.setCampains(campaigns);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            Logger.getLogger(PlanDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return plan;
    }
}
