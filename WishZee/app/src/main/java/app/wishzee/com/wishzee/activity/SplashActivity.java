package app.wishzee.com.wishzee.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.fragment.base.BaseFragment;
import app.wishzee.com.wishzee.fragment.splash.SplashFragment;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        BaseFragment splashFragment = new SplashFragment();

        FragmentManager fragmentManager = getFragmentManager();
        android.app.FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();

        fragmentTransaction.replace(R.id.frameContainer, splashFragment);
        fragmentTransaction.commit();

    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_container;
    }
}
