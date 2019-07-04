/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WebServices;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import java.sql.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 *
 * @author Chucho
 */
@WebService(serviceName = "WebServices")
public class WebServices {
    String URL = "jdbc:mysql://localhost:3306";
    String USERNAME = "root";
    String PASSWORD = "";
    Connection con = null;
    PreparedStatement preparedStatement = null;
    Statement stmt = null;
    String query = null;

    @WebMethod(operationName = "createDB")
    public boolean createDB() throws IOException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            //CREATE DB
            stmt = con.createStatement();
            String query = "CREATE SCHEMA IF NOT EXISTS `smarttek_db` "
                    + "DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci";
            stmt.executeUpdate(query);

            //CREATE items TABLE
            stmt = con.createStatement();
            query = "CREATE TABLE IF NOT EXISTS `smarttek_db`.`items` (\n"
                    + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `name` VARCHAR(100) NOT NULL,\n"
                    + "  `description` VARCHAR(300) NOT NULL,\n"
                    + "  `price` VARCHAR(45) NOT NULL,\n"
                    + "  `image` VARCHAR(100) NOT NULL,\n"
                    + "  PRIMARY KEY (`id`))\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = latin1;";
            stmt.executeUpdate(query);

            //CREATE users TABLE
            stmt = con.createStatement();
            query = "CREATE TABLE IF NOT EXISTS `smarttek_db`.`users` (\n"
                    + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `username` VARCHAR(45) NOT NULL,\n"
                    + "  `password` VARCHAR(45) NOT NULL,\n"
                    + "  `firstname` VARCHAR(45) NOT NULL,\n"
                    + "  `lastname` VARCHAR(45) NOT NULL,\n"
                    + "  `email` VARCHAR(45) NOT NULL,\n"
                    + "  `address` VARCHAR(45) NOT NULL,\n"
                    + "  `zipcode` VARCHAR(45) NOT NULL,\n"
                    + "  `phone` VARCHAR(45) NULL DEFAULT NULL,\n"
                    + "  `status` VARCHAR(45) NOT NULL,\n"
                    + "  PRIMARY KEY (`id`))\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = latin1;";
            stmt.executeUpdate(query);

            //CREATE orders TABLE
            stmt = con.createStatement();
            query = "CREATE TABLE IF NOT EXISTS `smarttek_db`.`orders` (\n"
                    + "  `id` INT(11) NOT NULL AUTO_INCREMENT,\n"
                    + "  `user_id` INT(11) NOT NULL,\n"
                    + "  `timestamp` DATETIME NOT NULL,\n"
                    + "  `status` VARCHAR(45) NOT NULL,\n"
                    + "  PRIMARY KEY (`id`),\n"
                    + "  INDEX `fk_table1_copy2_user1_idx` (`user_id` ASC),\n"
                    + "  CONSTRAINT `fk_table1_copy2_user1`\n"
                    + "    FOREIGN KEY (`user_id`)\n"
                    + "    REFERENCES `smarttek_db`.`users` (`id`)\n"
                    + "    ON DELETE NO ACTION\n"
                    + "    ON UPDATE NO ACTION)\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = latin1;";
            stmt.executeUpdate(query);

            stmt = con.createStatement();
            query = "CREATE TABLE IF NOT EXISTS `smarttek_db`.`order_has_items` (\n"
                    + "  `id` INT NOT NULL AUTO_INCREMENT,\n"
                    + "  `items_id` INT(11) NOT NULL,\n"
                    + "  `orders_id` INT(11) NOT NULL,\n"
                    + "  `quantity` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`id`, `items_id`, `orders_id`),\n"
                    + "  INDEX `fk_items_has_orders_orders1_idx` (`orders_id` ASC),\n"
                    + "  INDEX `fk_items_has_orders_items1_idx` (`items_id` ASC),\n"
                    + "  CONSTRAINT `fk_items_has_orders_items1`\n"
                    + "    FOREIGN KEY (`items_id`)\n"
                    + "    REFERENCES `smarttek_db`.`items` (`id`)\n"
                    + "    ON DELETE NO ACTION\n"
                    + "    ON UPDATE NO ACTION,\n"
                    + "  CONSTRAINT `fk_items_has_orders_orders1`\n"
                    + "    FOREIGN KEY (`orders_id`)\n"
                    + "    REFERENCES `smarttek_db`.`orders` (`id`)\n"
                    + "    ON DELETE NO ACTION\n"
                    + "    ON UPDATE NO ACTION)\n"
                    + "ENGINE = InnoDB\n"
                    + "DEFAULT CHARACTER SET = latin1;";
            stmt.executeUpdate(query);

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Apple iPhone 8 Plus (64GB)','Screen: 5.5'', Storage: 64GB, Camera: 12MP, Network: 4G, SIM: Single, Water Resistant , Manufacturer: Apple','876,86','Images/large_20170915113153_apple_iphone_8_plus.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Xiaomi Redmi 4x (32GB)','Screen: 5.5'', RAM: 3GB, Storage: 32GB, Camera: 13MP, Network: 4G, CPU Cores: 8, SIM: Dual, Battery: 4100mAh , Manufacturer: Xiaomi (Mobile Phones)','143,30','Images/large_20171103170952_xiaomi_redmi_4x_32gb.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Apple MacBook Pro 13.3'' (i5/8GB/128GB) (2017)','Type: Ultrabook, Display: 13.3'' Retina Display, Screen Resolution: 2560x1600, CPU: Intel Core i5 2.3GHz, RAM: 8GB, GPU: Intel Iris Plus Graphics 640, Hard Drive: 128GB SSD, Operating System: macOS, Weight: 1.37kg , Manufacturer: Apple','1.467,00','Images/large_20170609102744_apple_macbook_pro_13_3_i5_8gb_128gb_2017.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Samsung Galaxy S8 (64GB)','Screen: 5.8'', RAM: 4GB, Storage: 64GB, Camera: 12MP, Network: 4G, CPU Cores: 4+4, SIM: Single, Battery: 3000mAh, Water Resistant , Manufacturer: Samsung','579,22','Images/large_20170330105747_samsung_galaxy_s8.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Samsung UE40MU6120','40'', 4K Ultra HD, Smart, HDR, LED, Model: 2017 , Manufacturer: Samsung','545,00','Images/large_20171130144713_samsung_ue40mu6120.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('LG 32LJ500V','43'', 4K Ultra HD, Smart, HDR, LED, Model: 2017 , Manufacturer: LG','222,90','Images/large_20170824151256_lg_32lj500v.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('HP 250 G6 (i5-7200U/8GB/256GB/FHD/No OS)','Type: Notebook, Display: 15.6'' Full HD, Screen Resolution: 1920x1080, CPU: Intel Core i5 7200U 2.5GHz, RAM: 8GB, GPU: Intel HD Graphics 620, Hard Drive: 256GB SSD, Operating System: Windows 10, Weight: 1.86kg , Manufacturer: HP (Laptops)','595,90','Images/large_20171206163233_hp_250_g6_i5_7200u_8gb_256gb_fhd_no_os.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Huawei P10 Lite Dual (3GB/32GB)','Screen: 5.2'', RAM: 4GB, Storage: 32GB, Camera: 12MP, Network: 4G, CPU Cores: 4+4, SIM: Dual, Battery: 3000mAh , Manufacturer: Huawei (Mobile Phones)','233,15','Images/large_20170615150329_huawei_p10_lite_dual_4gb_32gb.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Sony Playstation 4 Pro (PS4 Pro) 1TB','Platform: PlayStation 4 Pro , Manufacturer: Sony','349,00','Images/large_20161017162824_sony_playstation_4_pro_ps4_pro_1tb.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values('Sony KD-55XE9005','55'', 4K Ultra HD, Smart, HDR, LED, Model: 2017 , Manufacturer: Sony','1.199,00','Images/large_20170828134134_sony_kd_55xe9005.jpeg')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            query = "INSERT INTO smarttek_db.users("
                    + "username,password,firstname,lastname,email,address,zipcode,phone,status)"
                    + " values('admin','admin','admin','admin','admin','admin','admin','admin','offline')";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.executeUpdate();

            System.out.println("Database created successfully...");
            con.close();
            return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "register")
    public int register(@WebParam(name = "username") String username,
            @WebParam(name = "password1") String password1,
            @WebParam(name = "password2") String password2,
            @WebParam(name = "firstname") String firstname,
            @WebParam(name = "lastname") String lastname,
            @WebParam(name = "emailAddress") String emailAddress,
            @WebParam(name = "address") String address,
            @WebParam(name = "zipcode") String zipcode,
            @WebParam(name = "phone") String phone) {

        String userid = null;
        if (username == null || password1 == null || password2 == null || firstname == null || lastname == null || emailAddress == null || address == null || zipcode == null) {
            return -1;
        }
        if (username.isEmpty() || password1.isEmpty() || password2.isEmpty() || firstname.isEmpty() || lastname.isEmpty() || emailAddress.isEmpty() || address.isEmpty() || zipcode.isEmpty()) {
            return -1;
        }
        if (password1.equals(password2)) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

                query = "SELECT id FROM smarttek_db.users WHERE username = ?";

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, username);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    userid = rs.getString("id");
                    if (userid != null) {
                        return -1;
                    }
                }

                query = "INSERT INTO smarttek_db.users("
                        + "username,password,firstname,lastname,email,address,zipcode,phone,status)"
                        + " values(?,?,?,?,?,?,?,?,?)";

                preparedStatement = con.prepareStatement(query);

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password1);
                preparedStatement.setString(3, firstname);
                preparedStatement.setString(4, lastname);
                preparedStatement.setString(5, emailAddress);
                preparedStatement.setString(6, address);
                preparedStatement.setString(7, zipcode);
                preparedStatement.setString(8, phone);
                preparedStatement.setString(9, "online");
                preparedStatement.executeUpdate();
                query = "SELECT id FROM smarttek_db.users WHERE username=? AND password=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password1);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    userid = rs.getString("id");
                }
                con.close();
                return Integer.parseInt(userid);
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return -1;
            }
        } else {
            return -1;
        }
    }

    @WebMethod(operationName = "login")
    public int login(@WebParam(name = "username") String username,
            @WebParam(name = "password1") String password1) {
        String userid = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "SELECT id FROM smarttek_db.users WHERE username = ? AND password = ?";

            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password1);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userid = rs.getString("id");
            }
            query = "UPDATE smarttek_db.users SET status = 'online'  WHERE username = ? AND id = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, userid);
            preparedStatement.executeUpdate();
            con.close();
            return Integer.parseInt(userid);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return -1;
        }
    }

    @WebMethod(operationName = "logout")
    public boolean logout(@WebParam(name = "username") String username) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "UPDATE smarttek_db.users SET status='offline'  WHERE username=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
            con.close();
            return true;

        } catch (ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    @WebMethod(operationName = "formatDescription")
    public String formatDescription(@WebParam(name = "itemDesc") String itemDesc) throws IOException {
        try {
            itemDesc = itemDesc.replaceAll(", ", "<br/>&bull; ");
            itemDesc = itemDesc.replaceAll(": ", " : ");
            itemDesc = "&bull; " + itemDesc;
            return itemDesc;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "-1";
        }
    }

    @WebMethod(operationName = "getItemPrice")
    public String getItemPrice(String itemName) {
        String itemPrice = "0";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "SELECT price FROM smarttek_db.items WHERE name=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemName);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                itemPrice = rs.getString("price");
            }
            con.close();
            return itemPrice;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return "-1";
        }
    }

    @WebMethod(operationName = "getTotalPrice")
    public String getTotalPrice(ArrayList<String> cart,
            ArrayList<String> quantities) {
        String sum = "0";
        float sumtemp = 0;
        String lasttwo = "";
        ArrayList<String> prices = new ArrayList<>();
        prices.add("notempty");
        String itemName;
        String itemPrice = "0";
        for (int i = 1; i < cart.size(); i++) {
            itemName = cart.get(i);
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
                query = "SELECT price FROM smarttek_db.items WHERE name=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, itemName);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    itemPrice = rs.getString("price");
                }
                itemPrice.replaceAll(".", "");
                prices.add(itemPrice);
                con.close();
            } catch (Exception e) {
                System.err.println(e.getMessage());
                return "-1";
            }
        }
        for (int i = 1; i < prices.size(); i++) {
            String formatfix = prices.get(i);
            formatfix = formatfix.replace(".", "");
            formatfix = formatfix.replace(",", "");
            lasttwo = formatfix.substring(formatfix.length() - 2, formatfix.length());
            formatfix = formatfix.replace(lasttwo, "." + lasttwo);
            prices.set(i, formatfix);
        }
        for (int i = 1; i < cart.size(); i++) {
            sumtemp += Float.parseFloat(quantities.get(i)) * Float.parseFloat(prices.get(i));
        }
        DecimalFormat df = new DecimalFormat("###.##");
        sum = String.valueOf(df.format(sumtemp));
        return sum;
    }

    @WebMethod(operationName = "submitCart")
    public boolean submitCart(String userid, ArrayList<String> cart,
            ArrayList<String> quantities) {
        String orderid = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            query = "SELECT id FROM smarttek_db.orders WHERE user_id=? AND status=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, "notsubmitted");

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
            }
            query = "UPDATE smarttek_db.orders SET user_id=?,timestamp=?,status=? WHERE id=?";
            preparedStatement = con.prepareStatement(query);
            java.util.Date date = new java.util.Date();
            Object dateTime = new java.sql.Timestamp(date.getTime());
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, dateTime.toString());
            preparedStatement.setString(3, "submitted");
            preparedStatement.setString(4, orderid);
            preparedStatement.executeUpdate();

            for (int i = 1; i < cart.size(); i++) {
                query = "UPDATE smarttek_db.order_has_items SET"
                        + " items_id=((SELECT id FROM smarttek_db.items WHERE name=?)),"
                        + "orders_id=?,quantity=?";

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, cart.get(i));
                preparedStatement.setString(2, orderid);
                preparedStatement.setString(3, quantities.get(i));

                preparedStatement.executeUpdate();
            }
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "editCart")
    public boolean editCart(String userid, ArrayList<String> cart,
            ArrayList<String> quantities) {
        String orderid = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            query = "SELECT id FROM smarttek_db.orders WHERE status='notsubmitted' and user_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
            }
            for (int i = 1; i < quantities.size(); i++) {
                query = "UPDATE smarttek_db.order_has_items SET quantity=? WHERE items_id=(SELECT id FROM smarttek_db.items WHERE name=?) and orders_id=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, quantities.get(i));
                preparedStatement.setString(2, cart.get(i));
                preparedStatement.setString(3, orderid);
                preparedStatement.executeUpdate();
            }
            con.close();
            return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "addToCart")
    public boolean addToCart(String userid,
            ArrayList<String> cart,
            ArrayList<String> quantities,
            String itemName,
            String itemQuantity) {
        String orderid = null;
        String orderitemid = null;
        String orderitemquantity = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            query = "SELECT id FROM smarttek_db.orders WHERE status='notsubmitted' and user_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
            }
            if (orderid == null) {
                query = "INSERT INTO smarttek_db.orders("
                        + "user_id,timestamp,status)"
                        + " values(?,?,?)";

                preparedStatement = con.prepareStatement(query);

                java.util.Date date = new java.util.Date();
                Object dateTime = new java.sql.Timestamp(date.getTime());
                preparedStatement.setString(1, userid);
                preparedStatement.setString(2, dateTime.toString());
                preparedStatement.setString(3, "notsubmitted");
                preparedStatement.executeUpdate();

                query = "SELECT id FROM smarttek_db.orders WHERE timestamp=? and user_id=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, dateTime.toString().substring(0, dateTime.toString().length() - 3));
                preparedStatement.setString(2, userid);

                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    orderid = rs.getString("id");
                }
                query = "INSERT INTO smarttek_db.order_has_items("
                        + "items_id,orders_id,quantity)"
                        + " values((SELECT id FROM smarttek_db.items WHERE name=?),?,?)";

                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, cart.get(1));
                preparedStatement.setString(2, orderid);
                preparedStatement.setString(3, quantities.get(1));

                preparedStatement.executeUpdate();
            } else {

                query = "SELECT id,quantity FROM smarttek_db.order_has_items WHERE items_id=(SELECT id FROM smarttek_db.items WHERE name=?)";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, itemName);

                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    orderitemid = rs.getString("id");
                    orderitemquantity = rs.getString("quantity");
                }
                if (orderitemid == null) {

                    query = "INSERT INTO smarttek_db.order_has_items("
                            + "items_id,orders_id,quantity)"
                            + " values((SELECT id FROM smarttek_db.items WHERE name=?),?,?)";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, cart.get(cart.size() - 1));
                    preparedStatement.setString(2, orderid);
                    preparedStatement.setString(3, quantities.get(quantities.size() - 1));
                    preparedStatement.executeUpdate();
                } else {
                    query = "UPDATE smarttek_db.order_has_items SET quantity=? WHERE items_id=(SELECT id FROM smarttek_db.items WHERE name=?) and orders_id=?";
                    preparedStatement = con.prepareStatement(query);

                    preparedStatement.setString(1, String.valueOf(Integer.parseInt(orderitemquantity) + Integer.parseInt(itemQuantity)));
                    preparedStatement.setString(2, itemName);
                    preparedStatement.setString(3, orderid);
                    preparedStatement.executeUpdate();
                }
            }
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "clearCart")
    public boolean clearCart(String userid,
            ArrayList<String> cart,
            ArrayList<String> quantities) {
        String orderid = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            query = "SELECT id FROM smarttek_db.orders WHERE status='notsubmitted' and user_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
            }
            for (int i = 1; i < cart.size(); i++) {
                query = "DELETE FROM smarttek_db.order_has_items WHERE items_id=(SELECT id FROM smarttek_db.items WHERE name=?) and orders_id=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, cart.get(i));
                preparedStatement.setString(2, orderid);
                preparedStatement.executeUpdate();
            }
            query = "DELETE FROM smarttek_db.orders WHERE status='notsubmitted' and user_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);
            preparedStatement.executeUpdate();
            con.close();
            return true;

        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "removeItem")
    public boolean removeItem(String userid,
            String itemName) {
        String orderid = null;
        String itemorderid = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "SELECT id FROM smarttek_db.orders WHERE status='notsubmitted' and user_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
            }
            query = "DELETE FROM smarttek_db.order_has_items WHERE items_id=(SELECT id FROM smarttek_db.items WHERE name=?) and orders_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, orderid);
            preparedStatement.executeUpdate();

            query = "SELECT id FROM smarttek_db.order_has_items WHERE orders_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, orderid);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                itemorderid = rs.getString("id");
            }
            if (itemorderid == null) {
                query = "DELETE FROM smarttek_db.orders WHERE status='notsubmitted' and user_id=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, userid);
                preparedStatement.executeUpdate();
            }
            con.close();
            return true;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "getCart")
    public ArrayList<ArrayList<String>> getCart(@WebParam(name = "userid") String userid) {

        String orderid = null;
        String itemid = null;
        String quantity = null;
        String itemname = null;
        ArrayList<String> cart = new ArrayList<>();
        ArrayList<String> quantities = new ArrayList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        cart.add("empty");
        quantities.add("empty");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "SELECT id FROM smarttek_db.orders WHERE user_id = ? and status = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);
            preparedStatement.setString(2, "notsubmitted");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
            }
            query = "SELECT items_id,quantity FROM smarttek_db.order_has_items WHERE orders_id = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, orderid);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                itemid = rs.getString("items_id");
                quantity = rs.getString("quantity");
                if (itemid == null) {
                    cart.set(0, "empty");
                    quantities.set(0, "empty");
                } else {
                    cart.set(0, "notempty");
                    quantities.set(0, "notempty");
                    query = "SELECT name FROM smarttek_db.items WHERE id = ?";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, itemid);
                    ResultSet rs2 = preparedStatement.executeQuery();
                    while (rs2.next()) {
                        itemname = rs2.getString("name");
                    }
                    cart.add(itemname);
                    quantities.add(quantity);
                }
            }
            result.add(cart);
            result.add(quantities);
            con.close();
            return result;
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "getOrders")
    public ArrayList<ArrayList<String>> getOrders(String userid) {

        ArrayList<String> cart = new ArrayList<>();
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        cart.add("empty");
        String orderid = null;
        String status = null;
        String itemid = null;
        String quantity = null;
        String itemname = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if ("1".equals(userid)) {
                query = "SELECT id,status FROM smarttek_db.orders";
                preparedStatement = con.prepareStatement(query);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    orderid = rs.getString("id");
                    status = rs.getString("status");

                    query = "SELECT items_id,quantity FROM smarttek_db.order_has_items WHERE orders_id = ?";
                    preparedStatement = con.prepareStatement(query);
                    preparedStatement.setString(1, orderid);
                    ResultSet rs2 = preparedStatement.executeQuery();
                    while (rs2.next()) {
                        itemid = rs2.getString("items_id");
                        quantity = rs2.getString("quantity");
                        if (itemid == null) {
                            cart.set(0, "empty");
                        } else {
                            cart.set(0, orderid);
                            query = "SELECT name FROM smarttek_db.items WHERE id = ?";
                            preparedStatement = con.prepareStatement(query);
                            preparedStatement.setString(1, itemid);
                            ResultSet rs3 = preparedStatement.executeQuery();
                            while (rs3.next()) {
                                itemname = rs3.getString("name");
                            }
                            cart.add(itemname);
                            cart.add(quantity);
                            cart.add(status);
                        }
                    }
                    result.add(cart);
                    cart = new ArrayList<>();
                    cart.add("empty");
                }
                con.close();
                return result;

            }
            query = "SELECT id,status FROM smarttek_db.orders WHERE user_id = ?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
                status = rs.getString("status");

                query = "SELECT items_id,quantity FROM smarttek_db.order_has_items WHERE orders_id = ?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, orderid);
                ResultSet rs2 = preparedStatement.executeQuery();
                while (rs2.next()) {
                    itemid = rs2.getString("items_id");
                    quantity = rs2.getString("quantity");
                    if (itemid == null) {
                        cart.set(0, "empty");
                    } else {
                        cart.set(0, orderid);
                        query = "SELECT name FROM smarttek_db.items WHERE id = ?";
                        preparedStatement = con.prepareStatement(query);
                        preparedStatement.setString(1, itemid);
                        ResultSet rs3 = preparedStatement.executeQuery();
                        while (rs3.next()) {
                            itemname = rs3.getString("name");
                        }
                        cart.add(itemname);
                        cart.add(quantity);
                        cart.add(status);
                    }
                }
                result.add(cart);
                cart = new ArrayList<>();
                cart.add("empty");
            }
            con.close();
            return result;
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "clearOrders")
    public boolean clearOrders(String userid) {

        String orderid = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "SELECT id FROM smarttek_db.orders WHERE status='submitted' and user_id=?";
            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, userid);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                orderid = rs.getString("id");
                query = "DELETE FROM smarttek_db.order_has_items WHERE orders_id=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, orderid);
                preparedStatement.executeUpdate();
                query = "DELETE FROM smarttek_db.orders WHERE id=?";
                preparedStatement = con.prepareStatement(query);
                preparedStatement.setString(1, orderid);
                preparedStatement.executeUpdate();
            }
            con.close();
            return true;
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return false;
        }
    }

    @WebMethod(operationName = "getItems")
    public ArrayList<ArrayList<String>> getItems() {

        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> item = new ArrayList<>();
        String name = null;
        String description = null;
        String price = null;
        String image = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            query = "SELECT * FROM smarttek_db.items";
            preparedStatement = con.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                name = rs.getString("name");
                description = rs.getString("description");
                price = rs.getString("price");
                image = rs.getString("image");
                item.add(name);
                item.add(description);
                item.add(price);
                item.add(image);
                result.add(item);
                item = new ArrayList<>();
            }
            con.close();
            return result;
        } catch (NumberFormatException | ClassNotFoundException | SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @WebMethod(operationName = "insertItem")
    public void insertItem(String itemName, String itemPrice, String itemDesc, String itemImg) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            query = "INSERT INTO smarttek_db.items("
                    + "name,description,price,image)"
                    + " values(?,?,?,?)";

            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemName);
            preparedStatement.setString(2, itemDesc);
            preparedStatement.setString(3, itemPrice);
            preparedStatement.setString(4, itemImg);
            preparedStatement.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @WebMethod(operationName = "removeItemFromDB")
    public void removeItemFromDB(String itemName) {

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            query = "DELETE FROM smarttek_db.items WHERE name=?";

            preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1, itemName);
            preparedStatement.executeUpdate();

            con.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

}
