<%-- 
    Document   : index
    Created on : Dec 29, 2017, 6:54:05 PM
    Author     : Chucho
--%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Smart Tek</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <style>
            /* Remove the navbar's default rounded borders and increase the bottom margin */ 
            .navbar {
                margin-bottom: 50px;
                border-radius: 0;
            }

            /* Remove the jumbotron's default bottom margin */ 
            .jumbotron {
                margin-bottom: 0;
            }
            .img-responsive img {
                position: fixed; bottom: 0; left: 0; background: lightgray; width: 100%;
            }
            .center {
                text-align: center;
            }
            .row.display-flex {
                display: flex;
                flex-wrap: wrap;

            }
            .row.display-flex > [class*='col-'] {
                display: flex;
                flex-direction: column;
            }
            .panel-heading{
                text-overflow: ellipsis;
            }
            /* Add a gray background color and some padding to the footer */
            footer {
                background-color: #f2f2f2;
                padding: 25px;
            }
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                padding-top: 100px; /* Location of the box */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: rgb(0,0,0); /* Fallback color */
                background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
            }

            /* Modal Content */
            .modal-content {
                background-color: #fefefe;
                margin: auto;
                padding: 20px;
                border: 1px solid #888;
                width: 80%;
            }

            /* The Close Button */
            .close {
                color: #aaaaaa;
                float: right;
                font-size: 28px;
                font-weight: bold;
            }

            .close:hover,
            .close:focus {
                color: #000;
                text-decoration: none;
                cursor: pointer;
            }
            table, th, td {
                padding: 5px;
            }
        </style>
    </head>
    <body>
        <%!
            WebServices.WebServices ws;
            ArrayList<ArrayList<String>> items;
        %>
        <%
            ws = new WebServices.WebServices();
            items = ws.getItems();
            session.setAttribute("SES_ITEMS", items);
            
        %>
        <div class="jumbotron">
            
        <div id="loggedIn" style="position:absolute;right:0px;top:0px;display:none;">Logged in as <%=(String) session.getAttribute("SES_USERNAME")%></div>
            <div class="container text-center">
                <h1>Smart Tek</h1>
            </div>
        </div>
        <nav class="navbar navbar-inverse">     
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="index.jsp">Home</a></li>
                        <li><a href="orders.jsp">Orders</a></li>
                        <li><a href="about.jsp">About</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" id="register" style="display:none">Register</a></li>
                        <li><a href="#" id="login"  style="display:none">Login</a></li>
                        <li><a href="#" id="logout" style="display:none">Logout</a></li>
                        <li><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div id="registerModal" class="modal">
            <div class="modal-content">
                <span id="registerSpan" class="close">&times;</span>

                <form method="POST" action="Register">
                    <table style="width:100%" >
                        <tr>
                            <td/>
                            <td align="left">Register</td>
                        </tr>
                        <tr>
                            <td align="right">Username :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="username" value="" /><br> </td> 

                        </tr>
                        <tr>
                            <td align="right">Password :&nbsp;</td>
                            <td align="left"><input class="textfield" type="password" name="password1" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">Confirm Password :&nbsp;</td>
                            <td align="left"><input class="textfield" type="password" name="password2" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">First Name :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="firstname" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">Last Name :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="lastname" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">Email :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="email" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">Address :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="address" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">Zip Code :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="zipcode" value="" /><br></td>
                        </tr>
                        <tr>
                            <td align="right">Phone :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="phone" value="" /><br></td>
                        </tr>
                        <tr>
                            <td/>
                            <td align="left"><input type="submit" class="button" value="Submit" name="register" /></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>

        <div id="logoutModal" class="modal" style="display:none">
            <div class="modal-content">
                <span id="logoutSpan" class="close">&times;</span>
                <form method="GET" action="Logout">
                    <table style="width:100%" >

                        <tr>
                            <td align="center">Are you sure you want to logout? &nbsp;</td>
                            <td align="center"><input style="display:none" class="textfield" type="text" name="username" value="<%=session.getAttribute("SES_USERNAME")%>" /><br> </td> 

                        </tr>
                        <tr>
                            <td align="center"><input type="submit" id="logoutBtn" class="button" value="Yes" name="logout" /></td>
                        </tr>
                        <tr>
                            <td align="center"><input id="logoutNo" type="button" class="button" value="No" name="logout" /></td>

                        </tr>

                    </table>

                </form>
            </div>
        </div>
        <div id="loginModal" class="modal">
            <div class="modal-content">
                <span id="loginSpan" class="close">&times;</span>
                <form method="post" action="Login">
                    <table style="width:100%" >
                        <tr>
                            <td/>
                            <td align="left">Login</td>
                        </tr>
                        <tr>
                            <td align="right">Username :&nbsp;</td>
                            <td align="left"><input class="textfield" type="text" name="username" /><br> </td> 

                        </tr>
                        <tr>
                            <td align="right">Password :&nbsp;</td>
                            <td align="left"><input class="textfield" type="password" name="password1" /><br></td>
                        </tr>
                        <tr>
                            <td/>
                            <td align="left"><input type="submit" class="button" value="Submit" name="login" /></td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>
                            
        <div class="container text-center" >    
            <div class="row display-flex">
                
                <%for (int i = 0; i < items.size(); i++) {%>
                <div class="col-xs-4">
                    <div class="panel panel-default" >
                        <form id="item" name="item" method="post" action="">

                            <input type="hidden" name="itemName" value="<%=items.get(i).get(0)%>"/>
                            <div class="panel-heading" id="itemName"><%=items.get(i).get(0)%></div>
                            <div class="panel-body">

                                <input type="hidden" name="itemImg" value="<%=items.get(i).get(3)%>"/>
                                <input type="image" name="itemImg" onclick="return OnImg(this);" value="<%=items.get(i).get(3)%>" src="<%=items.get(i).get(3)%>">

                                <div class="row">
                                    <input type="hidden" name="itemQuantity" value="1"/>
                                    <input type="hidden" name="itemPrice" value="<%=items.get(i).get(2)%>"/>
                                           <p id="itemPrice"><span class="glyphicon glyphicon-euro"></span><%=items.get(i).get(2)%></p>
                                </div>
                                <input type="hidden" name="itemDesc" value="<%=items.get(i).get(1)%>"/>
                                <div id="addItem" name="addItem" class="panel-footer"><button type="button" onclick="return OnAddToCart(this);" class="btn"><span class="glyphicon glyphicon-shopping-cart"></span>Add to  cart</button></div>
                                <div style="display:none" name="removeItem" class="panel-footer"><button type="button" onclick="return OnRemoveItem(this);" class="btn">Remove Item From Database</button></div>

                            </div>
                        </form>
                    </div>    
                </div>
                <%}%>
                <div id="insertItem" style="display:none" class="col-xs-4">
                    <div class="panel panel-default" >
                        <form id="item" name="item" method="post" action="">

                            <div class="panel-body">
                                <div class="panel-heading" id="itemName">Item Name</div>
                                <input type="text" name="itemName" value="Nikon D5300 Kit (18-140 VR)"/>


                                <div class="row">
                                    <p id="itemPrice">Item Price</p>
                                    <input type="text" name="itemPrice" value="843,00"/>
                                </div>
                                <div class="panel-heading" id="itemName">Item Description</div>
                                <input type="text" name="itemDesc" value="DSLR Kit, CMOS Sensor: 24.2MP, Display: 3.2', Photo: 6000 x 4000 pixels, Video: 1920 x 1080 pixels , Manufacturer: Nikon (Digital SLR Cameras)"/>

                                <div class="panel-heading" id="itemName">Item Image Path</div>
                                <input type="text" name="itemImg" value="Images/large_20150513095752_nikon_d5300_kit_18_140_vr.jpeg"/>

                                <div id="itemDesc" class="panel-footer"><button type="button" onclick="return OnInsertNewItem(this);" class="btn">Insert New Item To Database</button></div>

                            </div>
                        </form>
                    </div>    
                </div>
            </div>
        </div>   

        <footer class="container-fluid text-center">
            <p>Smart Tek Copyright &copy;</p>  
        </footer>

        <%String id = (String) session.getAttribute("SES_ID");%>
        <%String username = (String) session.getAttribute("SES_USERNAME");%>
        <%ArrayList<String> cart = (ArrayList<String>) session.getAttribute("SES_CART");
            ArrayList<String> quantities = (ArrayList<String>) session.getAttribute("SES_QUANTITIES");
            ArrayList<ArrayList<String>> orders = (ArrayList<ArrayList<String>>) session.getAttribute("SES_ORDERS");
            if (cart == null) {
        %>
        <form method="POST" action="SetAttributes">
            <input id="setAttributes" type="hidden" >
        </form>
        <%
            }
        %>

        <script>
            var registerModal = document.getElementById('registerModal');
            var reg = document.getElementById("register");
            var spanReg = document.getElementById("registerSpan");
            reg.onclick = function () {
                registerModal.style.display = "block";
            }
            spanReg.onclick = function () {
                registerModal.style.display = "none";
            }

            var loginModal = document.getElementById('loginModal');
            var log = document.getElementById("login");
            var loginSpan = document.getElementById("loginSpan");
            log.onclick = function () {
                loginModal.style.display = "block";
            }
            loginSpan.onclick = function () {
                loginModal.style.display = "none";
            }

            var logoutModal = document.getElementById('logoutModal');
            var logout = document.getElementById("logout");
            var logoutspan = document.getElementById("logoutSpan");
            var logoutNo = document.getElementById("logoutNo");
            logoutNo.onclick = function () {
                logoutModal.style.display = "none";
            }

            logout.onclick = function () {
                logoutModal.style.display = "block";
            }
            logoutspan.onclick = function () {
                logoutModal.style.display = "none";
            }
            <%
                if (session.getAttribute("SES_USERNAME") != null) {

            %>
                document.getElementById("loggedIn").style.display = "block";
            <%
                }
            %>
            <%
                if (session.getAttribute("SES_ID") != null) {

            %>
            document.getElementById("login").style.display = "none";
            document.getElementById("register").style.display = "none";
            document.getElementById("logout").style.display = "block";

            <%                }
                if (session.getAttribute("SES_ID") == null) {
            %>
            document.getElementById("login").style.display = "block";
            document.getElementById("register").style.display = "block";
            document.getElementById("logout").style.display = "none";
            <%
                }
            %>
            if (<%=session.getAttribute("SES_ID")%> === 1) {
                document.getElementById("insertItem").style.display = "block";
                
                var removeItems = document.getElementsByName("removeItem");
                var addItems = document.getElementsByName("addItem");

                for (var i = 0, max = removeItems.length; i < max; i++) {
                    removeItems[i].style.display = "block";
                }
                for (var i = 0, max = addItems.length; i < max; i++) {
                    addItems[i].style.display = "none";
                }
            }
            function OnImg(element)
            {
                $(element).closest('form').attr('action', 'item.jsp');
                $(element).closest('form').submit();

            }

            function OnAddToCart(element)
            {
                $(element).closest('form').attr('action', 'AddToCart');
                $(element).closest('form').submit();

            }
            function OnInsertNewItem(element)
            {
                $(element).closest('form').attr('action', 'InsertNewItem');
                $(element).closest('form').submit();

            }
            function OnRemoveItem(element)
            {
                $(element).closest('form').attr('action', 'RemoveItemFromDB');
                $(element).closest('form').submit();

            }
            window.onload = function () {
                var button = document.getElementById('setAttributes');
                button.form.submit();
            }
        </script>

    </body>
</html>
