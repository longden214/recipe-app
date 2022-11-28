package com.quanglong.recipeapp.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.quanglong.recipeapp.fragments.IngridentFragment;
import com.quanglong.recipeapp.fragments.NotificationAllFragment;
import com.quanglong.recipeapp.fragments.NotificationReadFragment;
import com.quanglong.recipeapp.fragments.NotificationUnreadFragment;
import com.quanglong.recipeapp.fragments.ProcedureFragment;

public class NotificationAdapter extends FragmentPagerAdapter {

    Context mContext;
    int mTotalTabs;

    public NotificationAdapter(Context context , FragmentManager fragmentManager , int totalTabs)
    {
        super(fragmentManager);
        mContext = context;
        mTotalTabs = totalTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new NotificationAllFragment();
        else if (position == 1)
            fragment = new NotificationReadFragment();
        else if (position == 2)
            fragment = new NotificationUnreadFragment();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return mTotalTabs;
    }
}
