//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab.model;

import com.liquidlab.view.UserInterface;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseInteraction {
    private static ArrayList<String> ourResults;

    public static ArrayList<String> getResults() {
        return ourResults;
    }

    private static void setResults(ArrayList<String> res) {
        ourResults = res;
    }

    private static Connection connect() {
        Connection conn = null;

        try {
            String e = "jdbc:sqlite:src/com/liquidlab/model/liquidlab_db";
            conn = DriverManager.getConnection(e);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return conn;
    }

    public static void selectFlavor(String flavor) {
        setResults(new ArrayList<>());
        String sqlcmd;
        if(flavor.equals("*")) {
            sqlcmd = "SELECT * from flavors";
        } else {
            sqlcmd = "SELECT * from flavors WHERE Name = ?";
        }

        try {
            Connection e = connect();
            PreparedStatement pstmt = e.prepareStatement(sqlcmd);
            if(!flavor.equals("*")) {
                pstmt.setString(1, flavor);
            }

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                getResults().add(rs.getString("Name"));
                getResults().add(rs.getString("fl"));
                getResults().add(rs.getString("desc"));
            }
            e.close();
        } catch (SQLException ex) {
            UserInterface.msgBox("ERROR", 5);
        }
    }

    public static void selectNotes(String flavor)
    {
        setResults(new ArrayList<>());
        String sqlcmd = "SELECT * from flavors WHERE name = ?";

        try {
            Connection e = connect();
            PreparedStatement pstmt = e.prepareStatement(sqlcmd);
            pstmt.setString(1, flavor);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                getResults().add(rs.getString("desc"));
            }
            e.close();
        } catch (Exception ex) {}
    }

    public static void queryForValues() {
        setResults(new ArrayList<>());
        String sqlcmd = "SELECT * from userinputs";

        try {
            Connection e = connect();
            PreparedStatement pstmt = e.prepareStatement(sqlcmd);
            ResultSet rs = pstmt.executeQuery();
            if(!rs.isClosed()) {
                getResults().add(rs.getString("nicStrength"));
            }
            e.close();
        } catch (SQLException ex) {
            UserInterface.msgBox("ERROR", 5);
        }

    }

    public static void selectValues() {
        setResults(new ArrayList<>());
        String sqlcmd = "SELECT * from userinputs";

        try {
            Connection e = connect();
            Throwable var2 = null;

            try {
                PreparedStatement pstmt = e.prepareStatement(sqlcmd);
                Throwable var4 = null;

                try {
                    ResultSet rs = pstmt.executeQuery();

                    while(rs.next()) {
                        getResults().add(rs.getString("nicStrength"));
                        getResults().add(rs.getString("nicBasePg"));
                        getResults().add(rs.getString("nicBaseVg"));
                        getResults().add(rs.getString("flavWt"));
                        getResults().add(rs.getString("nicWt"));
                        getResults().add(rs.getString("pgWt"));
                        getResults().add(rs.getString("vgWt"));
                    }
                } catch (Throwable var29) {
                    var4 = var29;
                    throw var29;
                } finally {
                    if(pstmt != null) {
                        if(var4 != null) {
                            try {
                                pstmt.close();
                            } catch (Throwable var28) {
                                var4.addSuppressed(var28);
                            }
                        } else {
                            pstmt.close();
                        }
                    }

                }
            } catch (Throwable var31) {
                var2 = var31;
                throw var31;
            } finally {
                if(e != null) {
                    if(var2 != null) {
                        try {
                            e.close();
                        } catch (Throwable var27) {
                            var2.addSuppressed(var27);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var33) {
            UserInterface.msgBox("ERROR", 5);
        }

    }

    public static void insertFlavor(String name, String fl, String desc) {
        String sqlcmd = "INSERT INTO flavors(name, fl, desc) VALUES(?,?,?)";

        try {
            Connection e = connect();
            Throwable var5 = null;

            try {
                PreparedStatement pstmt = e.prepareStatement(sqlcmd);
                Throwable var7 = null;

                try {
                    pstmt.setString(1, name);
                    pstmt.setString(2, fl);
                    pstmt.setString(3, desc);
                    pstmt.executeUpdate();
                } catch (Throwable var32) {
                    var7 = var32;
                    throw var32;
                } finally {
                    if(pstmt != null) {
                        if(var7 != null) {
                            try {
                                pstmt.close();
                            } catch (Throwable var31) {
                                var7.addSuppressed(var31);
                            }
                        } else {
                            pstmt.close();
                        }
                    }

                }
            } catch (Throwable var34) {
                var5 = var34;
                throw var34;
            } finally {
                if(e != null) {
                    if(var5 != null) {
                        try {
                            e.close();
                        } catch (Throwable var30) {
                            var5.addSuppressed(var30);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var36) {
            System.out.println(var36.getMessage());
        }

    }

    public static void insertValues(String nicStrength, String nicBasePg, String nicBaseVg, String flavWt, String nicWt, String pgWt, String vgWt) {
        String deleteCmd = "DELETE from userinputs;";
        String insertCmd = "INSERT INTO userinputs(nicStrength, nicBasePg, nicBaseVg, flavWt, nicWt, pgWt, vgWt) VALUES(?,?,?,?,?,?,?);";

        Connection e;
        PreparedStatement deleteStmt;
        PreparedStatement insertStmt;
        try {
        e = connect();
        deleteStmt = e.prepareStatement(deleteCmd);
        deleteStmt.executeUpdate();
        e.close();
        } catch (SQLException var101) {
            UserInterface.msgBox("ERROR", 5);
        }

        try {
            e = connect();
            insertStmt = e.prepareStatement(insertCmd);
            insertStmt.setString(1, nicStrength);
            insertStmt.setString(2, nicBasePg);
            insertStmt.setString(3, nicBaseVg);
            insertStmt.setString(4, flavWt);
            insertStmt.setString(5, nicWt);
            insertStmt.setString(6, pgWt);
            insertStmt.setString(7, vgWt);
            insertStmt.executeUpdate();
            e.close();
        } catch (SQLException ex) {
            UserInterface.msgBox("ERROR", 5);
        }

    }

    public static void updateFlavor(String name, String fl, String desc) {
        String sqlcmd = "UPDATE flavors SET fl = ?, desc = ? WHERE name = ?";

        try {
            Connection e = connect();
            PreparedStatement pstmt = e.prepareStatement(sqlcmd);
            pstmt.setString(1, fl);
            pstmt.setString(2, desc);
            pstmt.setString(3, name);
            pstmt.executeUpdate();
            e.close();
        } catch (SQLException ex) {
            UserInterface.msgBox("ERROR", 5);
        }

    }

    public static void deleteFlavor(String name) {
        String sqlcmd;
        if(name.equals("*")) {
            sqlcmd = "DELETE from flavors";
        } else {
            sqlcmd = "DELETE from flavors WHERE name = ?";
        }

        try {
            Connection e = connect();
            PreparedStatement pstmt = e.prepareStatement(sqlcmd);
            if(!name.equals("*")) {
                pstmt.setString(1, name);
                pstmt.executeUpdate();
            }
        } catch (SQLException ex) {
            UserInterface.msgBox("ERROR", 5);
        }

    }
}
