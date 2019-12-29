package com.basic.programming.sugarsqliteormcrudapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.basic.programming.sugarsqliteormcrudapp.R;
import com.basic.programming.sugarsqliteormcrudapp.entitys.Populations;

import java.util.ArrayList;

public class PopulationAdapters extends BaseAdapter {

    private Context context;
    private ArrayList<Populations> arrayList;

    public PopulationAdapters(Context context, ArrayList<Populations> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.list_display_record, null);
        }

        TextView countryName = convertView.findViewById(R.id.country_name_text_view);
        TextView countryPopulation = convertView.findViewById(R.id.country_population_text_view);
        TextView countryRanking = convertView.findViewById(R.id.country_rank_text_view);

        Populations populations = arrayList.get(position);

        countryName.setText(populations.getCountryName());
        countryPopulation.setText("" + populations.getCountryPopulation());
        countryRanking.setText("" +populations.getCountryRanking());


        return convertView;
    }
}
