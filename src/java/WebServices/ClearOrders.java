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
public class ClearOrders extends HttpServlet {

    WebServices ws;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ws = new WebServices();
        HttpSession session=request.getSession();
        ArrayList<ArrayList<String>> clearedOrders =new ArrayList<>();
        
        String userid = (String) session.getAttribute("SES_ID");
        ws.clearOrders(userid);
        session.setAttribute("SES_ORDERS",clearedOrders);
        response.sendRedirect("orders.jsp");
        
    }

    

}
