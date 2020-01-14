package com.example.ereaapp.DB;

import android.content.Context;

import com.example.ereaapp.Module.Country;
import com.example.ereaapp.UI.Listenres.BordersDBListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ServerBordersList implements Callback{

        Context context;
        private BordersDBListener listener;
        private ArrayList<Country> c;

    public ServerBordersList(Context context, BordersDBListener listener) {
        this.context = context;
        this.listener = listener;
        this.c = new ArrayList<>();
    }

    public void GetCodeList(JSONArray list) throws JSONException {
        for (int i = 0; i < list.length(); i++) {
            GetCountryByCode(list.getString(i));
        }
    }

    public void GetCountryByCode(String code) {
        OkHttpClient client = new OkHttpClient( );
        Request request = new Request.Builder( )
                .url("https://restcountries.eu/rest/v2/alpha/" + code)
                .build( );
        client.newCall(request).enqueue((Callback) this);
    }

    @Override
    public void onFailure(Call call, IOException e) {

    }

    @Override
    public void onResponse(Call call, Response response) throws IOException {
        try {
            JSONObject object = new JSONObject(response.body().string());
            String nativeName = object.getString("nativeName");
            String engName = object.getString("name");
            String flag = object.getString("flag");
            Country save = new Country(nativeName, engName, flag);
            c.add(save);
            listener.getBordersListSuccess(save);
        }catch (Exception e){ }
    }
}
