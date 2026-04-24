package com.example.myapplication.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.myapplication.repository.GankRepository;
import java.util.List;

public class ListViewModel extends ViewModel {

    private final GankRepository repository;

    private final MutableLiveData<List<Post>> articlesLiveData = new MutableLiveData<>();

    private final MutableLiveData<Boolean> isLoadingLiveData = new MutableLiveData<>(false);

    private final MutableLiveData<Boolean> isRefreshingLiveData = new MutableLiveData<>(false);

    private final MutableLiveData<String> errorMessageLiveData = new MutableLiveData<>();

    private boolean isDataLoaded = false;

    public ListViewModel() {
        repository = GankRepository.getInstance();
    }


    public LiveData<List<Post>> getArticles() {
        return articlesLiveData;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoadingLiveData;
    }


    public LiveData<Boolean> getIsRefreshing() {
        return isRefreshingLiveData;
    }


    public LiveData<String> getErrorMessage() {
        return errorMessageLiveData;
    }


    public boolean shouldLoadInitialData() {
        return !isDataLoaded && !Boolean.TRUE.equals(isLoadingLiveData.getValue());
    }

    public void loadPosts() {
        if (Boolean.TRUE.equals(isLoadingLiveData.getValue())) {
            android.util.Log.d("ListViewModel", "正在加载中，跳过重复请求");
            return;
        }

        isLoadingLiveData.setValue(true);
        errorMessageLiveData.setValue(null);
        android.util.Log.d("ListViewModel", "开始请求 JSONPlaceholder API");

        repository.getPosts(new GankRepository.RepositoryCallback<List<Post>>() {
            @Override
            public void onSuccess(List<Post> data) {
                isLoadingLiveData.setValue(false);

                if (data != null && !data.isEmpty()) {
                    android.util.Log.d("ListViewModel", "数据加载成功，共 " + data.size() + " 条");
                    articlesLiveData.setValue(data);
                    isDataLoaded = true;
                } else {
                    android.util.Log.w("ListViewModel", "数据为空");
                    errorMessageLiveData.setValue("数据为空");
                }
            }

            @Override
            public void onError(String error) {
                // 关闭加载状态
                isLoadingLiveData.setValue(false);
                android.util.Log.e("ListViewModel", "加载失败: " + error);
                errorMessageLiveData.setValue(error);
            }
        });
    }

    /**
     * 下拉刷新（重新加载数据）
     */
    public void refresh() {
        // 防止重复刷新
        if (Boolean.TRUE.equals(isRefreshingLiveData.getValue())) {
            android.util.Log.d("ListViewModel", "正在刷新中，跳过重复请求");
            return;
        }

        // 设置刷新状态
        isRefreshingLiveData.setValue(true);
        errorMessageLiveData.setValue(null);
        android.util.Log.d("ListViewModel", "下拉刷新，重新请求数据");

        // 执行网络请求
        repository.getPosts(new GankRepository.RepositoryCallback<List<Post>>() {
            @Override
            public void onSuccess(List<Post> data) {
                // 关闭刷新状态
                isRefreshingLiveData.setValue(false);

                if (data != null && !data.isEmpty()) {
                    android.util.Log.d("ListViewModel", "刷新成功，共 " + data.size() + " 条");
                    articlesLiveData.setValue(data);
                    isDataLoaded = true;
                } else {
                    android.util.Log.w("ListViewModel", "刷新返回数据为空");
                    errorMessageLiveData.setValue("刷新失败，数据为空");
                }
            }

            @Override
            public void onError(String error) {
                // 关闭刷新状态
                isRefreshingLiveData.setValue(false);
                android.util.Log.e("ListViewModel", "刷新失败: " + error);
                errorMessageLiveData.setValue("刷新失败: " + error);
            }
        });
    }

    /**
     * 手动清除错误信息
     */
    public void clearError() {
        errorMessageLiveData.setValue(null);
    }

    /**
     * 重置加载状态（用于重新加载）
     */
    public void resetLoadedState() {
        isDataLoaded = false;
    }

    // ==================== 生命周期 ====================

    @Override
    protected void onCleared() {
        super.onCleared();
        android.util.Log.d("ListViewModel", "ViewModel 被清除，释放资源");
        // 如果有需要清理的资源，在这里处理
    }
}