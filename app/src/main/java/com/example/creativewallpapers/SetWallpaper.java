package com.example.creativewallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.bumptech.glide.Glide;

import java.io.IOException;

public class SetWallpaper extends AppCompatActivity {
    Intent intent;
    Button set;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_wallpaper);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final WallpaperManager wallpaperManager= WallpaperManager.getInstance(getApplicationContext());
        set=findViewById(R.id.set);
        imageView=findViewById(R.id.imageView);

        intent=getIntent();
        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Bitmap bitmap= ((BitmapDrawable)imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(SetWallpaper.this, "successful😀", Toast.LENGTH_SHORT).show();

                }catch (IOException e){
                    e.printStackTrace();
                }


            }
        });

    }

}