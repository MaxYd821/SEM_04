package com.example.sem_04;

import com.google.gson.annotations.SerializedName;

public class ColorClass {
    private String name;
    @SerializedName("color_hex")
    private String hex;

    public ColorClass(String name, String hex) {
        this.name = name;
        this.hex = hex;
    }

    public ColorClass() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }
}
