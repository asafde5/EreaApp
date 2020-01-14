package com.example.ereaapp.Module;

import java.io.Serializable;
import java.util.ArrayList;

public class Country implements Serializable {

    private String nativeName;
    private String engName;
    private String borderCountries;
    private long area;
    private String flag;

    public Country(String nativeName, String engName, String borderCountries, long area,  String flag) {
        this.nativeName = nativeName;
        this.engName = engName;
        this.borderCountries = borderCountries;
        this.area = area;
        this.flag = flag;
    }

    public Country(String nativeName, String engName, String flag) {
        this.nativeName = nativeName;
        this.engName = engName;
        this.flag = flag;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getEngName() {
        return engName;
    }

    public String getBorderCountries() {
        return borderCountries;
    }

    public long getArea() { return area; }

    public String getFlag() {
        return flag;
    }
}
