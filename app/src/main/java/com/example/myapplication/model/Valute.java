package com.example.myapplication.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name="Valute", strict = false)
public class Valute {

    @Element(name = "CharCode")
    private String charCode;

    public String getCharCode() {
        return charCode;
    }

    public void setCharCode(String charCode) {
        this.charCode = charCode;
    }

    @Element(name = "Name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Element(name = "Value")
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
