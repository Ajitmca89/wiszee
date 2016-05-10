package app.wishzee.com.wishzee.activity;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.fragment.BaseFragment;
import app.wishzee.com.wishzee.fragment.LoginAndSignUpFragment;
import app.wishzee.com.wishzee.fragment.LoginFragment;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BaseFragment baseFragment = new LoginAndSignUpFragment();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.container, baseFragment);
        fragmentTransaction.commit();


    }
}
