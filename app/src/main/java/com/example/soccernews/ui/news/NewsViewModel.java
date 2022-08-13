package com.example.soccernews.ui.news;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Room;

import com.example.soccernews.data.dao.AppDatabase;
import com.example.soccernews.data.remote.SoccerNewsApi;
import com.example.soccernews.domain.News;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NewsViewModel extends ViewModel {

    public enum State {
        DOING,DONE,ERROR
    }

    private final SoccerNewsApi api;
    private MutableLiveData<List<News>> news = new  MutableLiveData<>();
    private MutableLiveData<State> state = new  MutableLiveData<>();

    public NewsViewModel() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://barros-thiago.github.io/SoccerNewsAPI/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(SoccerNewsApi.class);

        findNews();
    }

    private void findNews() {
        state.setValue(State.DOING);
        api.getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()){
                    state.setValue(State.DONE);
                    news.setValue(response.body());
                }else{
                    state.setValue(State.ERROR);;
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, @NonNull Throwable t) {
                state.setValue(State.ERROR);
                Log.e(null,t.getMessage());
            }
        });
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }
    public LiveData<State> getState() {return this.news;    }

}