/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Chucho
 */
public class RemoveItemFromDB extends HttpServlet {

    WebServices ws;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ws = new WebServices();
        String itemName = request.getParameter("itemName");
        ws.removeItemFromDB(itemName);
        response.sendRedirect("index.jsp");
    }

    

}
