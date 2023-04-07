package com.example.creativewallpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Catagories extends AppCompatActivity {

    CardView backs, cool, love, wildlife, neon, gradient, abstracts, fourk;
    RecyclerView wallpaperView;
    private ArrayList<ImageModels> classList;
    Adapters adaptersFor;
    EditText edSearch;
    ImageButton btnSearch;
    LottieAnimationView anison;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catagories);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);//  set status text dark

        backs=findViewById(R.id.backs);
       wallpaperView=findViewById(R.id.wallpaperView);
       edSearch=findViewById(R.id.edSearch);
       btnSearch=findViewById(R.id.btnSearch);
       cool=findViewById(R.id.cool);
       love=findViewById(R.id.love);
       wildlife=findViewById(R.id.wildlife);
       neon=findViewById(R.id.neon);
       gradient=findViewById(R.id.gradient);
       abstracts=findViewById(R.id.abstracts);
       fourk=findViewById(R.id.fourk);
       anison=findViewById(R.id.anison);


        classList= new ArrayList<>();
        wallpaperView.setLayoutManager(new GridLayoutManager(this, 2));
        wallpaperView.setHasFixedSize(true);
        adaptersFor=new Adapters(getApplicationContext(), classList);
        wallpaperView.setAdapter(adaptersFor);

        backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent= new Intent(Catagories.this, MainActivity.class);
//                startActivity(intent);
                onBackPressed();
            }
        });


        cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="cool";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });

        fourk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="4K";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });

        love.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="love";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });

        wildlife.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="wildlife";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });

        neon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="neon";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });

        gradient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="gradient";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });

        abstracts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query="abstract";
                getsearchimage(query);
                anison.setVisibility(View.INVISIBLE);
            }
        });








        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query=edSearch.getText().toString().trim().toLowerCase();
                if (query.isEmpty()){
                    Toast.makeText(getApplicationContext(), "enter something...", Toast.LENGTH_SHORT).show();
                }else {
                    getsearchimage(query);
                    anison.setVisibility(View.INVISIBLE);
                }
            }
        });

    }


    private void getsearchimage(String query) {
        ApiUtilities.getApiInterfaces().getSearchImage(query,1, 80).enqueue(new Callback<SearchModels>() {
            @Override
            public void onResponse(Call<SearchModels> call, Response<SearchModels> response) {
                classList.clear();
                if (response.isSuccessful()) {
                    classList.addAll(response.body().getPhotos());
                    adaptersFor.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModels> call, Throwable t) {

            }
        });
    }


}