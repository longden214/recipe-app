package com.quanglong.recipeapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.quanglong.recipeapp.fragments.IngridentFragment;
import com.quanglong.recipeapp.fragments.ProcedureFragment;

public class RecipeDetailVPAdapter extends FragmentPagerAdapter {
    private int id;
    public RecipeDetailVPAdapter(@NonNull FragmentManager fm,int _id)
    {
        super(fm);
        id = _id;
    }

    @NonNull
    @Override
    public Fragment getItem(int position)
    {
        Fragment fragment = null;
        if (position == 0)
            fragment = new IngridentFragment(id);
        else if (position == 1)
            fragment = new ProcedureFragment();

        return fragment;
    }

    @Override
    public int getCount()
    {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        String title = null;
        if (position == 0)
            title = "Ingrident";
        else if (position == 1)
            title = "Procedure";

        return title;
    }
}
