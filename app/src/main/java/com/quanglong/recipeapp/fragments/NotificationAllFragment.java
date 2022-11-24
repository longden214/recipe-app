package com.quanglong.recipeapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.NotificationAllAdapter;
import com.quanglong.recipeapp.listener.NotificationListener;
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.responses.NotificationResponse;
import com.quanglong.recipeapp.viewmodels.NotificationViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class NotificationAllFragment extends Fragment implements NotificationListener {
    private NotificationAllAdapter notificationAllAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Notifications> mlistNotification = new ArrayList<>();
    private NotificationViewModel notificationViewModel;
    private int id;
    private SharedPreferences userLocalDatabase;

    public NotificationAllFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doInitialization(view);
        id = userLocalDatabase.getInt("id", -1);
        setNotificationAll(mlistNotification);
        getNotificationAll();
    }

    private void setNotificationAll(ArrayList<Notifications> mlistNotification) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        notificationAllAdapter = new NotificationAllAdapter(mlistNotification, getActivity(), this);
        recyclerView.setAdapter(notificationAllAdapter);
    }

    private void getNotificationAll() {
        notificationViewModel.getNotificationWithParam(id, -1, 1, 10).observe(getActivity(), new Observer<NotificationResponse>() {
            @Override
            public void onChanged(NotificationResponse notification) {
                if (notification != null) {
                    if (notification.getNotifications().size() > 0) {
                        int oldCount = mlistNotification.size();
                        mlistNotification.addAll(notification.getNotifications());
                        notificationAllAdapter.notifyItemRangeInserted(oldCount, mlistNotification.size());
                    }
                }
            }
        });
    }

    private void doInitialization(View view) {
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);
        recyclerView = view.findViewById(R.id.notification_all_rv);
        mlistNotification = new ArrayList<>();
        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);
    }

    @Override
    public void onNotificationClicked(int id, int adapterPosition) {
        NotificationBottomSheetFragment bottomSheetFragment = new NotificationBottomSheetFragment(id, adapterPosition);
        ((NotificationBottomSheetFragment) bottomSheetFragment).setCallback(new NotificationBottomSheetFragment.Callback() {
            @Override
            public void onNotificationActionClick(int notiId, int optionSelected, int adapterPosition) {
                Toast.makeText(getActivity(), "id " + notiId + " selected " + optionSelected + " position " + adapterPosition, Toast.LENGTH_SHORT).show();
            }
        });
        bottomSheetFragment.show(getActivity().getSupportFragmentManager(), bottomSheetFragment.getTag());
    }
}