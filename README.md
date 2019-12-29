## Description 

Sugar ORM is a database persistence library that provides a simple and concise way to integrate your application models into SQLite. In contrast to ActiveAndroid, which is mature, powerful, and flexible, Sugar ORM

#### Depandency 

```xml
implementation 'com.github.satyan:sugar:1.5'

```

### Sugar Configuration 

```xml

import com.orm.SugarApp;
import com.orm.SugarContext;

public class Sugar extends SugarApp {

    @Override
    public void onCreate() {
        super.onCreate();
        SugarContext.init(getApplicationContext());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        SugarContext.terminate();
    }
}


```


### Database Model

```xml

package com.basic.programming.sugarsqliteormcrudapp.entitys;

import com.orm.SugarRecord;
import com.orm.dsl.Column;
import com.orm.dsl.Unique;

public class Populations extends SugarRecord {

    @Unique
    @Column(name = "population_id")
    private Long populationId;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "country_population")
    private Double countryPopulation;
    @Column(name = "country_ranking")
    private Integer countryRanking;

    public Populations() {
    }

    public Populations(String countryName, Double countryPopulation, Integer countryRanking) {
        this.countryName = countryName;
        this.countryPopulation = countryPopulation;
        this.countryRanking = countryRanking;
    }

    public Long getPopulationId() {
        return populationId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public Double getCountryPopulation() {
        return countryPopulation;
    }

    public void setCountryPopulation(Double countryPopulation) {
        this.countryPopulation = countryPopulation;
    }

    public Integer getCountryRanking() {
        return countryRanking;
    }

    public void setCountryRanking(Integer countryRanking) {
        this.countryRanking = countryRanking;
    }
}

```
### AndroidManifest.xml

```xml

 <application
        android:name=".configs.Sugar"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">

        <activity android:name=".InsertActivity"
            android:parentActivityName=".MainActivity"/>

        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

        <meta-data
            android:name="DATABASE"
            android:value="population.db" />
        <meta-data
            android:name="VERSION"
            android:value="1" />
        <meta-data
            android:name="QUERY_LOG"
            android:value="true" />
    </application>


```

### Insert Records

```xml

 Populations populations = new Populations();

        String name = countryName.getText().toString();
        String cPopulation = countryPopulation.getText().toString();
        String cRank = countryRank.getText().toString();

        populations.setCountryName(name);
        populations.setCountryPopulation(Double.parseDouble(cPopulation));
        populations.setCountryRanking(Integer.parseInt(cRank));

        populations.save();
        setResult(RESULT_OK);

        Toast.makeText(InsertActivity.this, "Record Insert Success",
                Toast.LENGTH_LONG).show();

```
### Display Records

```xml

 List<Populations> list = Populations.listAll(Populations.class);
        populationsArrayList = new ArrayList<>();
        populations = new Populations();

        for (int i = 0; i < list.size(); i++) {
            populations = list.get(i);
            populationsArrayList.add(populations);
        }

        adapters = new PopulationAdapters(MainActivity.this, populationsArrayList);
        listView.setAdapter(adapters);

```

### Edit Records

```xml

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


```

### Delete Records

```xml

 populations = populationsArrayList.get(position);

        populations.delete();
        populationsArrayList.remove(position);

        setResult(RESULT_OK);
        Toast.makeText(MainActivity.this, "Delete Record Success",
                Toast.LENGTH_LONG).show();

        listView.setAdapter(adapters);
        adapters.notifyDataSetChanged();

```

## Work Done


