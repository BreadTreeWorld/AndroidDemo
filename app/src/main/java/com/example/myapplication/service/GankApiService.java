package com.example.myapplication.service;

import com.example.myapplication.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GankApiService {
//    @GET("api/data/{category}/{pageSize}/{page}")
//    Call<GankResponse> getArticles(
//            @Path("category") String category,
//            @Path("pageSize") int pageSize,
//            @Path("page") int page
//    );

    @GET("posts")
    Call<List<Post>> getPosts();
}
