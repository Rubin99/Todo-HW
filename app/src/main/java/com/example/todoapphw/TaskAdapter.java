package com.example.todoapphw;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapphw.data.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> data;
    final private ItemClickListener mItemClickListener;
    private Context mContext;


    public TaskAdapter(Context context, ItemClickLitsener litsener){
        mContext = context;
        mItemClickListener = listener;

    }

    public void setData(List<Task> tasks) {
        data = tasks;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TaskAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        //View view = inflater.inflate(R.layout.task_item, parent, false);

        return new ViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = data.get(position);
        holder.bind(task);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public class ViewHolder extends  RecyclerView.ViewHolder{

        private TextView title;
        private TextView description;

        public ViewHolder(LayoutInflater inflater, @NonNull ViewGroup parent) {
            super(inflater.inflate(R.layout.task_item, parent, false));
            title = itemView.findViewById(R.id.title_tv);
            description = itemView.findViewById(R.id.description_tv);
        }

        public void bind(Task task){
            title.setText(task.getTitle());
            description.setText(task.getDescription());
        }

    }
}
