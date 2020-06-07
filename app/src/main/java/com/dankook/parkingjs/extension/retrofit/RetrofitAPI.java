package com.dankook.parkingjs.extension.retrofit;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface RetrofitAPI {

	@Multipart
	@POST("/")
	Call<Integer> sendImage(@Part MultipartBody.Part image);
}
