package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wishzee.com.wishzee.R;

/**
 * Created by Ajit Gupta on 5/12/2016.
 */
public class MyCornerFragment extends BaseFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(),container,false);
        return view;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.my_corner_layout;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void onNetworkConnected() {

    }
}
