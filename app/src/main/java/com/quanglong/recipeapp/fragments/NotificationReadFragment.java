package com.quanglong.recipeapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.adapter.NotificationAllAdapter;
import com.quanglong.recipeapp.model.Notifications;
import com.quanglong.recipeapp.responses.NotificationResponse;
import com.quanglong.recipeapp.viewmodels.NotificationViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationReadFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationReadFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private NotificationAllAdapter notificationAllAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Notifications> mlistNotification = new ArrayList<>();
    private NotificationViewModel notificationViewModel;
    private int id ;
    private SharedPreferences userLocalDatabase;

    public NotificationReadFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotificationReadFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotificationReadFragment newInstance(String param1, String param2) {
        NotificationReadFragment fragment = new NotificationReadFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doInitialization(view);
        id = userLocalDatabase.getInt("id", -1);
        setNotificationAll(mlistNotification);
        getNotificationAll();

//        Button btn = view.findViewById(R.id.button3);
//        btn.setOnClickListener(this);
    }
    private void setNotificationAll(ArrayList<Notifications> mlistNotification){
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        notificationAllAdapter = new NotificationAllAdapter(mlistNotification,getActivity());
        recyclerView.setAdapter(notificationAllAdapter);
    }

    private void getNotificationAll(){
        notificationViewModel.getNotificationWithParam(id,1,1,10).observe(getActivity(), new Observer<NotificationResponse>() {
            @Override
            public void onChanged(NotificationResponse notification) {
                if(notification != null){
                    if(notification.getNotifications().size()>0){
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
        recyclerView = view.findViewById(R.id.notification_read_rv);
        mlistNotification = new ArrayList<>();
        notificationViewModel = new ViewModelProvider(this).get(NotificationViewModel.class);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_notification_read, container, false);
    }
}