package com.example.ereaapp.Adapters;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.ahmadrosid.svgloader.SvgLoader;
import com.example.ereaapp.EreaAppConstance;
import com.example.ereaapp.UI.BordersActivity;
import com.example.ereaapp.Module.Country;
import com.example.ereaapp.R;

import java.util.ArrayList;


public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryHolder> {

    private Context context;
    private ArrayList<Country> countries;


    public CountryAdapter(Context context) {
        this.context = context;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(LayoutInflater.from(context).inflate(R.layout.country_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        holder.bind(countries.get(position));
    }

    @Override
    public int getItemCount() {
        if(countries != null) {
            return countries.size();
        }

        return 0;
    }

    //*****************************
    public class CountryHolder extends RecyclerView.ViewHolder{

        private TextView nativeName, engName;
        private ImageView flag;
        private Country country;

        public CountryHolder(final View itemView) {
            super(itemView);
            nativeName = itemView.findViewById(R.id.native_name);
            engName = itemView.findViewById(R.id.eng_name);
            flag = itemView.findViewById(R.id.flag);
            itemView.setOnClickListener(new View.OnClickListener( ) {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, BordersActivity.class);
                    i.putExtra(EreaAppConstance.COUNTRY_EXTRA_BUNDLE_KEY, country);
                    context.startActivity(i);
                }
            });
        }

        public void bind(Country country){
            this.country = country;
            nativeName.setText(country.getNativeName());
            engName.setText(country.getEngName());
            SvgLoader.pluck()
                    .with((Activity) context)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(country.getFlag(), flag);
        }
    }

    public void DeleteAdapter() {
        if (countries != null) {
            countries.clear();
            notifyDataSetChanged( );
        }
    }
}
