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
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

    WebServices ws;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ws = new WebServices();
        HttpSession session = request.getSession();
        String itemName = request.getParameter("itemName");
        String itemQuantity = request.getParameter("itemQuantity");
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("SES_CART");
        ArrayList<String> quantities = (ArrayList<String>) session.getAttribute("SES_QUANTITIES");
        boolean added = false;
        int value;
        if(cart==null){
            response.sendRedirect("index.jsp");
        }else{
        if (cart.get(0).equals("empty")) {
            cart.set(0, "notempty");
            quantities.set(0, "notempty");
            cart.add(itemName);
            quantities.add(itemQuantity);
        } else {
            for (int i = 1; i < cart.size(); i++) {
                if (cart.get(i).equals(itemName)) {
                    added = true;
                    value = Integer.parseInt(quantities.get(i)) + Integer.parseInt(itemQuantity);
                    quantities.set(i, String.valueOf(value));
                    break;
                } else {

                }
            }
            if (!added) {
                cart.add(itemName);
                quantities.add("1");
            }
        }
        session.setAttribute("SES_CART", cart);
        session.setAttribute("SES_QUANTITIES", quantities);
        String userid=(String)session.getAttribute("SES_ID");
        ws.addToCart(userid,cart,quantities,itemName,itemQuantity);
        response.sendRedirect("cart.jsp");
    }
        

    }

}
