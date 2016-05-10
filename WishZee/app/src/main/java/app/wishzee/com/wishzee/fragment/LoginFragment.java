package app.wishzee.com.wishzee.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.wishzee.com.wishzee.R;

/**
 * Created by Ajit Gupta on 5/9/2016.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private final static String LOGINTAG = "LoginFragment";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        AppCompatButton buttonLogin = (AppCompatButton) view.findViewById(R.id.btn_login);
        AppCompatButton buttonSignUp = (AppCompatButton) view.findViewById(R.id.btn_sign_up);
        TextView txtForgotPass = (TextView) view.findViewById(R.id.forgot_password);
        buttonLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        txtForgotPass.setOnClickListener(this);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.login_layout;
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
        BaseFragment fragment;
        switch (v.getId()){
            case R.id.btn_login:
                fragment = new HomeLauncherFragment();
                callFragmentMethod(fragment,LOGINTAG);
                break;
            case R.id.btn_sign_up:
                fragment = new SignUpFragment();
                callFragmentMethod(fragment,LOGINTAG);
                break;
            case R.id.forgot_password:
                fragment = new ForgotPasswordFragment();
                callFragmentMethod(fragment, LOGINTAG);
                break;
        }
    }
}
