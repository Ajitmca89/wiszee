package app.wishzee.com.wishzee.fragment;

import android.app.Activity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ProgressBar;

import app.wishzee.com.wishzee.R;


/**
 * Created by Ajit Gupta on 5/10/2016.
 */
public abstract class BaseFragment extends DialogFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

    /**
     * Gets a reference to the global progressbar, which is part of the view
     * for fragment activity
     *
     * @return ProgressBar
     */
    private ProgressBar findLoader() {
        if (getActivity() != null) {
            return (ProgressBar) getActivity().findViewById(R.id.loadProgress);
        } else {
            return null;
        }
    }

    /**
     * Makes global progressbar visible
     */
    public void showLoader() {
        ProgressBar progressBar = findLoader();
        if (progressBar != null) {
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Hides global progressbar by setting its visibility to GONE
     */
    public void hideLoader() {
        ProgressBar progressBar = findLoader();
        if (progressBar != null) {
            findLoader().setVisibility(View.GONE);
        }
    }


    protected void callFragmentMethod(BaseFragment baseFragment, final String TAG) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            fragmentTransaction.setCustomAnimations(R.animator.enter_anim, 0, 0, R.animator.exit_anim);
        }
        fragmentTransaction.add(R.id.container, baseFragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }
}

