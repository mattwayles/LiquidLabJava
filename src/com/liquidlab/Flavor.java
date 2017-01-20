//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.liquidlab;

public class Flavor {
    private String myVen;
    private String myName;
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

    public String getVen() {
        return this.myVen;
    }

    private void setVen(String ven) {
        this.myVen = ven;
    }

    public String getName() {
        return this.myName;
    }

    private void setName(String str) {
        this.myName = str;
    }

    private void setPercentage(double num) {
        this.myPercentage = num;
    }

    public double getFraction() {
        return this.myPercentage / 100.0D;
    }
}
