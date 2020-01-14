package com.example.ereaapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahmadrosid.svgloader.SvgLoader;
import com.example.ereaapp.Module.Country;
import com.example.ereaapp.R;

import java.util.ArrayList;


public class BorderAdapter extends RecyclerView.Adapter<BorderAdapter.CountryHolder> {

    private Context context;
    private ArrayList<Country> borders;


    public BorderAdapter(Context context) {
        this.context = context;
    }

    public void setBorders(ArrayList<Country> borders) {
        this.borders = borders;
    }


    @Override
    public CountryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryHolder(LayoutInflater.from(context).inflate(R.layout.border_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryHolder holder, int position) {
        holder.bind(borders.get(position));
    }

    @Override
    public int getItemCount() {
        if (borders != null) {
            return borders.size();
        }

        return 0;
    }

    //*****************************
    public class CountryHolder extends RecyclerView.ViewHolder {

        private TextView nativeName, engName;
        private ImageView flag;

        public CountryHolder(final View itemView) {
            super(itemView);
            nativeName = itemView.findViewById(R.id.native_name_border);
            engName = itemView.findViewById(R.id.eng_name_border);
            flag = itemView.findViewById(R.id.flag_border);
        }

        public void bind(Country country) {
            nativeName.setText(country.getNativeName());
            engName.setText(country.getEngName());
            SvgLoader.pluck()
                    .with((Activity) context)
                    .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                    .load(country.getFlag(), flag);
        }
    }
}