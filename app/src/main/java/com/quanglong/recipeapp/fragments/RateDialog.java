package com.quanglong.recipeapp.fragments;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.quanglong.recipeapp.R;

public class RateDialog extends DialogFragment implements View.OnClickListener {

    Button btn_send;
    RatingBar ratingBar;
    private RateDialog.Callback callback;
    private float ratingValue;

    public RateDialog(float ratingValue) {
        this.ratingValue = ratingValue;
    }

    static RateDialog newInstance() {
        return new RateDialog(newInstance().ratingValue);
    }

    public void setCallback(RateDialog.Callback callback) {
        this.callback = callback;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.rate_dialog,null);

        if (getDialog() != null && getDialog().getWindow() != null) {
            getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.btn_send = view.findViewById(R.id.btn_send);
        this.ratingBar = view.findViewById(R.id.ratingBar);
        this.ratingBar.setRating(ratingValue);

        this.ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                btn_send.setEnabled(true);
                btn_send.setBackground(getResources().getDrawable(R.drawable.bg_button_send));
                btn_send.setOnClickListener(RateDialog.this);

                return false;
            }
        });
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_send){
            callback.onActionClick(ratingBar.getRating());
            dismiss();
        }
    }

    public interface Callback {
        void onActionClick(float rating);
    }
}
