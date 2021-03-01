package com.example.imagenes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RequestQueue requestQueue;
    List<String> names = new ArrayList<>();
    List<String> urls = new ArrayList<>();
    ListaAdapter adapter;
    ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = (ListView) findViewById(R.id.listaNotas);
        requestQueue = Volley.newRequestQueue(this);
        peticion();
    }

    public void peticion() {
        String url = "https://simplifiedcoding.net/demos/view-flipper/heroes.php";
        JsonRequest jsonRequest = new JsonObjectRequest(
                url, null, response -> {
            try {
                JSONArray item = item = response.getJSONArray("heroes");
                ;
                for (int i = 0; i < item.length(); i++) {
                    JSONObject jsonObject;
                    String job = "";
                    jsonObject = item.getJSONObject(i);
                    names.add(jsonObject.getString("name"));
                    urls.add(jsonObject.getString("imageurl"));
                    Log.d("VOLL", job);
                }
                String[] nom = new String[names.size()];
                String[] img = new String[names.size()];
                for (int i = 0; i < names.size(); i++) {
                    nom[i] = names.get(i);
                    img[i] = urls.get(i);
                }
                adapter = new ListaAdapter(getApplicationContext(), nom, img);
                lista.setAdapter(adapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        },
                error -> {
                    Log.d("VOLL", "Error \n" + error.toString());
                });
        requestQueue.add(jsonRequest);
    }
}