package com.liquidlab.model;


import java.sql.*;
import java.util.ArrayList;

/**
 * Provides the Model of the MVC relationship, dealing with all database requests and manipulation. This class is
 * hard-coded to retrieve data frm the liquiclab_db SQLite3 database, located in the model package at
 * <LiquidLab directory>/LiquidLab/src/com/liquidlab/model
 *
 * @author Matthew Blough-Wayles
 * @version 1.0
 * @since 1.0
 */

public class DatabaseInteraction {
    private ArrayList<String> myResults;

    public ArrayList<String> getResults() {
        return myResults;
    }

    private void setResults(ArrayList<String> res) {
        myResults = res;
    }

    /**
     * Opens a connection to the flavor database in order to query, insert, update, and remove data.
     *
     * @return <code>conn</code> An open connection to the local SQLite Database liquidlab_db.
     */
    private Connection connect() {
        Connection conn = null;
        try {
            //set database parameters
            String url = "jdbc:sqlite:src/com/liquidlab/model/liquidlab_db";  ////try to minimize this path!
            //create database connection
            conn = DriverManager.getConnection(url);
        }
        //catch exception if database does not exist or is inaccessible
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        //return open database connection for data retrieval and manipulation
        return conn;
    }

    /**
     * Retrieve a row of data from the database, or all rows if the asterisk wildcard ("*") is used.
     *
     * @param flavor The name of the flavor (Primary Key) to be retrieved from the database. Also accepts
     *               the asterisk wildcard ("*").
     */
    public void selectFlavor(String flavor) {
        String sqlcmd;

        this.setResults(new ArrayList<>());

        //if user wants to retrieve all flavors from database
        if (flavor.equals("*")) {
            sqlcmd = "SELECT * from flavors";
        }
        //if user wants a specific flavor
        else {
            sqlcmd = "SELECT * from flavors WHERE Name = ?";
        }

        //create database connection
        try (Connection conn = this.connect();
             //create sql statement
             PreparedStatement pstmt = conn.prepareStatement(sqlcmd)) {
            if (!flavor.equals("*")) {
                //add variables to sql statement
                pstmt.setString(1, flavor);
            }
            //capture results from query execution
            ResultSet rs = pstmt.executeQuery();

            //loop through the result set

            while (rs.next()) {
                getResults().add(rs.getString("Name"));
                getResults().add(rs.getString("fl"));
                getResults().add(rs.getString("desc"));
                //System.out.println(rs.getString("Name") + "\t" +
                //rs.getString("fl") + "\t" +
                //rs.getString("desc"));
            }
        }
        //catch exception if database does not exist or is inaccessible
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Add a new flavor to the database.
     *
     * @param name The name (Primary Key) of the flavor being inserted.
     * @param fl   A string representation of the list of base flavors and percentages that make up the
     *             final flavor to be added.
     * @param desc A description of the flavor being added.
     */
    public void insert(String name, String fl, String desc) {
        String sqlcmd = "INSERT INTO flavors(name, fl, desc) VALUES(?,?,?)";

        //create database connection
        try (Connection conn = this.connect();
             //create sql statement
             PreparedStatement pstmt = conn.prepareStatement(sqlcmd)) {
            //add variables to sql statement
            pstmt.setString(1, name);
            pstmt.setString(2, fl);
            pstmt.setString(3, desc);
            //execute sql statement
            pstmt.executeUpdate();

        }
        //catch exception if database does not exist or is inaccessible
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update a flavor in the database.
     *
     * @param name The name (Primary Key) of the flavor being updated.
     * @param fl   A string representation of the list of base flavors and percentages that make up the
     *             final flavor to be updated.
     * @param desc A description of the flavor being updated.
     */
    public void update(String name, String fl, String desc) {
        String sqlcmd = "UPDATE flavors SET fl = ?, desc = ? WHERE name = ?";

        //create database connection
        try (Connection conn = this.connect();
             //create sql statement
             PreparedStatement pstmt = conn.prepareStatement(sqlcmd)) {
            //add variables to sql statement
            pstmt.setString(1, fl);
            pstmt.setString(2, desc);
            pstmt.setString(3, name);
            //execute sql statement
            pstmt.executeUpdate();

        }
        //catch exception if database does not exist or is inaccessible
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Deletes a flavor in the database.
     *
     * @param name The name (Primary Key) of the flavor being deleted.
     */
    public void delete(String name) {
        String sqlcmd;

        //if user wants to wipe entire table
        if (name.equals("*")) {
            sqlcmd = "DELETE from flavors";
        }
        //if user only wants to remove a single flavor
        else {
            sqlcmd = "DELETE from flavors WHERE name = ?";
        }

        //create database connection
        try (Connection conn = this.connect();
             //create sql statement
             PreparedStatement pstmt = conn.prepareStatement(sqlcmd)) {
            if (!name.equals("*")) {
                //add variable to sql statement
                pstmt.setString(1, name);
            }
            //execute sql statement
            pstmt.executeUpdate();
        }
        //catch exception if database does not exist or is inaccessible
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
