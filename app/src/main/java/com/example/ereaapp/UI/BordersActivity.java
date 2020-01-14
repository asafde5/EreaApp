package com.example.ereaapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.ereaapp.Adapters.BorderAdapter;
import com.example.ereaapp.EreaAppConstance;
import com.example.ereaapp.Module.Country;
import com.example.ereaapp.R;
import com.example.ereaapp.DB.ServerBordersList;
import com.example.ereaapp.UI.Listenres.BordersDBListener;

import org.json.JSONArray;

import java.util.ArrayList;

public class BordersActivity extends AppCompatActivity implements BordersDBListener {

    private Country country;
    private JSONArray borders;
    private TextView border;
    private ServerBordersList serverBordersList;
    private BorderAdapter adapter;
    ArrayList<Country> currentBordersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borders);
        adapter = new BorderAdapter(this);
        country = (Country) getIntent().getSerializableExtra(EreaAppConstance.COUNTRY_EXTRA_BUNDLE_KEY);
        serverBordersList = new ServerBordersList(this,this);
        initUI();
        initRecycleView();
        getBordersList();

    }

    public void initUI(){
        border = findViewById(R.id.borderText);
        currentBordersList = new ArrayList<>();
    }

    public void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.recycleBorders);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    public void getBordersList(){
        try {
            borders = new JSONArray(country.getBorderCountries());
            if(borders.isNull(0)){
                border.setText(getResources().getString(R.string.no_border_states));
            }else{
                border.setText(getResources().getString(R.string.borders_countries));
                serverBordersList.GetCodeList(borders);
            }
        }catch (Exception e){ }
    }

    @Override
    public void getBordersListSuccess(Country country) {
        currentBordersList.add(country);
        if(currentBordersList.size() == borders.length()){
            this.runOnUiThread(new Runnable() {
                public void run() {
                    adapter.setBorders(currentBordersList);
                    adapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void getBordersListFailure() {
        //Toast
    }
}
