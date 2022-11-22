package com.quanglong.recipeapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.quanglong.recipeapp.fragments.IngridentFragment;
import com.quanglong.recipeapp.fragments.NotificationAllFragment;
import com.quanglong.recipeapp.fragments.NotificationReadFragment;
import com.quanglong.recipeapp.fragments.NotificationUnreadFragment;
import com.quanglong.recipeapp.fragments.ProcedureFragment;

public class NotificationAdapter extends FragmentPagerAdapter   {

    public NotificationAdapter(@NonNull FragmentManager fm)
    {
        super(fm);
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
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "All";
        else if (position == 1)
            title = "Read";
        else if (position == 2)
            title = "Unread";

        return title;
    }
}
