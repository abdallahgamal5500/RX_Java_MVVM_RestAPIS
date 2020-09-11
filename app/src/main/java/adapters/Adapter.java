package adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm_rxjava.R;

import java.util.ArrayList;
import java.util.List;

import pojo.Model;

public class Adapter extends RecyclerView.Adapter<Adapter.viewHolder> {
    private List<Model> arrayList = new ArrayList<>();

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_design, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.userId.setText(arrayList.get(position).getUserId());
        holder.id.setText(arrayList.get(position).getId());
        holder.title.setText(arrayList.get(position).getTitle());
        holder.body.setText(arrayList.get(position).getBody());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public void setList(List<Model> arrayList) {
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        TextView userId,id,title,body;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            userId = itemView.findViewById(R.id.userId);
            id = itemView.findViewById(R.id.id);
            title = itemView.findViewById(R.id.title);
            body = itemView.findViewById(R.id.body);
        }
    }
}