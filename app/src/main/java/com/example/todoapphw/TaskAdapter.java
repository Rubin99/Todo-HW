package com.example.todoapphw;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoapphw.data.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private List<Task> data;
    final private ItemClickListener mItemClickListener;
    private Context mContext;


    public TaskAdapter(Context context, ItemClickListener litsener){
        mContext = context;
        mItemClickListener = litsener;

    }

    public void setData(List<Task> tasks) {
        data = tasks;
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.task_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapter.ViewHolder holder, int position) {
        Task task = data.get(position);
        String get_Title = task.getTitle();
        String get_Description = task.getDescription();
        holder.bind(task);
        int priority= task.getPriority();

        holder.title.setText(get_Title);
        holder.description.setText(get_Description);

        String priorityString = "" + priority;
        holder.priorityView.setText(priorityString);

        GradientDrawable priorityCircle = (GradientDrawable) holder.priorityView.getBackground();
        // Get the appropriate background color based on the priority
        int priorityColor = getPriorityColor(priority);
        priorityCircle.setColor(priorityColor);
    }

    private int getPriorityColor(int priority) {
        int priorityColor = 0;

        switch (priority) {
            case 1:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialRed);
                break;
            case 2:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialOrange);
                break;
            case 3:
                priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow);
                break;
            default:
                break;
        }
        return priorityColor;
    }

    @Override
    public int getItemCount() {
        if (data == null)
            return 0;
        return data.size();
    }

    public List<Task> getTasks(){
        return data;
    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }

    public class ViewHolder extends  RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView title;
        private TextView description;
        private TextView priorityView;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title_tv);
            description = itemView.findViewById(R.id.description_tv);
            itemView.setOnClickListener(this);
        }

        public void bind(Task task){
            title.setText(task.getTitle());
            description.setText(task.getDescription());
        }

        @Override
        public void onClick(View v) {
            int elementId = data.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
}
