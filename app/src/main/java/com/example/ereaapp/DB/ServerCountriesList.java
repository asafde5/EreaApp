package com.example.ereaapp.DB;

import android.content.Context;
import android.util.Log;

import com.example.ereaapp.Module.Country;
import com.example.ereaapp.UI.Listenres.CountriesDBListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServerCountriesList implements Callback {
    private CountriesDBHelper helper;
    Context context;
    private CountriesDBListener listener;


    public ServerCountriesList(CountriesDBHelper helper, Context context, CountriesDBListener listener) {
        this.helper = helper;
        this.context = context;
        this.listener = listener;

    }

    public void GetDataFromWebSite() {
        helper = new CountriesDBHelper(context);
        OkHttpClient client = new OkHttpClient( );
        Request request = new Request.Builder( )
                .url("https://restcountries.eu/rest/v2/all")
                .build( );
        client.newCall(request).enqueue((Callback) this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        long area;
        try {
            JSONArray array = new JSONArray(response.body( ).string( ));
            for (int i = 0; i < array.length( ); i++) {
                JSONObject country = array.getJSONObject(i);
                String nativeName = country.getString("nativeName");
                String engName = country.getString("name");
                JSONArray borderCountries = country.getJSONArray("borders");
                Log.d("asasa", "" + borderCountries);
                try {
                    area = country.getLong("area");
                    Log.d("asasa", "" + area);
                }catch (JSONException e){
                    area = 0;
                }
                String flag = country.getString("flag");
                String saveBorders = borderCountries.toString();
                helper.InsertCountry(new Country(nativeName, engName, saveBorders, area, flag));
            }
            listener.getCountriesListSuccess();
        } catch (JSONException e) {
            e.printStackTrace( );
        }
    }
}
