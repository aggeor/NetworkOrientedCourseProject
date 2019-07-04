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
public class RemoveItem extends HttpServlet {
    WebServices ws;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ws = new WebServices();
        HttpSession session = request.getSession();
        String itemName = request.getParameter("removeItem");
        String userid=(String)session.getAttribute("SES_ID");
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("SES_CART");
        ArrayList<String> quantities = (ArrayList<String>) session.getAttribute("SES_QUANTITIES");
        ws.removeItem(userid,itemName);
        for(int i=1;i<cart.size();i++){
            if(cart.get(i).equals(itemName)){
                cart.remove(i);
                quantities.remove(i);
            }
        }
        if(cart.size()==1){
            cart.set(0, "empty");
            quantities.set(0, "empty");
        }
        session.setAttribute("SES_CART",cart);
        session.setAttribute("SES_QUANTITIES",quantities);
        response.sendRedirect("cart.jsp");
    }

    

}
