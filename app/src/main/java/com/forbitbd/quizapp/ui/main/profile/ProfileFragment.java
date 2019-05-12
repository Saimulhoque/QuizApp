package com.forbitbd.quizapp.ui.main.profile;


import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.ui.main.BaseFragment;
import com.sdsmdg.tastytoast.TastyToast;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends BaseFragment implements ProfileContract.View, View.OnClickListener {

    private EditText etName, etContact,etAddress;
    private TextInputLayout tiName, tiContact,tiAddress;
    private CircleImageView ivProfile;
    private TextView tvName,tvEmail,tvBrowse;
    private Button btnUpload;

    private ProfilePresenter mPresenter;

    private boolean isUpdate;

    private User user;

    private byte[] bytes;





    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new ProfilePresenter(this, getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        tvName = view.findViewById(R.id.name);
        tvEmail = view.findViewById(R.id.email);
        tvBrowse = view.findViewById(R.id.browse);
        ivProfile = view.findViewById(R.id.iv_profile);

        etName = view.findViewById(R.id.et_name);
        etContact = view.findViewById(R.id.et_contact);
        etAddress = view.findViewById(R.id.et_address);

        tiName = view.findViewById(R.id.ti_name);
        tiContact = view.findViewById(R.id.ti_contact);
        tiAddress = view.findViewById(R.id.ti_address);

        btnUpload = view.findViewById(R.id.btn_upload);

        btnUpload.setOnClickListener(this);
        tvBrowse.setOnClickListener(this);

        mPresenter.requestForUser();

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.browse:
                mPresenter.browseClick();
                //getCameraPermission();
                break;

            case R.id.btn_upload:
                final String name = etName.getText().toString();
                final String contact = etContact.getText().toString();
                final String address = etAddress.getText().toString();

                if(user!=null){
                    user.setName(name);
                    user.setPhone(contact);
                    user.setAddress(address);
                }

                boolean valid = mPresenter.validate(user);

                if(!valid){
                    return;
                }

                if(isOnline()){
                    mPresenter.updateUser(user,bytes);
                }else{
                    TastyToast.makeText(getContext(),"Please turn on your internet connection", TastyToast.LENGTH_LONG, TastyToast.ERROR).show();
                }
                break;
        }



    }

    @Override
    public void renderUI(User user) {
        this.user = user;


        if(user.getImage()==null || user.getImage().equals("")){
            ivProfile.setVisibility(View.GONE);
            tvBrowse.setVisibility(View.VISIBLE);
        }else {
            Picasso.with(getContext())
                    .load(ServiceGenerator.BASE_URL+"/"+user.getImage())
                    .into(ivProfile);
        }

        if(user.getName()!=null){
            tvName.setText(user.getName());
            etName.setText(user.getName());
        }

        if(user.getEmail()!=null){
            tvEmail.setText(user.getEmail());
        }

        if(user.getPhone()!=null){
            etContact.setText(user.getPhone());
        }

        if(user.getAddress()!=null){
            etAddress.setText(user.getAddress());
        }
    }

    @Override
    public void updateUser(User user) {
        Toast.makeText(getContext(), "User Updated", Toast.LENGTH_SHORT).show();
        updateUI(user);
        renderUI(user);
    }

    @Override
    public void clearPreErrors() {
        tiName.setErrorEnabled(false);
        tiContact.setErrorEnabled(false);
        tiAddress.setErrorEnabled(false);
    }

    @Override
    public void showDialog() {

    }

    @Override
    public void hideDialog() {

    }

    @Override
    public void showErrorMessage(int fieldId, String message) {

        switch (fieldId){
            case 1:
                etName.requestFocus();
                tiName.setError(message);
                break;

            case 2:
                etContact.requestFocus();
                tiContact.setError(message);
                break;

            case 3:
                etAddress.requestFocus();
                tiAddress.setError(message);
                break;
        }

    }

    @Override
    public void complete(String url) {

    }

    @Override
    public void basicUpdateComplete() {

    }

    @Override
    public void openCropImageActivity() {
        cropImage();
    }


    public void setBitmap(Bitmap bitmap, byte[] bytes){
        this.bytes = bytes;
        isUpdate=true;
        //ivLogo.setImageBitmap(bitmap);
        ivProfile.setVisibility(View.VISIBLE);
        tvBrowse.setVisibility(View.GONE);
        ivProfile.setImageBitmap(bitmap);

        //Log.d("UUUUUU","Real Path "+getRealPathFromURI(file));

    }


    public String getRealPathFromURI (Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = getActivity().getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }
}
