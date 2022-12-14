package com.quanglong.recipeapp.utilities;

import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class BindingAdapter {

    public static void setImageURL(ImageView imageView, String URL){
        try {
            imageView.setAlpha(0f);

            Picasso.get().load(URL).noFade().into(imageView, new Callback() {
                @Override
                public void onSuccess() {
                    imageView.animate().setDuration(200).alpha(1f).start();
                }

                @Override
                public void onError(Exception e) {

                }
            });
        }catch (Exception ignored){

        }
    }
}
