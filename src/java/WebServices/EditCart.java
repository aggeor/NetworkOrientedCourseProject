/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Chucho
 */
public class EditCart extends HttpServlet {

    WebServices ws;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ws = new WebServices();
        HttpSession session=request.getSession();
        ArrayList<String> finalQuantities =new ArrayList<>();
        finalQuantities.add("notempty");
        ArrayList<String> editedQuantities =new ArrayList<>(Arrays.asList(request.getParameterValues("itemQuantity"))) ;
        finalQuantities.addAll(editedQuantities);
        String userid = (String) session.getAttribute("SES_ID");
        ArrayList<String> cart = (ArrayList<String>) session.getAttribute("SES_CART");
        ArrayList<String> quantities = (ArrayList<String>) session.getAttribute("SES_QUANTITIES");
        ws.editCart(userid,cart,finalQuantities);
        session.setAttribute("SES_QUANTITIES",finalQuantities);
        response.sendRedirect("cart.jsp");
    }

}
