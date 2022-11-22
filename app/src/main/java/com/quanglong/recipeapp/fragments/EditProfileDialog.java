package com.quanglong.recipeapp.fragments;

import static android.app.Activity.RESULT_OK;
import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.AccountActivity;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import pub.devrel.easypermissions.AppSettingsDialog;
import pub.devrel.easypermissions.EasyPermissions;

public class EditProfileDialog extends DialogFragment implements View.OnClickListener, EasyPermissions.PermissionCallbacks {

    private SharedPreferences userLocalDatabase;
    private CircleImageView avatar;
    private EditText name;
    private EditText username;
    private EditText description;
    private TextView edit_avatar;
    private Callback callback;
    private Bitmap bitmap;
    private ArrayList<Uri> images_picker = new ArrayList<>();

    static EditProfileDialog newInstance() {
        return new EditProfileDialog();
    }

    public void setCallback(Callback callback) {
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
        View view = inflater.inflate(R.layout.dialog_edit_profile, container, false);
        ImageView close = view.findViewById(R.id.fullscreen_dialog_close);
        TextView action = view.findViewById(R.id.fullscreen_dialog_save);
        TextView btn_sett_acc = view.findViewById(R.id.btn_setting_account);
        this.avatar = view.findViewById(R.id.profile_image);
        this.name = view.findViewById(R.id.itemName);
        this.username = view.findViewById(R.id.itemUsername);
        this.description = view.findViewById(R.id.itemDescription);
        this.edit_avatar = view.findViewById(R.id.edit_avatar);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);

        if (!userLocalDatabase.getString("avatar", "").equals("")){
            setImageURL(avatar, userLocalDatabase.getString("avatar", ""));
        }else{
            avatar.setImageResource(R.drawable.avater_default);
        }
        this.name.setText(userLocalDatabase.getString("displayName", ""));
        this.username.setText(userLocalDatabase.getString("userName", ""));
        this.description.setText(userLocalDatabase.getString("description", ""));

        close.setOnClickListener(this);
        action.setOnClickListener(this);
        btn_sett_acc.setOnClickListener(this);
        edit_avatar.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id) {

            case R.id.fullscreen_dialog_close:
                dismiss();
                break;
            case R.id.edit_avatar:
                images_picker.clear();
                // define camera & storange permissions
                String[] strings = {Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE};
                // check condition
                if (EasyPermissions.hasPermissions(getActivity(), strings)) {
                    //when permissions are already granted
                    //create method
                    imagePicker();
                } else {
                    //when permission not granted
                    //Request permission
                    EasyPermissions.requestPermissions(
                            this,
                            "App needs access to your camera & storage",
                            100,
                            strings
                    );
                }
                break;

            case R.id.btn_setting_account:
                Intent intent = new Intent(getActivity(), AccountActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                break;

            case R.id.fullscreen_dialog_save:
                if (checkAllField()){
                    callback.onActionClick("Whatever");
                    dismiss();
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // handles the request result
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check condition
        if (resultCode == RESULT_OK && data != null) {
            //when activity contain data
            //check condition
            if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO) {
                //when request for photo
                //Initialize array list
                images_picker = data.getParcelableArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA);
                // set image on container
                avatar.setImageURI(images_picker.get(0));
            }
        }
    }

    private void imagePicker() {
        // open picker
        FilePickerBuilder.getInstance()
                .setActivityTitle("Select Images")
                .setSpan(FilePickerConst.SPAN_TYPE.FOLDER_SPAN, 3)
                .setSpan(FilePickerConst.SPAN_TYPE.DETAIL_SPAN, 4)
                .setMaxCount(1)
                .setSelectedFiles(images_picker)
                .setActivityTheme(R.style.CustomPickerTheme)
                .pickPhoto(this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        // check condition
        if (requestCode == 100 && perms.size() == 2) {
            //when permissions are granted
            //call method
            imagePicker();
        }
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        // check condition
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            //when permisstions denied multiple time
            //Open app setting
            new AppSettingsDialog.Builder(this).build().show();
        } else {
            //when permissions deny once
            //Display toast
            Toast.makeText(getActivity(), "Permission Denied", Toast.LENGTH_SHORT).show();
        }
    }

    public interface Callback {
        void onActionClick(String name);
    }

    private boolean checkAllField() {

        if (name.getText().toString().length() == 0) {
            name.setError("This field is required");
            return false;
        }

        if (username.getText().toString().length() == 0) {
            username.setError("This field is required");
            return false;
        }

        return true;
    }
}
