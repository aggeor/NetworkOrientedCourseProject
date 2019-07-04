/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chucho
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

    WebServices ws;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ws = new WebServices();
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");
        String emailAddress = request.getParameter("email");
        String address = request.getParameter("address");
        String zipcode = request.getParameter("zipcode");
        String  phone = request.getParameter("phone");
        ArrayList<String> cart = new ArrayList<String>();
        ArrayList<String> quantities = new ArrayList<String>();
        cart.add(0,"empty");
        quantities.add(0,"empty");
        
        String id = String.valueOf(ws.register(username, password1, password2, firstName, lastName, emailAddress, address, zipcode, phone));
        if (Integer.parseInt(id) != -1) {
            HttpSession session = request.getSession();
            session.setAttribute("SES_ID", id);
            session.setAttribute("SES_USERNAME", username);
            session.setAttribute("SES_CART",cart);
            session.setAttribute("SES_QUANTITIES",quantities);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
