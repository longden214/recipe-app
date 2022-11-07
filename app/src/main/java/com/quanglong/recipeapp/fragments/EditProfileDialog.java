package com.quanglong.recipeapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.AccountActivity;

public class EditProfileDialog extends DialogFragment implements View.OnClickListener {

    private Callback callback;

    static EditProfileDialog newInstance() {
        return new EditProfileDialog();
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);
        ImageView close = view.findViewById(R.id.fullscreen_dialog_close);
        TextView action = view.findViewById(R.id.fullscreen_dialog_save);
        TextView btn_sett_acc = view.findViewById(R.id.btn_setting_account);

        close.setOnClickListener(this);
        action.setOnClickListener(this);
        btn_sett_acc.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;

            case R.id.btn_setting_account:
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                break;

            case R.id.fullscreen_dialog_save:
                callback.onActionClick("Whatever");
                dismiss();
                break;
        }
    }

    public interface Callback {
        void onActionClick(String name);
    }
}
