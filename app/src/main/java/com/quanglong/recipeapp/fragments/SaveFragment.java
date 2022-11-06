package com.quanglong.recipeapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.quanglong.recipeapp.R;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class SaveFragment extends Fragment {

    Toolbar toolbar;
    TextView tv_title;

    public SaveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_save, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        toolbar = view.findViewById(R.id.toolbar);
        this.tv_title = view.findViewById(R.id.toolbar_title);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("");
        this.tv_title.setText("Save recipes");

//        // Customize the back button
//        activity.getSupportActionBar().setHomeAsUpIndicator( R.drawable.ic_arrow_left);
//        // showing the back button in action bar
//        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}