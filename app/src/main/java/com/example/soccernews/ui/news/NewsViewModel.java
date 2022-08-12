package com.example.soccernews.ui.news;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.soccernews.data.remote.SoccerNewsApi;
import com.example.soccernews.domain.News;
import com.google.gson.JsonArray;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    private MutableLiveData<List<News>> news = new  MutableLiveData<>();

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://barros-thiago.github.io/SoccerNewsAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        SoccerNewsApi api = retrofit.create(SoccerNewsApi.class);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()){
                    news.setValue(response.body());
                }else{
                    Log.e("onResponde","Deu ruim");
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                Log.e(null,t.getMessage());
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }

}