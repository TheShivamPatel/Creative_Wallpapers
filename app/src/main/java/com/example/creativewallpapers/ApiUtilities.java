package com.example.creativewallpapers;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtilities {

    private static  Retrofit retrofit= null;
    public static final String API= "563492ad6f91700001000001af57799f698d4f01b80164628cfaa983";

    public static ApiInterfaces getApiInterfaces()
    {
        if (retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(ApiInterfaces.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterfaces.class);
    }

}
