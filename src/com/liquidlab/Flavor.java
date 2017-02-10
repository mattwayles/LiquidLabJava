//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Flavor {
    private String myVen;
    private String myName;
    private String myAmt;
    private static SimpleDoubleProperty low;
    private double myPercentage;

    public Flavor(String name, double percentage) {
        this.setVen("");
        this.setName(name);
        this.setPercentage(percentage);
    }

    public Flavor(String ven, String name, double percentage) {
        this.setVen(ven);
        this.setName(name);
        this.setPercentage(percentage);
    }

    public Flavor(String ven, String name, String amt) {
        this.setVen(ven);
        this.setName(name);
        this.setAmt(amt);
    }

    public double getLow() { return low.get(); }

    public void setLow(double ml) { low.set(ml); }

    public DoubleProperty getLowProperty() { return low; }

    public String getVen() {
        return this.myVen;
    }

    public void setVen(String ven) {
        this.myVen = ven;
    }

    public String getName() {
        return this.myName;
    }

    public void setName(String str) {
        this.myName = str;
    }

    private void setPercentage(double num) {
        this.myPercentage = num;
    }

    public String getAmt() { return this.myAmt; }

    public void setAmt(String num) { this.myAmt = num; }

    public double getFraction() {
        return this.myPercentage / 100.0D;
    }
}
