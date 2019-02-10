package com.icodev76.redditpics;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.icodev76.redditpics.model.children.Children;
import java.util.List;

import static android.support.constraint.Constraints.TAG;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;

public class CustomAdapter
        extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>{
    private Context context;
    private List<Children> children;

    public CustomAdapter(Context context, List<Children> children) {
        this.context = context;
        this.children = children;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.card_view,parent,false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {
        final Children childrenlist= children.get(position);
        Glide
                .with(context)
                .load(childrenlist.getData().getUrl())
                .apply(fitCenterTransform()
                        .placeholder(R.color.colorPrimaryDark)
                )
                .into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.d(TAG, "onClick: "+childrenlist.getData().getUrl());
                Intent intent = new Intent(context, Webview.class);
                intent.putExtra("url",childrenlist.getData().getUrl());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return children.size();
    }




    public  class CustomViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageview);
        }
    }
}
