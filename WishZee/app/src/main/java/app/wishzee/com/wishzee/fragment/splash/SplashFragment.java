package app.wishzee.com.wishzee.fragment.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.fragment.base.BaseFragment;

/**
 * Created by Ajit Gupta on 5/3/2016.
 */
public class SplashFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.splash_layout;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void onNetworkConnected() {

    }
}
