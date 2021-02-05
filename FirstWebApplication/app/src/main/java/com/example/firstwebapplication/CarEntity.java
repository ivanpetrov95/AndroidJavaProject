package com.example.firstwebapplication;

import java.io.Serializable;

public class CarEntity implements Serializable {
    private int ID;
    private String carClass;
    private String carCategory;
    private String carCharacteristics;
    private String isSmoker;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getCarCategory() {
        return carCategory;
    }

    public void setCarCategory(String carCategory) {
        this.carCategory = carCategory;
    }

    public String getCarCharacteristics() {
        return carCharacteristics;
    }

    public void setCarCharacteristics(String carCharacteristics) {
        this.carCharacteristics = carCharacteristics;
    }

    public String getIsSmoker() {
        return isSmoker;
    }

    public void setIsSmoker(String isSmoker) {
        this.isSmoker = isSmoker;
    }
}
