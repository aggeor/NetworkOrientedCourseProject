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
public class Login extends HttpServlet {

    WebServices ws;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ws = new WebServices();
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password1 = request.getParameter("password1");
        ArrayList<String> cart = new ArrayList<String>();
        ArrayList<String> quantities = new ArrayList<String>();
        ArrayList<ArrayList<String>> orders = new ArrayList<ArrayList<String>>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        cart.add(0, "empty");
        quantities.add(0, "empty");
        String id = String.valueOf(ws.login(username, password1));
        result = ws.getCart(id);
        cart = result.get(0);
        quantities = result.get(1);
        session.setAttribute("SES_CART", cart);
        session.setAttribute("SES_QUANTITIES", quantities);
        if (Integer.parseInt(id) != -1) {
            session.setAttribute("SES_ID", id);
            session.setAttribute("SES_USERNAME", username);
            session.setAttribute("SES_CART", cart);
            session.setAttribute("SES_QUANTITIES", quantities);
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
