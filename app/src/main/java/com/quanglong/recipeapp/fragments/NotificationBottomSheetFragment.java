package com.quanglong.recipeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.quanglong.recipeapp.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NotificationBottomSheetFragment extends BottomSheetDialogFragment {
    private int notiId;
    private int adapterPosition;
    private NotificationBottomSheetFragment.Callback callback;

    public static NotificationBottomSheetFragment newInstance() {
        return new NotificationBottomSheetFragment(newInstance().notiId, newInstance().adapterPosition);
    }

    public void setCallback(NotificationBottomSheetFragment.Callback callback) {
        this.callback = callback;
    }

    public NotificationBottomSheetFragment(int _notiId, int _adapterPosition) {
        // Required empty public constructor
        this.notiId = _notiId;
        this.adapterPosition = _adapterPosition;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_bottom_sheet, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RelativeLayout mark_menu = view.findViewById(R.id.mark_as_read_menu);
        RelativeLayout remove_menu = view.findViewById(R.id.remove_menu);
        RelativeLayout cancel_menu = view.findViewById(R.id.cancel_menu);

        mark_menu.setOnClickListener(view1 -> {
            callback.onNotificationActionClick(notiId, 1,adapterPosition);
            dismiss();
        });

        remove_menu.setOnClickListener(view2 -> {
            callback.onNotificationActionClick(notiId, 2,adapterPosition);
            dismiss();
        });

        cancel_menu.setOnClickListener(view3 -> {
            callback.onNotificationActionClick(notiId, 3,adapterPosition);
            dismiss();
        });
    }

    public interface Callback {
        void onNotificationActionClick(int notiId,int optionSelected,int adapterPosition);
    }
}