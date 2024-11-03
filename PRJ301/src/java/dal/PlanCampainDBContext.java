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
import java.util.ArrayList;
import model.PlanCampain;

/**
 *
 * @author Laptop Acer
 */
public class PlanCampainDBContext extends DBContext<PlanCampain> {

    @Override
    public void insert(PlanCampain model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(PlanCampain model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(PlanCampain model) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<PlanCampain> list() {
        ArrayList<PlanCampain> planCampains = new ArrayList<>();
        try {
            String sql_list_planCampain = "SELECT pc.comid, pc.plid, pc.pid, pc.Quantity, pc.estimateddeffort "
                    + "FROM plan_compain pc";
            PreparedStatement stm = connection.prepareStatement(sql_list_planCampain);
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                PlanCampain pc = new PlanCampain();
                pc.setId(rs.getInt("comid"));

                Plan plan = new Plan();
                plan.setId(rs.getInt("plid"));
                pc.setPlan(plan);

                Product product = new Product();
                product.setId(rs.getInt("pid"));
                pc.setProduct(product);

                pc.setQuantity(rs.getInt("Quantity"));
                pc.setEstimatedeffort(rs.getFloat("estimateddeffort"));

                planCampains.add(pc);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PlanCampainDBContext.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(PlanCampainDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return planCampains;
    }

    @Override
    public PlanCampain get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
