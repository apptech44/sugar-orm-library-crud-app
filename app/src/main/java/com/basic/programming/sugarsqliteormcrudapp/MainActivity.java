package com.basic.programming.sugarsqliteormcrudapp;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basic.programming.sugarsqliteormcrudapp.adapters.PopulationAdapters;
import com.basic.programming.sugarsqliteormcrudapp.entitys.Populations;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemLongClickListener {

    private ListView listView;
    private Populations populations;
    private PopulationAdapters adapters;
    private ArrayList<Populations> populationsArrayList;
    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new Dialog(this, R.style.appDialog);

        listView = findViewById(R.id.population_list_view);

        listView.setOnItemClickListener(this);
        listView.setOnItemLongClickListener(this);

        displayRecords();
    }

    private void displayRecords() {

        List<Populations> list = Populations.listAll(Populations.class);
        populationsArrayList = new ArrayList<>();
        populations = new Populations();

        for (int i = 0; i < list.size(); i++) {
            populations = list.get(i);
            populationsArrayList.add(populations);
        }

        adapters = new PopulationAdapters(MainActivity.this, populationsArrayList);
        listView.setAdapter(adapters);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.setting:
                Toast.makeText(MainActivity.this, "Setting Menu",
                        Toast.LENGTH_LONG).show();
                break;

            case R.id.insert:
                startActivity(new Intent(MainActivity.this, InsertActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

        populations = populationsArrayList.get(position);
        dialog.setTitle("Edit Records");
        dialog.setContentView(R.layout.edit_records_layout);
        dialog.setCanceledOnTouchOutside(true);

        final EditText countryNames = dialog.findViewById(R.id.edit_country_name_edit_text);
        final EditText countryPopulation = dialog.findViewById(R.id.edit_country_population_edit_text);
        final EditText countryRank = dialog.findViewById(R.id.edit_country_rank_edit_text);
        Button editBtn = dialog.findViewById(R.id.editButton);

        countryNames.setText(populations.getCountryName());
        countryPopulation.setText("" + populations.getCountryPopulation());
        countryRank.setText("" + populations.getCountryRanking());

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String countryName = countryNames.getText().toString();
                String cPopulation = countryPopulation.getText().toString();
                String cRanking = countryRank.getText().toString();

                populations.setCountryName(countryName);
                populations.setCountryPopulation(Double.parseDouble(cPopulation));
                populations.setCountryRanking(Integer.parseInt(cRanking));

                populations.save();
                populationsArrayList.remove(position);
                populationsArrayList.add(populations);

                setResult(RESULT_OK);

                Toast.makeText(MainActivity.this, "Edit Record Success",
                        Toast.LENGTH_LONG).show();
                dialog.dismiss();
                listView.setAdapter(adapters);
                adapters.notifyDataSetChanged();
            }
        });
        dialog.show();

    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        //delete records

        populations = populationsArrayList.get(position);

        populations.delete();
        populationsArrayList.remove(position);

        setResult(RESULT_OK);
        Toast.makeText(MainActivity.this, "Delete Record Success",
                Toast.LENGTH_LONG).show();

        listView.setAdapter(adapters);
        adapters.notifyDataSetChanged();

        return true;
    }
}
