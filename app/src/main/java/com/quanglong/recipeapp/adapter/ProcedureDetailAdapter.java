package com.quanglong.recipeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.model.Ingredient;
import com.quanglong.recipeapp.model.Step;

import java.util.List;

public class ProcedureDetailAdapter extends RecyclerView.Adapter<ProcedureDetailAdapter.StepViewHolder> {

    Context context;
    List<Step> stepList;

    public ProcedureDetailAdapter(Context context, List<Step> stepList) {
        this.context = context;
        this.stepList = stepList;
    }

    @NonNull
    @Override
    public StepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_procedure,parent,false);

        return new StepViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StepViewHolder holder, int position) {
        Step step = stepList.get(position);
        if (step == null){
            return;
        }

        holder.step_number.setText("Step " + step.getStepNumber());
        holder.step_content.setText(step.getDescription());
    }

    @Override
    public int getItemCount() {
        return stepList.size();
    }

    public static final class StepViewHolder extends RecyclerView.ViewHolder{

        TextView step_number, step_content;

        public StepViewHolder(@NonNull View itemView) {
            super(itemView);

            step_number = itemView.findViewById(R.id.step_number);
            step_content = itemView.findViewById(R.id.content);
        }
    }
}
