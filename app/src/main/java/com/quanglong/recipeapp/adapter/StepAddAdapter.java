package com.quanglong.recipeapp.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.databinding.ItemStepAddBinding;
import com.quanglong.recipeapp.listener.StepListener;
import com.quanglong.recipeapp.model.Step;

import java.util.List;

public class StepAddAdapter extends RecyclerView.Adapter<StepAddAdapter.StepAddViewHolder> {
    private List<Step> steps;
    private LayoutInflater layoutInflater;
    private StepListener stepListener;

    public StepAddAdapter(List<Step> _steps, StepListener _stepListener){
        this.steps = _steps;
        this.stepListener = _stepListener;
    }

    @NonNull
    @Override
    public StepAddViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }

        ItemStepAddBinding itemStepAddBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_step_add,parent, false
        );

        return new StepAddViewHolder(itemStepAddBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull StepAddViewHolder holder, int position) {
        holder.bindTVShow(steps.get(position));
    }

    @Override
    public int getItemCount() {
        return steps.size();
    }

    class StepAddViewHolder extends RecyclerView.ViewHolder{
        private ItemStepAddBinding itemStepAddBinding;
        private StepAddAdapter adapter;

        public StepAddViewHolder(ItemStepAddBinding itemStepAddBinding) {
            super(itemStepAddBinding.getRoot());
            this.itemStepAddBinding = itemStepAddBinding;
        }

        public void bindTVShow(Step step){
            step.setStepNumber(steps.indexOf(step)+1);

            itemStepAddBinding.setStep(step);
            itemStepAddBinding.executePendingBindings();
            itemStepAddBinding.btnRemoveStep.setOnClickListener(view -> stepListener.onStepRemove(steps.indexOf(step)+1));
        }
    }

}
