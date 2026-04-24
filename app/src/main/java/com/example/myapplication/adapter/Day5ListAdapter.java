package com.example.myapplication.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.databinding.ItemGankArticleBinding;
import com.example.myapplication.model.GankArticle;
import com.example.myapplication.model.Post;
import com.facebook.drawee.view.SimpleDraweeView;
import java.util.ArrayList;
import java.util.List;

public class Day5ListAdapter extends RecyclerView.Adapter<Day5ListAdapter.ViewHolder> {

    private List<Post> posts = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemGankArticleBinding binding = ItemGankArticleBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public void submitList(List<Post> newPosts) {
        if (newPosts == null) return;
        this.posts.clear();
        this.posts.addAll(newPosts);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private final ItemGankArticleBinding binding;

        ViewHolder(ItemGankArticleBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Post post) {
            binding.tvTitle.setText(post.getTitle());
            binding.tvDesc.setText(post.getBody());
            binding.tvWho.setText("User: " + post.getUserId());
            binding.executePendingBindings();
        }
    }
}
