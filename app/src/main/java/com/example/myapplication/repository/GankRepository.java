package com.example.myapplication.repository;

import android.os.Handler;
import android.os.Looper;
import com.example.myapplication.model.Post;
import com.example.myapplication.service.GankApiService;
import com.example.myapplication.utils.ServiceGenerator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class GankRepository {

    private static GankRepository instance;
    private final GankApiService apiService;
    private final ExecutorService executorService;
    private final Handler mainHandler;

    private GankRepository() {
        apiService = ServiceGenerator.getGankApiService();
        executorService = Executors.newSingleThreadExecutor();
        mainHandler = new Handler(Looper.getMainLooper());
    }

    public static synchronized GankRepository getInstance() {
        if (instance == null) {
            instance = new GankRepository();
        }
        return instance;
    }

    public void getPosts(RepositoryCallback<List<Post>> callback) {
        android.util.Log.d("Repository", "Start request JSONPlaceholder API");

        Call<List<Post>> call = apiService.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                android.util.Log.d("Repository", "Receive Response: " + response.code());

                if (response.isSuccessful() && response.body() != null) {
                    android.util.Log.d("Repository", "Data total " + response.body().size() + " records");
                    mainHandler.post(() -> callback.onSuccess(response.body()));
                } else {
                    mainHandler.post(() -> callback.onError("Response code: " + response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                android.util.Log.e("Repository", "request failed: " + t.getMessage());
                mainHandler.post(() -> callback.onError(t.getMessage()));
            }
        });
    }

    public interface RepositoryCallback<T> {
        void onSuccess(T data);
        void onError(String error);
    }
}