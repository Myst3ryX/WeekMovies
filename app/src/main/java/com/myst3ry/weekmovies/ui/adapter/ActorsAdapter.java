package com.myst3ry.weekmovies.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.myst3ry.weekmovies.R;
import com.myst3ry.weekmovies.listeners.OnActorClickListener;
import com.myst3ry.weekmovies.model.Actor;
import com.myst3ry.weekmovies.network.GlideApp;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public final class ActorsAdapter extends RecyclerView.Adapter<ActorsAdapter.ActorHolder> {

    private List<Actor> actors;
    private final OnActorClickListener onActorClickListener;


    public ActorsAdapter(final OnActorClickListener onActorClickListener) {
        this.onActorClickListener = onActorClickListener;
        actors = new ArrayList<>();
    }


    @Override
    public ActorHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ActorHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actor, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ActorHolder holder, int position) {
        final Actor actor = actors.get(position);
        if (actor != null) {
            GlideApp.with(holder.itemView.getContext())
                    .load(actor.getPhotoUrl())
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .placeholder(R.color.color_placeholder)
                    .into(holder.photo);

            holder.name.setText(actor.getName());
            holder.birthdayDate.setText(actor.getBirthdayDate());
        }
    }

    @Override
    public int getItemCount() {
        return actors != null ? actors.size() : 0;
    }

    private Actor getActor(int position) {
        return actors.get(position);
    }

    public void setActors(@NonNull List<Actor> actors) {
        this.actors = actors;
        notifyDataSetChanged();
    }

    public final class ActorHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.actor_photo_image)
        ImageView photo;
        @BindView(R.id.actor_name)
        TextView name;
        @BindView(R.id.actor_birthday_date)
        TextView birthdayDate;

        @OnClick(R.id.actor_container)
        public void onClick() {
            final int position = getLayoutPosition();
            onActorClickListener.onActorClick(getActor(position), position);
        }

        ActorHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}



