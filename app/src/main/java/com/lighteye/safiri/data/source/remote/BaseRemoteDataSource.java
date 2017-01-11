package com.lighteye.safiri.data.source.remote;

import com.lighteye.safiri.utils.Constants;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yonny on 7/16/16.
 */
public class BaseRemoteDataSource {

    protected final SafiriService mSafiriService;

    public BaseRemoteDataSource() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …
        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.FIREBASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .client(httpClient.build())
                .build();

        mSafiriService = retrofit.create(SafiriService.class);
    }
}
