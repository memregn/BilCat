package com.beyza.bilcat;

import java.util.ArrayList;

public class CatData {
    String name, neighbourhood, age, image;
    VaccinationData vaccinationData;

    public CatData() {

    }

    public CatData(String name, String neighbourhood, String age, String image, VaccinationData vaccinationData) {
        this.name = name;
        this.neighbourhood = neighbourhood;
        this.age = age;
        this.image = image;
        this.vaccinationData = vaccinationData;
    }


    public String getName() {
        return name;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public String getAge() {
        return age;
    }

    public VaccinationData getVaccinationData() {
        return vaccinationData;
    }

    public String getImage() {
        return image;
    }
}
