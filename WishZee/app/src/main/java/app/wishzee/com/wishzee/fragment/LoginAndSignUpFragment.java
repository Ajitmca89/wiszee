package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.constant.Constants;

/**
 * Created by Ajit Gupta on 5/10/2016.
 */
public class LoginAndSignUpFragment extends BaseFragment implements View.OnClickListener {

    private RelativeLayout relLogin, relSignUp;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);

        initialize(view);

        return view;
    }

    private void initialize(View view) {

        relLogin = (RelativeLayout) view.findViewById(R.id.rel_login);
        relSignUp = (RelativeLayout) view.findViewById(R.id.rel_sign_up);
        relLogin.setOnClickListener(this);
        relSignUp.setOnClickListener(this);

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.login_sign_up_layout;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void onNetworkConnected() {

    }

    @Override
    public void onClick(View v) {
        BaseFragment baseFragment;
        switch (v.getId()) {
            case R.id.rel_login:
                baseFragment = new LoginFragment();
                callFragmentMethod(baseFragment, Constants.LOGIN_AND_SIGN_UP_TAG);
                break;
            case R.id.rel_sign_up:
                baseFragment = new SignUpFragment();
                callFragmentMethod(baseFragment, Constants.LOGIN_AND_SIGN_UP_TAG);
                break;
        }
    }

}
