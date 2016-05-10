package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wishzee.com.wishzee.R;

/**
 * Created by Ajit Gupta on 5/10/2016.
 */
public class HomeLauncherFragment extends BaseFragment implements View.OnClickListener{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);

        initialize(view);
        return view;
    }

    private void initialize(View view) {
        FloatingActionButton buttonMakeWish = (FloatingActionButton) view.findViewById(R.id.btn_make_wish);
        buttonMakeWish.setOnClickListener(this);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.home_layout;
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
            case R.id.btn_make_wish:
                fragment = new HomeLauncherFragment();
                callFragmentMethod(fragment, );
                break;
        }
    }
}
