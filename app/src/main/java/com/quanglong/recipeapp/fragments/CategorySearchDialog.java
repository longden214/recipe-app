package com.quanglong.recipeapp.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.quanglong.recipeapp.R;

public class CategorySearchDialog extends DialogFragment implements View.OnClickListener {

    private CategorySearchDialog.Callback callback;

    public static CategorySearchDialog newInstance() {
        return new CategorySearchDialog();
    }

    public void setCallback(CategorySearchDialog.Callback callback) {
        this.callback = callback;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogTheme);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.choose_category, container, false);
        TextView close = view.findViewById(R.id.btn_cancel);
        EditText edt_search = view.findViewById(R.id.category_search);

        close.setOnClickListener(this);
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (edt_search.getText().toString().length() > 0) {
                    edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_close_24, 0);

                    edt_search.setOnTouchListener(new View.OnTouchListener() {
                        @Override
                        public boolean onTouch(View view, MotionEvent event) {
                            final int DRAWABLE_LEFT = 0;
                            final int DRAWABLE_TOP = 1;
                            final int DRAWABLE_RIGHT = 2;
                            final int DRAWABLE_BOTTOM = 3;

                            if (event.getAction() == MotionEvent.ACTION_UP) {
                                if (event.getRawX() >= (edt_search.getRight() -
                                        edt_search.getCompoundDrawables()[DRAWABLE_RIGHT].getBounds().width())) {
                                        edt_search.setText("");

                                        edt_search.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_search_normal, 0, 0, 0);
                                    return true;
                                }
                            }
                            return false;
                        }
                    });
                } else
                    edt_search.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.btn_cancel:
                dismiss();
                break;

            case R.id.fullscreen_dialog_save:
                callback.onActionClick("Whatever");
                dismiss();
                break;
        }
    }

    public interface Callback {
        void onActionClick(String name);
    }
}
