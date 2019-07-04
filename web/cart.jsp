<%-- 
    Document   : cart
    Created on : Dec 29, 2017, 5:50:36 PM
    Author     : Chucho
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Smart Tek</title>
        <meta charset="utf-8">
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
            String itemName;
            String itemImg;
            String itemDesc;
            String itemPrice;
            ArrayList<String> cart;
            ArrayList<String> quantities;
        %>
        <% ws = new WebServices.WebServices();
            itemName = request.getParameter("itemName");
            itemImg = request.getParameter("itemImg");
            itemDesc = request.getParameter("itemDesc");
            itemPrice = request.getParameter("itemPrice");
            cart = (ArrayList<String>) session.getAttribute("SES_CART");
            quantities = (ArrayList<String>) session.getAttribute("SES_QUANTITIES");
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
                        <li><a href="index.jsp">Home</a></li>
                        <li><a href="orders.jsp">Orders</a></li>
                        <li><a href="about.jsp">About</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <li><a href="#" id="register" style="display:none">Register</a></li>
                        <li><a href="#" id="login" style="display:none">Login</a></li>
                        <li><a href="#" id="logout" style="display:none">Logout</a></li>
                        <li class="active"><a href="cart.jsp"><span class="glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
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
                            <td align="center"><input style="display:none" class="textfield" type="text" name="username" value="<%=session.getAttribute("SES_USERNAME")%>"/><br> </td> 

                        </tr>
                        <tr>
                            <td align="center"><input type="submit" class="button" value="Yes" name="logout" /></td>
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
                <form method="POST" action="Login">
                    <table style="width:100%" >
                        <tr>
                            <td/>
                            <td align="left">Login</td>
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
                            <td/>
                            <td align="left"><input type="submit" class="button" value="Submit" name="login" /></td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>

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

        </script>
        <form style="width:100%;text-align: center; margin: 0px auto;" method="POST">
            <table style="width:50%;text-align: center; margin: 0px auto;" border="1">
                <tbody>


                    <%if (cart.get(0).equals("notempty")) {%>
                    <tr>
                        <td><b>Item Name</b></td>
                        <td><b>Quantity</b></td>
                        <td><b>Price</b></td>
                    </tr>
                    <%for (int i = 1; i < cart.size(); i++) {%>
                    <tr>
                        <td>
                            <%=cart.get(i)%>
                            <input type="hidden" name="itemName<%=i%>" value="<%=cart.get(i)%>">
                        </td>
                        <td><input name="itemQuantity" value="<%=quantities.get(i)%>"></td>
                        <td name="itemPrice">
                            <button align="left" type="submit" class="close" aria-label="Close" name="removeItem" value="<%=cart.get(i)%>" onclick="OnRemove(this)">
                                <span aria-hidden="true">&times;</span>
                            </button>
                            <%=ws.getItemPrice(cart.get(i))%></td>
                    </tr>
                    <%}%>
                    <tr>
                        <td/>
                        <td><b>Total: </b></td>
                        <td><b><%=ws.getTotalPrice(cart, quantities)%></b></td>
                    </tr>

                </tbody>
            </table>
            <input type="submit" id="submitBtn" class="button" value="Submit" name="submitOrder" onclick="OnSubmit(this);" />
            <input type="submit" id="submitBtn" class="button" value="Edit Cart" name="editOrder" onclick="OnEdit(this);" />
            <input type="submit" id="submitBtn" class="button" value="Clear Cart" name="clearOrder" onclick="OnClear(this);" />
            <%} else {%>
            <table style="width:50%;text-align: center; margin: 0px auto;" border="1">
                <tbody>
                    <tr>
                        <td><b><p style="color:red">Your cart is empty!</p></b></td>
                    </tr>
                </tbody>
            </table>
            
            <%}%>
        </form>
        <footer class="container-fluid text-center">
            <p>Smart Tek Copyright &copy;</p>  
        </footer>
        
        <script>
            function OnSubmit(element)
            {
                $(element).closest('form').attr('action', 'SubmitCart');
                $(element).closest('form').submit();

            }
            function OnEdit(element)
            {
                $(element).closest('form').attr('action', 'EditCart');
                $(element).closest('form').submit();

            }
            function OnRemove(element)
            {
                $(element).closest('form').attr('action', 'RemoveItem');
                $(element).closest('form').submit();

            }
            function OnClear(element)
            {
                $(element).closest('form').attr('action', 'ClearCart');
                $(element).closest('form').submit();

            }
        </script>
        <script>
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
        </script>

    </body>
</html>
