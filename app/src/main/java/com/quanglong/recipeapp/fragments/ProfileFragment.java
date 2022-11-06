package com.quanglong.recipeapp.fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.EditProfileActivity;
import com.quanglong.recipeapp.activities.SettingActivity;
import com.quanglong.recipeapp.activities.SignInActivity;
import com.quanglong.recipeapp.utilities.StatusBarConfig;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
    Button button ;
    ImageView imagesetting;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inflate the layout for this fragment
        TextView txt_description = (TextView) view.findViewById(R.id.description);
        addReadMore(txt_description.getText().toString(), txt_description);

        button = view.findViewById(R.id.change_profile);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EditProfileActivity.class);
                startActivity(intent);
            }
        });

        imagesetting = view.findViewById(R.id.iconmenu);
        imagesetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SettingActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }



    private void addReadMore(final String text, final TextView textView) {
        SpannableString ss = new SpannableString(text.substring(0, 100) + "More...");
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
        ss.setSpan(clickableSpan, ss.length() - 10, ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
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
        ss.setSpan(clickableSpan, ss.length() - 10, ss.length() , Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}