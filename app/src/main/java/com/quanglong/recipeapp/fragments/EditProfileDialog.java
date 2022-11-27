package com.quanglong.recipeapp.fragments;

import static android.app.Activity.RESULT_OK;
import static com.quanglong.recipeapp.utilities.BindingAdapter.setImageURL;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Base64;
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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.quanglong.recipeapp.R;
import com.quanglong.recipeapp.activities.AccountActivity;
import com.quanglong.recipeapp.model.EditProfileRequest;
import com.quanglong.recipeapp.responses.UserLoginResponse;
import com.quanglong.recipeapp.utilities.Base64Config;
import com.quanglong.recipeapp.viewmodels.CategoryViewModel;
import com.quanglong.recipeapp.viewmodels.UserViewModel;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
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
    private UserViewModel userViewModel;

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
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        this.avatar = view.findViewById(R.id.profile_image);
        this.name = view.findViewById(R.id.itemName);
        this.username = view.findViewById(R.id.itemUsername);
        this.description = view.findViewById(R.id.itemDescription);
        this.edit_avatar = view.findViewById(R.id.edit_avatar);
        this.userLocalDatabase = getActivity().getSharedPreferences("userDetails", 0);

        setUserInfo();

        close.setOnClickListener(this);
        action.setOnClickListener(this);
        btn_sett_acc.setOnClickListener(this);
        edit_avatar.setOnClickListener(this);

        return view;
    }

    private void setUserInfo() {
        userViewModel.userDetail(userLocalDatabase.getInt("id", -1),userLocalDatabase.getInt("id", -1) ).observe(getViewLifecycleOwner(), new Observer<UserLoginResponse>() {
                    @Override
                    public void onChanged(UserLoginResponse userLoginResponse) {
                        if (userLoginResponse != null){
                            if (!userLoginResponse.getAvatar().equals("")){
                                setImageURL(avatar, userLoginResponse.getAvatar());
                            }else{
                                avatar.setImageResource(R.drawable.avater_default);
                            }
                            name.setText(userLoginResponse.getDisplayName());
                            username.setText(userLoginResponse.getUserName());
                            description.setText(userLoginResponse.getDescription());
                        }
                    }
                }
        );
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
                    EditProfileRequest request = new EditProfileRequest();
                    request.setId(userLocalDatabase.getInt("id", -1));
                    request.setUserName(username.getText().toString());
                    request.setDisplayName(name.getText().toString());
                    request.setSex(0);
                    request.setDescription(description.getText().toString());


                    if (images_picker.size() > 0){
                        try {
                            Uri filepath = images_picker.get(0);
                            InputStream inputStream = getActivity().getContentResolver().openInputStream(filepath);
                            bitmap = BitmapFactory.decodeStream(inputStream);

                            request.setImageInput(Base64Config.Base64Split(encodeBitmapImage(bitmap)));
                        } catch (Exception ex) {
                        }
                    }else{
                        request.setImageInput(new ArrayList<>());
                    }

                    userViewModel.editProfile(request).observe(getViewLifecycleOwner(), new Observer<String>() {
                        @Override
                        public void onChanged(String s) {
                            if (s.equals("Success!")){
                                callback.onActionClick("Edit profile successfully!");
                            }else{
                                callback.onActionClick("Edit profile failed!");
                            }
                            dismiss();
                        }
                    });
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

    private String encodeBitmapImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] bytesofimage = byteArrayOutputStream.toByteArray();
        return android.util.Base64.encodeToString(bytesofimage, Base64.NO_WRAP);
    }
}
