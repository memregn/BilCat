package com.beyza.bilcat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class VaccinationList extends AppCompatActivity {

    TextView txtVaccination, txtVac1, txtVac2, txtVac3, txtVac4, txtVac5, txtVac6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccination_list);

        txtVaccination = findViewById(R.id.txtVaccination);
        txtVac1 = findViewById(R.id.txtVac1);
        txtVac2 = findViewById(R.id.txtVac2);
        txtVac3 = findViewById(R.id.txtVac3);
        txtVac4 = findViewById(R.id.txtVac4);
        txtVac5 = findViewById(R.id.txtVac5);
        txtVac6 = findViewById(R.id.txtVac6);

        VaccinationData vaccinationData = CatList.list.get(CatList.currentCat).getVaccinationData();

        txtVaccination.setText(CatList.list.get(CatList.currentCat).getName().toUpperCase() + "'S VACCINATION LIST");
        if (vaccinationData.getVac1() != null)
            txtVac1.setText("- " + vaccinationData.getVac1());
        if (vaccinationData.getVac2() != null)
            txtVac2.setText("- " + vaccinationData.getVac2());
        if (vaccinationData.getVac3() != null)
            txtVac3.setText("- " + vaccinationData.getVac3());
        if (vaccinationData.getVac4() != null)
            txtVac4.setText("- " + vaccinationData.getVac4());
        if (vaccinationData.getVac5() != null)
            txtVac5.setText("- " + vaccinationData.getVac5());
        if (vaccinationData.getVac6() != null)
            txtVac6.setText("- " + vaccinationData.getVac6());


    }
}