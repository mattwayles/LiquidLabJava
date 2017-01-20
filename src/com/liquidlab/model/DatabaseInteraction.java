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

    public DatabaseInteraction() {
    }

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
        } catch (SQLException var2) {
            System.out.println(var2.getMessage());
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
            Throwable var3 = null;

            try {
                PreparedStatement pstmt = e.prepareStatement(sqlcmd);
                Throwable var5 = null;

                try {
                    if(!flavor.equals("*")) {
                        pstmt.setString(1, flavor);
                    }

                    ResultSet rs = pstmt.executeQuery();

                    while(rs.next()) {
                        getResults().add(rs.getString("Name"));
                        getResults().add(rs.getString("fl"));
                        getResults().add(rs.getString("desc"));
                    }
                } catch (Throwable var30) {
                    var5 = var30;
                    throw var30;
                } finally {
                    if(pstmt != null) {
                        if(var5 != null) {
                            try {
                                pstmt.close();
                            } catch (Throwable var29) {
                                var5.addSuppressed(var29);
                            }
                        } else {
                            pstmt.close();
                        }
                    }

                }
            } catch (Throwable var32) {
                var3 = var32;
                throw var32;
            } finally {
                if(e != null) {
                    if(var3 != null) {
                        try {
                            e.close();
                        } catch (Throwable var28) {
                            var3.addSuppressed(var28);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var34) {
            System.out.println(var34.getMessage());
        }

    }

    public static void queryForValues() {
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
                    if(!rs.isClosed()) {
                        getResults().add(rs.getString("nicStrength"));
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
        Throwable var10;
        PreparedStatement insertStmt;
        Throwable var12;
        try {
            e = connect();
            var10 = null;

            try {
                insertStmt = e.prepareStatement(deleteCmd);
                var12 = null;

                try {
                    insertStmt.executeUpdate();
                } catch (Throwable var93) {
                    var12 = var93;
                    throw var93;
                } finally {
                    if(insertStmt != null) {
                        if(var12 != null) {
                            try {
                                insertStmt.close();
                            } catch (Throwable var92) {
                                var12.addSuppressed(var92);
                            }
                        } else {
                            insertStmt.close();
                        }
                    }

                }
            } catch (Throwable var99) {
                var10 = var99;
                throw var99;
            } finally {
                if(e != null) {
                    if(var10 != null) {
                        try {
                            e.close();
                        } catch (Throwable var91) {
                            var10.addSuppressed(var91);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var101) {
            UserInterface.msgBox("ERROR", 5);
        }

        try {
            e = connect();
            var10 = null;

            try {
                insertStmt = e.prepareStatement(insertCmd);
                var12 = null;

                try {
                    insertStmt.setString(1, nicStrength);
                    insertStmt.setString(2, nicBasePg);
                    insertStmt.setString(3, nicBaseVg);
                    insertStmt.setString(4, flavWt);
                    insertStmt.setString(5, nicWt);
                    insertStmt.setString(6, pgWt);
                    insertStmt.setString(7, vgWt);
                    insertStmt.executeUpdate();
                } catch (Throwable var90) {
                    var12 = var90;
                    throw var90;
                } finally {
                    if(insertStmt != null) {
                        if(var12 != null) {
                            try {
                                insertStmt.close();
                            } catch (Throwable var89) {
                                var12.addSuppressed(var89);
                            }
                        } else {
                            insertStmt.close();
                        }
                    }

                }
            } catch (Throwable var95) {
                var10 = var95;
                throw var95;
            } finally {
                if(e != null) {
                    if(var10 != null) {
                        try {
                            e.close();
                        } catch (Throwable var88) {
                            var10.addSuppressed(var88);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var97) {
            UserInterface.msgBox("ERROR", 5);
        }

    }

    public static void updateFlavor(String name, String fl, String desc) {
        String sqlcmd = "UPDATE flavors SET fl = ?, desc = ? WHERE name = ?";

        try {
            Connection e = connect();
            Throwable var5 = null;

            try {
                PreparedStatement pstmt = e.prepareStatement(sqlcmd);
                Throwable var7 = null;

                try {
                    pstmt.setString(1, fl);
                    pstmt.setString(2, desc);
                    pstmt.setString(3, name);
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

    public static void deleteFlavor(String name) {
        String sqlcmd;
        if(name.equals("*")) {
            sqlcmd = "DELETE from flavors";
        } else {
            sqlcmd = "DELETE from flavors WHERE name = ?";
        }

        try {
            Connection e = connect();
            Throwable var3 = null;

            try {
                PreparedStatement pstmt = e.prepareStatement(sqlcmd);
                Throwable var5 = null;

                try {
                    if(!name.equals("*")) {
                        pstmt.setString(1, name);
                    }

                    pstmt.executeUpdate();
                } catch (Throwable var30) {
                    var5 = var30;
                    throw var30;
                } finally {
                    if(pstmt != null) {
                        if(var5 != null) {
                            try {
                                pstmt.close();
                            } catch (Throwable var29) {
                                var5.addSuppressed(var29);
                            }
                        } else {
                            pstmt.close();
                        }
                    }

                }
            } catch (Throwable var32) {
                var3 = var32;
                throw var32;
            } finally {
                if(e != null) {
                    if(var3 != null) {
                        try {
                            e.close();
                        } catch (Throwable var28) {
                            var3.addSuppressed(var28);
                        }
                    } else {
                        e.close();
                    }
                }

            }
        } catch (SQLException var34) {
            System.out.println(var34.getMessage());
        }

    }
}
