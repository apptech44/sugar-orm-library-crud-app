package com.basic.programming.sugarsqliteormcrudapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basic.programming.sugarsqliteormcrudapp.entitys.Populations;

public class InsertActivity extends AppCompatActivity {

    private EditText countryName, countryPopulation, countryRank;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        countryName = findViewById(R.id.country_name_edit_text);
        countryPopulation = findViewById(R.id.country_population_edit_text);
        countryRank = findViewById(R.id.country_rank_edit_text);

    }

    private void insertRecords() {

        Populations populations = new Populations();

        String name = countryName.getText().toString();
        String cPapulation = countryPopulation.getText().toString();
        String cRank = countryRank.getText().toString();

        populations.setCountryName(name);
        populations.setCountryPopulation(Double.parseDouble(cPapulation));
        populations.setCountryRanking(Integer.parseInt(cRank));

        populations.save();
        setResult(RESULT_OK);

        Toast.makeText(InsertActivity.this, "Record Insert Success",
                Toast.LENGTH_LONG).show();

        countryName.setText("");
        countryPopulation.setText("");
        countryRank.setText("");

    }

    public void addRecords(View view) {
        insertRecords();
    }
}
