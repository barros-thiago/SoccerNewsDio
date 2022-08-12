package com.example.soccernews.data.remote;

import com.example.soccernews.domain.News;
import com.google.gson.Gson;
import com.google.gson.JsonArray;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface SoccerNewsApi {

    @GET("news.json")
    Call<List<News>> getNews();
}
