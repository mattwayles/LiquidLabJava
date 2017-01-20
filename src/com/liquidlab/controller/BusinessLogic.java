//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab.controller;

import com.liquidlab.Flavor;
import com.liquidlab.model.DatabaseInteraction;
import com.liquidlab.view.UserInterface;
import java.text.DecimalFormat;

public class BusinessLogic {
    private static double ourFlavorWeight;
    private static double ourNicWeight;
    private static double ourPgWeight;
    private static double ourVgWeight;
    private static double ourMlToMake;
    private static double ourFlavorMl;
    private static double ourFlavorGrams;
    private static double ourFlavorPercent;
    private static double ourFlavorMlTotal;
    private static double ourNicMl;
    private static double ourNicAmt;
    private static double ourNicGrams;
    private static double ourNicTarget;
    private static double ourNicBasePg;
    private static double ourNicBaseVg;
    private static double ourNicStrength;
    private static double ourNicTotalPercent;
    private static double ourPgMl;
    private static double ourPgGrams;
    private static double ourPgTarget;
    private static double ourPgPercent;
    private static double ourPgTotalPercent;
    private static double ourVgMl;
    private static double ourVgGrams;
    private static double ourVgTarget;
    private static double ourVgPercent;
    private static double ourVgTotalPercent;

    private static double getFlavorWeight() {
        return ourFlavorWeight;
    }

    private static void setFlavorWeight(double wt) {
        ourFlavorWeight = wt;
    }

    private static double getNicWeight() {
        return ourNicWeight;
    }

    private static void setNicWeight(double wt) {
        ourNicWeight = wt;
    }

    private static double getPgWeight() {
        return ourPgWeight;
    }

    private static void setPgWeight(double wt) {
        ourPgWeight = wt;
    }

    private static double getVgWeight() {
        return ourVgWeight;
    }

    private static void setVgWeight(double wt) {
        ourVgWeight = wt;
    }


    private static double getMlToMake() {
        return ourMlToMake;
    }

    private static void setMlToMake(double ml) {
        ourMlToMake = ml;
    }

    private static double getFlavorMl() {
        return ourFlavorMl;
    }

    private static void setFlavorMl(double ml) {
        ourFlavorMl = ml;
    }

    private static double getFlavorPercent() {
        return ourFlavorPercent;
    }

    private static void setFlavorPercent(double pc) {
        ourFlavorPercent = pc;
    }

    private static double getFlavorMlTotal() {
        return ourFlavorMlTotal;
    }

    private static void setFlavorMlTotal(double ml) {
        ourFlavorMlTotal = ml;
    }

    private static double getFlavorGrams() {
        return ourFlavorGrams;
    }

    private static void setFlavorGrams(double gr) {
        ourFlavorGrams = gr;
    }

    private static double getNicAmt() {
        return ourNicAmt;
    }

    private static void setNicAmt(double nic) {
        ourNicAmt = nic;
    }

    private static double getNicTarget() {
        return ourNicTarget;
    }

    private static void setNicTarget(double nic) {
        ourNicTarget = nic;
    }

    private static double getNicTotalPercent() {
        return ourNicTotalPercent;
    }

    private static void setNicTotalPercent(double nic) {
        ourNicTotalPercent = nic;
    }

    private static double getPgTotalPercent() {
        return ourPgTotalPercent;
    }

    private static void setPgTotalPercent(double pc) {
        ourPgTotalPercent = pc;
    }

    private static double getVgTotalPercent() {
        return ourVgTotalPercent;
    }

    private static void setVgTotalPercent(double pc) {
        ourVgTotalPercent = pc;
    }

    private static double getNicMl() {
        return ourNicMl;
    }

    private static void setNicMl(double ml) {
        ourNicMl = ml;
    }

    private static double getNicStrength() {
        return ourNicStrength;
    }

    private static void setNicStrength(double nic) {
        ourNicStrength = nic;
    }

    private static double getNicGrams() {
        return ourNicGrams;
    }

    private static void setNicGrams(double gr) {
        ourNicGrams = gr;
    }

    private static double getNicBasePg() {
        return ourNicBasePg;
    }

    private static void setNicBasePg(double nic) {
        ourNicBasePg = nic;
    }

    private static double getNicBaseVg() {
        return ourNicBaseVg;
    }

    private static void setNicBaseVg(double nic) {
        ourNicBaseVg = nic;
    }

    private static double getPgTarget() {
        return ourPgTarget;
    }

    private static void setPgTarget(double pg) {
        ourPgTarget = pg;
    }

    private static double getPgPercent() {
        return ourPgPercent;
    }

    private static void setPgPercent(double pgpc) {
        ourPgPercent = pgpc;
    }

    private static double getPgMl() {
        return ourPgMl;
    }

    private static void setPgMl(double ml) {
        ourPgMl = ml;
    }

    private static double getPgGrams() {
        return ourPgGrams;
    }

    private static void setPgGrams(double gr) {
        ourPgGrams = gr;
    }

    private static double getVgTarget() {
        return ourVgTarget;
    }

    private static void setVgTarget(double vg) {
        ourVgTarget = vg;
    }

    private static double getVgPercent() {
        return ourVgPercent;
    }

    private static void setVgPercent(double pc) {
        ourVgPercent = pc;
    }

    private static double getVgMl() {
        return ourVgMl;
    }

    private static void setVgMl(double ml) {
        ourVgMl = ml;
    }

    private static double getVgGrams() {
        return ourVgGrams;
    }

    private static void setVgGrams(double gr) {
        ourVgGrams = gr;
    }

    public static void parseData(String fl) {
        DatabaseInteraction.selectFlavor(fl);
        UserInterface.getNotes().setText(DatabaseInteraction.getResults().get(2));
        String flavors = DatabaseInteraction.getResults().get(1);
        flavors = flavors.replaceAll(",", "%");
        String[] results = flavors.split("%");

        int j;
        for(j = 0; j < results.length; ++j) {
            String i = results[j].trim();
            results[j] = i;
        }

        j = 0;

        for(int i = 0; i < results.length; i += 3) {
            if(!results[i + 1].isEmpty()) {
                UserInterface.getFlavors()[j].getVenField().setText(results[i + 1]);
            }

            UserInterface.getFlavors()[j].getFlavField().setText(results[i + 2]);
            UserInterface.getFlavors()[j].getFlavPerField().setText(results[i]);
            ++j;
        }

    }

    public static String combineFlavorData() {
        String flListToAdd = "";

        for(int i = 0; i < UserInterface.getFlavors().length; ++i) {
            if(UserInterface.getFlavors()[i] != null) {
                String percent = UserInterface.getFlavors()[i].getFlavPerField().getText();
                String flavor;
                if(!UserInterface.getFlavors()[i].getVenField().getText().isEmpty()) {
                    flavor = UserInterface.getFlavors()[i].getVenField().getText() + "," + UserInterface.getFlavors()[i].getFlavField().getText();
                } else {
                    flavor = "," + UserInterface.getFlavors()[i].getFlavField().getText();
                }

                if(flavor.isEmpty() && !percent.isEmpty()) {
                    UserInterface.msgBox("ERROR", 8);
                    flListToAdd = "error";
                    break;
                }

                if(!flavor.isEmpty() && !flavor.equals(",") && percent.isEmpty()) {
                    UserInterface.msgBox("ERROR", 7);
                    flListToAdd = "error";
                    break;
                }

                if(!flavor.isEmpty() && !percent.isEmpty()) {
                    if(i > 0) {
                        flListToAdd = flListToAdd + ", ";
                    }

                    flListToAdd = flListToAdd + percent + "% " + flavor;
                }
            }
        }

        return flListToAdd;
    }

    private static void calcBaseGrams() {
        setNicBasePg(getNicBasePg() / 100.0);
        setNicBaseVg(getNicBaseVg() / 100.0);
        setPgPercent(getPgPercent() / 100.0);
        setVgPercent(getVgPercent() / 100.0);
        setNicTarget(getMlToMake() * getNicAmt());
        setNicMl(getMlToMake() / getNicStrength() * getNicAmt());
        setNicGrams(getNicMl() * getNicWeight());
        setPgTarget(getMlToMake() * getPgPercent() - getNicTarget() * getNicBasePg());
        setPgMl(getPgTarget() - (getNicMl() - getNicTarget()) * getNicBasePg() - getFlavorMlTotal());
        setPgGrams(getPgMl() * getPgWeight());
        setVgTarget(getMlToMake() * getVgPercent() - getNicTarget() * getNicBaseVg());
        setVgMl(getVgTarget() - (getNicMl() - getNicTarget()) * getNicBaseVg());
        setVgGrams(getVgMl() * getVgWeight());
    }

    private static void calcFlavorGrams(Flavor fl, DecimalFormat df, int i) {
        setFlavorPercent(fl.getFraction());
        setFlavorMl(getMlToMake() * getFlavorPercent());
        setFlavorMlTotal(getFlavorMlTotal() + getFlavorMl());
        setFlavorGrams(getFlavorMl() * getFlavorWeight());
        if(!fl.getVen().isEmpty()) {
            UserInterface.getFlavors()[i].setFlav("[" + fl.getVen() + "] " + fl.getName());
        } else {
            UserInterface.getFlavors()[i].setFlav(fl.getName());
        }

        UserInterface.getFlavors()[i].setFlavMl(Double.parseDouble(df.format(getFlavorMl())));
        UserInterface.getFlavors()[i].setFlavG(Double.parseDouble(df.format(getFlavorGrams())));
        UserInterface.getFlavors()[i].setFlavPercent(Double.parseDouble(df.format(getFlavorPercent() * 100.0)));
    }

    private static void calcPercents() {
        setNicTotalPercent(getNicMl() / getMlToMake() * 100.0);
        setPgTotalPercent(getPgMl() / getMlToMake() * 100.0);
        setVgTotalPercent(getVgMl() / getMlToMake() * 100.0);
    }

    public static void calculate() {
        DecimalFormat df = new DecimalFormat("#.##");
        if(!UserInterface.getMlToMake().getText().isEmpty() && !UserInterface.getPg().getText().isEmpty()) {
            if(UserInterface.getNic().getText().isEmpty()) {
                UserInterface.getNic().setText("0");
            }

            if(getUserValues()) {
                UserInterface.msgBox("ERROR", 6);
            } else {
                setMlToMake(Double.parseDouble(UserInterface.getMlToMake().getText()));
                setNicAmt(Double.parseDouble(UserInterface.getNic().getText()));
                setPgPercent(Double.parseDouble(UserInterface.getPg().getText()));
                setVgPercent(Double.parseDouble(UserInterface.getVg().getText()));

                for(int i = 0; i < UserInterface.getRowCount(); ++i) {
                    if(UserInterface.getFlavors()[i] != null) {
                        String ven = UserInterface.getFlavors()[i].getVenField().getText();
                        String flavor = UserInterface.getFlavors()[i].getFlavField().getText();
                        String percent = UserInterface.getFlavors()[i].getFlavPerField().getText();
                        if(!flavor.isEmpty() && !percent.isEmpty()) {
                            if(!ven.isEmpty()) {
                                calcFlavorGrams(new Flavor(ven, flavor, Double.parseDouble(percent)), df, i);
                            } else {
                                calcFlavorGrams(new Flavor(flavor, Double.parseDouble(percent)), df, i);
                            }
                        } else if(!flavor.isEmpty() && percent.isEmpty()) {
                            UserInterface.msgBox("ERROR", 7);
                        } else if(flavor.isEmpty() && !percent.isEmpty()) {
                            if(!ven.isEmpty()) {
                                calcFlavorGrams(new Flavor(ven, "No-Name Flavor", Double.parseDouble(percent)), df, i);
                            } else {
                                calcFlavorGrams(new Flavor("No-Name Flavor", Double.parseDouble(percent)), df, i);
                            }
                        }
                    }
                }

                calcBaseGrams();
                calcPercents();
                if(getPgMl() < 0.0) {
                    UserInterface.msgBox("ERROR", 2);
                } else if(getVgMl() < 0.0) {
                    UserInterface.msgBox("ERROR", 3);
                } else if(getNicMl() < 0.0) {
                    UserInterface.msgBox("ERROR", 4);
                } else {
                    UserInterface.setPgMl(Double.parseDouble(df.format(getPgMl())));
                    UserInterface.setVgMl(Double.parseDouble(df.format(getVgMl())));
                    UserInterface.setNicMl(Double.parseDouble(df.format(getNicMl())));
                    UserInterface.setPgG(Double.parseDouble(df.format(getPgGrams())));
                    UserInterface.setVgG(Double.parseDouble(df.format(getVgGrams())));
                    UserInterface.setNicG(Double.parseDouble(df.format(getNicGrams())));
                    UserInterface.setNicPer(Double.parseDouble(df.format(getNicTotalPercent())));
                    UserInterface.setPgPer(Double.parseDouble(df.format(getPgTotalPercent())));
                    UserInterface.setVgPer(Double.parseDouble(df.format(getVgTotalPercent())));
                    UserInterface.update();
                    reset();
                }

            }
        } else {
            if(UserInterface.getMlToMake().getText().isEmpty()) {
                UserInterface.msgBox("ERROR", 0);
            } else {
                UserInterface.msgBox("ERROR", 1);
            }

        }
    }

    public static Boolean getUserValues() {
        Boolean missingValue = false;
        DatabaseInteraction.selectValues();
        if(!DatabaseInteraction.getResults().isEmpty()) {
            setNicStrength(Double.parseDouble(DatabaseInteraction.getResults().get(0)));
            setNicBasePg(Double.parseDouble(DatabaseInteraction.getResults().get(1)));
            setNicBaseVg(Double.parseDouble(DatabaseInteraction.getResults().get(2)));
            setFlavorWeight(Double.parseDouble(DatabaseInteraction.getResults().get(3)));
            setNicWeight(Double.parseDouble(DatabaseInteraction.getResults().get(4)));
            setPgWeight(Double.parseDouble(DatabaseInteraction.getResults().get(5)));
            setVgWeight(Double.parseDouble(DatabaseInteraction.getResults().get(6)));
        } else {
            missingValue = true;
        }

        return missingValue;
    }

    private static void reset() {
        for(int i = 0; i < UserInterface.getFlavors().length; ++i) {
            if(UserInterface.getFlavors()[i] != null) {
                UserInterface.getFlavors()[i].setFlavMl(0.0);
                UserInterface.getFlavors()[i].setFlavG(0.0);
                UserInterface.getFlavors()[i].setFlavPercent(0.0);
            }
        }

        setMlToMake(0.0);
        setFlavorMlTotal(0.0);
        setFlavorGrams(0.0);
        setFlavorMl(0.0);
        setFlavorPercent(0.0);
        setNicTarget(0.0);
        setNicAmt(0.0);
        setNicMl(0.0);
        setNicGrams(0.0);
        setNicTotalPercent(0.0);
        setPgTarget(0.0);
        setPgMl(0.0);
        setPgGrams(0.0);
        setPgPercent(0.0);
        setPgTotalPercent(0.0);
        setVgTarget(0.0);
        setVgMl(0.0);
        setVgGrams(0.0);
        setVgPercent(0.0);
        setVgTotalPercent(0.0);
        getUserValues();
    }
}
