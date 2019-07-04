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

public class ClearCart extends HttpServlet {

    WebServices ws;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ws = new WebServices();
        HttpSession session=request.getSession();
        ArrayList<String> clearedCart =new ArrayList<>();
        clearedCart.add("empty");
        ArrayList<String> clearedQuantities =new ArrayList<>();
        clearedQuantities.add("empty");
        
        String userid = (String) session.getAttribute("SES_ID");
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("SES_CART");
        ArrayList<String> quantities = (ArrayList<String>) session.getAttribute("SES_QUANTITIES");
        
        ws.clearCart(userid,cart,quantities);
        session.setAttribute("SES_CART",clearedCart);
        session.setAttribute("SES_QUANTITIES",clearedQuantities);
        response.sendRedirect("cart.jsp");
    }
    

}
