/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller.access;

import dal.UserDBContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import model.accesscontrol.Feature;
import model.accesscontrol.Role;
import model.accesscontrol.User;

/**
 *
 * @author Laptop Acer
 */
public abstract class BaseRBACController extends BaseRequiredAuthenticationController {

    private boolean isAuthorized(HttpServletRequest req, User loggeduser) {
        UserDBContext db = new UserDBContext();
        ArrayList<Role> roles = db.getRoles(loggeduser.getUsername());
        loggeduser.setRoles(roles);
        String c_url = req.getServletPath();
        for (Role role : roles) {
            for (Feature feature : role.getFeatures()) {
                if (feature.getUrl().equals(c_url)) {
                    return true;
                }
            }
        }

        return false;
    }

    protected abstract void doAuthorizedGet(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;

    protected abstract void doAuthorizedPost(HttpServletRequest req, HttpServletResponse resp, User loggeduser) throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response, User loggeduser) throws ServletException, IOException {
         if(isAuthorized(request, loggeduser))
        {
            doAuthorizedGet(request, response, loggeduser);
        }
        else
            response.sendError(403, "you do not have right to access this feature!");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response,
             User loggeduser) throws ServletException, IOException {
         if(isAuthorized(request, loggeduser))
        {
            doAuthorizedPost(request, response, loggeduser);
        }
        else
            response.sendError(403, "you do not have right to access this feature!");
    }

}
