package com.quanglong.recipeapp.fragments;

import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.content.Intent;
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

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.FollowerActivity;
import com.quanglong.recipeapp.activities.FollowingActivity;
import com.quanglong.recipeapp.activities.SettingActivity;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.utilities.UserLocalStore;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
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
        setImageURL(profile_image, user.getAvatar());
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
}