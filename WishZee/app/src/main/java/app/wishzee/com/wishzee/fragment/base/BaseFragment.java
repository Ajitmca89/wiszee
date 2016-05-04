package app.wishzee.com.wishzee.fragment.base;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import app.wishzee.com.wishzee.R;

/**
 * Created by Ajit Gupta on 5/3/2016.
 */
public abstract class BaseFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getFragmentLayout(), container, false);

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }



    protected abstract int getFragmentLayout();

    /**
     * Child fragments will return toolbar menu which it needs to inflate.
     *
     * @return int
     */
    public abstract int getToolbarMenu();

    /**
     * Fragments will override this method for all kinds of network call.
     * TODO - Will see how much I need to change this concept
     */
    public abstract void onNetworkConnected();


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        int menuResId = getToolbarMenu();
        if (menuResId != 0) {
            inflater.inflate(menuResId, menu);

        }
        super.onCreateOptionsMenu(menu, inflater);
    }

}
