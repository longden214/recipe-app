package com.quanglong.recipeapp.fragments;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.FollowerActivity;
import com.quanglong.recipeapp.activities.FollowingActivity;
import com.quanglong.recipeapp.activities.RecipeDetailActivity;
import com.quanglong.recipeapp.activities.SettingActivity;
import com.quanglong.recipeapp.activities.UserProfileActivity;
import com.quanglong.recipeapp.adapter.RecipeProfileAdptar;
import com.quanglong.recipeapp.adapter.RecipeSaveAdptar;
import com.quanglong.recipeapp.listener.RecipeDetailListener;
import com.quanglong.recipeapp.model.Recipe;
import com.quanglong.recipeapp.model.RecipeRequest;
import com.quanglong.recipeapp.responses.RecipeResponse;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.utilities.UserLocalStore;
import com.quanglong.recipeapp.viewmodels.RecipeViewModel;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener, RecipeDetailListener {
    Button button;
    ImageView imagesetting;
    Toolbar toolbar;
    TextView txt_description;
    TextView tv_title;
    TextView dplName;
    UserLocalStore userLocalStore;
    UserLoginResponse user;
    private CircleImageView profile_image;
    private TextView itemrecipe;
    private TextView itemFollwer;
    private TextView itemFollwing;
    private TextView job;
    private TextView total_item;
    private RecyclerView recyclerView;
    private ArrayList<Recipe> mlistreRecipes = new ArrayList<>() ;
    private RecipeViewModel viewModel;
    private RecipeProfileAdptar recipeProfileAdptar;
    private int id ;
    private SharedPreferences userLocalDatabase;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        setHasOptionsMenu(true);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doInitialization(view);

        user = userLocalStore.getLoggedInUser();

        toolbar = view.findViewById(R.id.toolbar);
        this.tv_title = view.findViewById(R.id.toolbar_title);
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        activity.setSupportActionBar(toolbar);
        activity.getSupportActionBar().setTitle("");
        this.tv_title.setText("Profile");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = EditProfileDialog.newInstance();
                ((EditProfileDialog) dialog).setCallback(new EditProfileDialog.Callback() {
                    @Override
                    public void onActionClick(String name) {
                        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.show(getActivity().getSupportFragmentManager(), "tag");
            }
        });

        this.dplName.setText(user.getDisplayName());
        this.txt_description.setText(user.getDescription());

        if (txt_description.getLineCount() > 2) {
            addReadMore(txt_description.getText().toString(), txt_description);
        }
        if (!user.getAvatar().equals("")){
            setImageURL(profile_image, user.getAvatar());
        }else{
            profile_image.setImageResource(R.drawable.avater_default);
        }
        this.itemrecipe.setText(String.valueOf(user.getTotalRecipe()));
        this.itemFollwer.setText(String.valueOf(user.getTotalFollowedByOthersUser()));
        this.itemFollwing.setText(String.valueOf(user.getTotalFollowOtherUser()));
        this.job.setText(user.getJob());
        this.total_item.setText(user.getTotalRecipe() + " items");

        itemFollwer.setOnClickListener(view1 -> {
            startActivity(new Intent(getActivity(), FollowerActivity.class));
        });

        itemFollwing.setOnClickListener(view2 -> {
            startActivity(new Intent(getActivity(), FollowingActivity.class));
        });
        id = userLocalDatabase.getInt("id", -1);
        setNewRecipe(mlistreRecipes);
        getNewRecipe();

    }

    private void setNewRecipe(ArrayList<Recipe> RecipeSaveList) {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        recipeProfileAdptar = new RecipeProfileAdptar(getActivity(),RecipeSaveList,this);
        recyclerView.setAdapter(recipeProfileAdptar);
    }

    private void getNewRecipe() {
        RecipeRequest newRequest = new RecipeRequest();
        newRequest.setKeyword("");
        newRequest.setListCatId(new int[]{});
        newRequest.setAuthorId(id);
        newRequest.setName("");
        newRequest.setOrigin("");
        newRequest.setIngredient("");
        newRequest.setMinServer(0);
        newRequest.setMaxServer(0);
        newRequest.setMinTotalViews(0);
        newRequest.setMaxTotalViews(0);
        newRequest.setMinTotalRating(0);
        newRequest.setMaxTotalRating(0);
        newRequest.setMinCalories(0);
        newRequest.setMaxCalories(0);
        newRequest.setMinFat(0);
        newRequest.setMaxFat(0);
        newRequest.setMinProtein(0);
        newRequest.setMaxProtein(0);
        newRequest.setMinCarbo(0);
        newRequest.setMaxCarbo(0);
        newRequest.setMinAvgRating(0);
        newRequest.setMaxAvgRating(5);
        newRequest.setCookTime("");
        newRequest.setStatus(-1);
        newRequest.setSortByIdDESC(true);
        newRequest.setSortByNameASC(false);
        newRequest.setSortByServesASC(false);
        newRequest.setSortByServesDESC(false);
        newRequest.setSortByTotalViewDESC(false);
        newRequest.setSortByAvgRatingDESC(false);
        newRequest.setSortByTotalRatingDESC(false);
        newRequest.setSortByCaloriesDESC(false);
        newRequest.setSortByFatDESC(false);
        newRequest.setSortByProteinDESC(false);
        newRequest.setSortByCarbo(false);
        newRequest.setPageIndex(1);
        newRequest.setPageSize(10);
        viewModel.getAllNewRecipe(newRequest).observe(getViewLifecycleOwner(), new Observer<RecipeResponse>() {
            @Override
            public void onChanged(RecipeResponse recipeResponse) {
                if (recipeResponse.getNewRecipeShow() != null) {
                    if (recipeResponse.getNewRecipeShow().size() > 0) {
                        int oldCount = mlistreRecipes.size();
                        mlistreRecipes.addAll(recipeResponse.getNewRecipeShow());
                        recipeProfileAdptar.notifyItemRangeInserted(oldCount, mlistreRecipes.size());
                    }
                }
            }
        });
    }
    private void doInitialization(View view) {
        userLocalStore = new UserLocalStore(getActivity());
        user = new UserLoginResponse();
        this.dplName = view.findViewById(R.id.itemUsername);
        txt_description = (TextView) view.findViewById(R.id.description);
        button = view.findViewById(R.id.change_profile);
        profile_image = view.findViewById(R.id.profile_image);
        itemrecipe = view.findViewById(R.id.itemrecipe);
        itemFollwer = view.findViewById(R.id.itemFollwer);
        itemFollwing = view.findViewById(R.id.itemFollwing);
        job = view.findViewById(R.id.job);
        total_item = view.findViewById(R.id.total_item);
        recyclerView = view.findViewById(R.id.listviewSave);
        viewModel = new ViewModelProvider(this).get(RecipeViewModel.class);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);


    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.setting_menu, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected( @NonNull MenuItem item ) {
        AppCompatActivity activity = (AppCompatActivity) getActivity();

        switch (item.getItemId()){
            case R.id.menu_setting:
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void addReadMore(final String text, final TextView textView) {
        SpannableString ss = new SpannableString(text.substring(0, 100) + "\nMore...");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                addReadLess(text, textView);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ds.setColor(getResources().getColor(R.color.medium_butt, getActivity().getTheme()));
                } else {
                    ds.setColor(getResources().getColor(R.color.text_color));
                }
            }
        };
        ss.setSpan(clickableSpan, ss.length() - 8, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void addReadLess(final String text, final TextView textView) {
        SpannableString ss = new SpannableString(text + " Read less");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                addReadMore(text, textView);
            }

            @Override
            public void updateDrawState(TextPaint ds) {
                super.updateDrawState(ds);
                ds.setUnderlineText(false);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ds.setColor(getResources().getColor(R.color.medium_butt, getActivity().getTheme()));
                } else {
                    ds.setColor(getResources().getColor(R.color.text_color));
                }
            }
        };
        ss.setSpan(clickableSpan, ss.length() - 10, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onRecipeDetailListener(int recipe_id, String recipe_name) {
        Intent intent = new Intent(getActivity(), RecipeDetailActivity.class);
        intent.putExtra("recipeId",recipe_id);
        intent.putExtra("recipeName",recipe_name);
        startActivity(intent);
    }
}