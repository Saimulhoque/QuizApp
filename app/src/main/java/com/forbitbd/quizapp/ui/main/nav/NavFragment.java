package com.forbitbd.quizapp.ui.main.nav;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.forbitbd.quizapp.R;
import com.forbitbd.quizapp.api.ServiceGenerator;
import com.forbitbd.quizapp.model.User;
import com.forbitbd.quizapp.ui.main.MainActivity;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavFragment extends Fragment implements View.OnClickListener , NavContract.View {

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private LinearLayout rvHome,rvProfile, rvContactUs, rvLogOut;

    private ImageView ivProfile;
    private TextView tvName,tvEmail;


    private NavPresenter mPresenter;

    private MainActivity mainActivity;



    public NavFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new NavPresenter(this);

        if(getActivity() instanceof MainActivity){
            mainActivity = (MainActivity) getActivity();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nav, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {

        rvHome = view.findViewById(R.id.home);
        rvProfile = view.findViewById(R.id.profile);
        rvContactUs = view.findViewById(R.id.contact_us);
        rvLogOut = view.findViewById(R.id.logout);

        tvName = view.findViewById(R.id.name);
        tvEmail = view.findViewById(R.id.email);
        ivProfile = view.findViewById(R.id.iv_profile);


        rvHome.setOnClickListener(this);
        rvContactUs.setOnClickListener(this);
        rvLogOut.setOnClickListener(this);
        rvProfile.setOnClickListener(this);
    }

    public void setUp(DrawerLayout layout, final Toolbar toolbar) {

        mDrawerLayout = layout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);

                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        mDrawerLayout.addDrawerListener(mDrawerToggle);

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.home:
                mDrawerLayout.closeDrawer(Gravity.START);
                mPresenter.loadHomeFragment();
                break;

            case R.id.profile:
                mDrawerLayout.closeDrawer(Gravity.START);
                mPresenter.loadProfileFragment();
                break;

            case R.id.contact_us:
                mDrawerLayout.closeDrawer(Gravity.START);
                break;

            case R.id.logout:
                mDrawerLayout.closeDrawer(Gravity.START);
                mPresenter.logout();
                break;
        }
    }


    @Override
    public void renderNav(User user) {

        if(user.getName()!=null){
            tvName.setText(user.getName());
        }

        if(user.getEmail()!=null){
            tvEmail.setText(user.getEmail());
        }

        if(user.getImage()!=null && !user.getImage().equals("")){

            Log.d("HHHHH", ServiceGenerator.BASE_URL+"/"+user.getImage());

            Log.d("UUUUU",user.getImage());
            Picasso.with(getContext())
                    .load(ServiceGenerator.BASE_URL+"/"+user.getImage())
                    .into(ivProfile);

        }

    }

    @Override
    public void loadHomeFragment() {
        mainActivity.loadHomeFragment();
    }

    @Override
    public void loadProfileFragment() {
        mainActivity.loadProfileFragment();
    }

    @Override
    public void loadContactUsFragment() {

    }

    @Override
    public void logout() {
        mainActivity.logout();
    }
}
