package com.example.creativewallpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModels> modelClasslist;
    private RecyclerView recyclerView;
    Adapters adapters;
    CardView car, nature, trending, rain, dogs, searchit;
    ProgressDialog dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark

        recyclerView = findViewById(R.id.recyclerView);
        car = findViewById(R.id.car);
        nature = findViewById(R.id.nature);
        trending = findViewById(R.id.trending);
        rain = findViewById(R.id.rain);
        dogs = findViewById(R.id.dogs);
        searchit = findViewById(R.id.searchit);


        modelClasslist = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapters = new Adapters(getApplicationContext(), modelClasslist);
        recyclerView.setAdapter(adapters);
        findPhotoes();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Fetching wallpapers...");
        dialog.show();


        searchit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Catagories.class);
                startActivity(intent);
            }
        });

        car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "cars";
                getsearchimage(query);
            }
        });

        nature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "nature";
                getsearchimage(query);
            }
        });

        trending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                findPhotoes();
            }
        });

        dogs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "dogs";
                getsearchimage(query);
            }
        });

        rain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query = "rain";
                getsearchimage(query);
            }
        });



    }


    private void getsearchimage(String query) {
        ApiUtilities.getApiInterfaces().getSearchImage(query, 1, 80).enqueue(new Callback<SearchModels>() {
            @Override
            public void onResponse(Call<SearchModels> call, Response<SearchModels> response) {
                modelClasslist.clear();
                if (response.isSuccessful()) {
                    modelClasslist.addAll(response.body().getPhotos());
                    adapters.notifyDataSetChanged();
                    
                } else {
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModels> call, Throwable t) {

            }
        });

    }


    private void findPhotoes() {
        ApiUtilities.getApiInterfaces().getImage(1, 80).enqueue(new Callback<SearchModels>() {
            @Override
            public void onResponse(Call<SearchModels> call, Response<SearchModels> response) {
                if (response.isSuccessful()) {
                    modelClasslist.addAll(response.body().getPhotos());
                    adapters.notifyDataSetChanged();
                    dialog.dismiss();
                } else {
                    Toast.makeText(getApplicationContext(), "Your are Offline🛰", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModels> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Your are Offline🛰", Toast.LENGTH_SHORT).show();
            }
        });

    }
}