package com.aushadh.hospital.App;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aushadh.hospital.Model.Path;
import com.aushadh.hospital.R;

import java.util.List;

public class PathAdapter extends RecyclerView.Adapter<PathAdapter.ViewHolder> {
    private List<Path> list ;
    private Context context ;

    public PathAdapter(List<Path> list, Context context, Activity activity) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_layout_lab  , parent,false);
        return new ViewHolder( view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.type.setText(list.get(position).getType());
        holder.address.setText(list.get(position).getAddress());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends  RecyclerView.ViewHolder {
        TextView name , type ,address ;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            type = itemView.findViewById(R.id.type);
            address = itemView.findViewById(R.id.address);
        }
    }
}
