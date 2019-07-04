/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chucho
 */
public class Logout extends HttpServlet {

    WebServices ws;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ws = new WebServices();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("SES_USERNAME");
        ws.logout(username);
        ArrayList<String> cart = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        ArrayList<String> orders = new ArrayList<>();
        cart.add(0,"empty");
        quantities.add(0,"empty");
        orders.add(0,"empty");
        session.setAttribute("SES_ID", null);
        session.setAttribute("SES_USERNAME", null);
        session.setAttribute("SES_CART", cart);
        session.setAttribute("SES_QUANTITIES", quantities);
        session.setAttribute("SES_ORDERS", orders);
        response.sendRedirect("index.jsp");

    }

}
