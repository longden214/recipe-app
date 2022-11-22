package com.quanglong.recipeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.Notifications;


import java.util.ArrayList;

public class NotificationAllAdapter extends RecyclerView.Adapter<NotificationAllAdapter.NotificationAllViewHolder>{
    ArrayList<Notifications> mlist;
    Context mContext;

    public
    NotificationAllAdapter(ArrayList<Notifications> mlist, Context _mContext){
        this.mlist = mlist;
        this.mContext = _mContext;

    }
    @NonNull
    @Override
    public NotificationAllViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.item_notification,parent,false);
        NotificationAllViewHolder mvh = new NotificationAllAdapter.NotificationAllViewHolder(v);
        return mvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAllViewHolder holder, int position) {
        Notifications notification = mlist.get(position);
        if(notification == null){
            return;
        }
        holder.date.setText(mlist.get(position).getCreateDate().substring(0,10));
        holder.type.setText(mlist.get(position).getNotificationType());
        holder.description.setText(mlist.get(position).getDescription());
        holder.time.setText(mlist.get(position).getCreateDate().substring(11,16));
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public static class NotificationAllViewHolder extends RecyclerView.ViewHolder{
        TextView date,type,time;
        TextView description;

        public NotificationAllViewHolder(View v){
            super(v);
            this.date = v.findViewById(R.id.textView7);
            this.type = v.findViewById(R.id.notifi_title);
            this.description=v.findViewById(R.id.textView41);
            this.time = v.findViewById(R.id.textView38);
        }
    }
}
