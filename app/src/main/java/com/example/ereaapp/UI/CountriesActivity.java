package com.example.ereaapp.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.ereaapp.Adapters.CountryAdapter;
import com.example.ereaapp.DB.CountriesDBHelper;
import com.example.ereaapp.R;

public class CountriesActivity extends AppCompatActivity implements View.OnClickListener {

    private CountryAdapter adapter;
    private CountriesDBHelper helper;
    private Button name, size;
    private int sort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new CountriesDBHelper(this);
        adapter = new CountryAdapter(this);
        initRecycleView();
        initUI();
        adapter.setCountries(helper.getCountries(1));

    }

    public void initUI(){
        sort = 1;
        name = findViewById(R.id.name);
        size = findViewById(R.id.size);
        name.setOnClickListener(this);
        size.setOnClickListener(this);
    }

    public void initRecycleView(){
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.name:
                if(sort == 1){
                    adapter.DeleteAdapter();
                    adapter.setCountries(helper.getCountries(2));
                    sort = 2;
                }else{
                    adapter.DeleteAdapter();
                    adapter.setCountries(helper.getCountries(1));
                    sort = 1;
                }
                break;
            case R.id.size:
                if(sort == 3){
                    adapter.DeleteAdapter();
                    adapter.setCountries(helper.getCountries(4));
                    sort = 4;
                }else{
                    adapter.DeleteAdapter();
                    adapter.setCountries(helper.getCountries(3));
                    sort = 3;
                }
                break;
        }
    }
}
