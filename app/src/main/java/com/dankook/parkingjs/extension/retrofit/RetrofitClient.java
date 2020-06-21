package com.dankook.parkingjs.extension.retrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

	private static final Gson gson = new GsonBuilder()
			.setLenient()
			.create();

	public static final Retrofit retrofit = new Retrofit.Builder()
			.baseUrl("http://18.182.47.67:80/")
			.addConverterFactory(GsonConverterFactory.create(gson))
			.build();

	public static final RetrofitAPI api = retrofit.create(RetrofitAPI.class);
}
