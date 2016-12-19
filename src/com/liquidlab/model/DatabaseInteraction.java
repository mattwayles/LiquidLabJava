package com.liquidlab.model;

import java.sql.*;

/**
 * DatabaseInteraction.java
 * Author: Matthew Blough-Wayles
 * Created: 12/19/2016
 * Edited: 12/19/2016
 * This class provides the Model of the MVC relationship, dealing with all database requests and manipulation.
 */
public class DatabaseInteraction {

    private Connection connect() {
        Connection conn = null;
        try {
            //db parameters
            String url = "jdbc:sqlite:/git/LiquidLab/src/com/liquidlab/model/liquidlab_db";  ////try to minimzie this path!
            //create database connection
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite Database has been established");
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    public void selectAll() {
        String sql = "SELECT * from flavors";

        try (Connection conn = this.connect();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql))
        {
            while (rs.next()) {
                System.out.println(rs.getString("Name") + "\t" +
                    rs.getInt("mlmade") + "\t" +
                    rs.getString("fl") + "\t" +
                    rs.getString("desc"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void selectFlavor(String flavor) {
        String sql = "SELECT * from flavors WHERE Name = ?" ;

        try (Connection conn = this.connect();
        PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, flavor);
            ResultSet rs = pstmt.executeQuery();

            //loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("Name") + "\t" +
                        rs.getInt("mlmade") + "\t" +
                        rs.getString("fl") + "\t" +
                        rs.getString("desc"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
