package app.wishzee.com.wishzee.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.fragment.BaseFragment;
import app.wishzee.com.wishzee.fragment.LoginAndSignUpFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        BaseFragment baseFragment = new LoginAndSignUpFragment();
        FragmentManager fm = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(0, 0, 0, 0);

        fragmentTransaction.add(R.id.container, baseFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }
}
