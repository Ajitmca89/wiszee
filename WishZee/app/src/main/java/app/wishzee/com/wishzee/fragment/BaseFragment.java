package app.wishzee.com.wishzee.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.HashMap;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.volleycustomrequest.CustomJSONObjectRequest;
import app.wishzee.com.wishzee.volleycustomrequest.CustomVolleyRequestQueue;


/**
 * Created by Ajit Gupta on 5/10/2016.
 */
public abstract class BaseFragment extends Fragment implements Response.Listener, Response.ErrorListener {

    private RequestQueue mQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    public void disableTouchOfBackFragment(Bundle savedInstanceState) {
        if (getView() != null && savedInstanceState == null) {
            getView().getParent().requestDisallowInterceptTouchEvent(true);
            BaseFragment.disableTouchTheft(getView());
        }
    }

    public static void disableTouchTheft(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        view.getParent().requestDisallowInterceptTouchEvent(true);
                        break;
                }
                return true;
            }
        });
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
        FragmentManager fm = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.setCustomAnimations(0, 0, 0, 0);

        fragmentTransaction.add(R.id.container, baseFragment);
        fragmentTransaction.addToBackStack(TAG);
        fragmentTransaction.commit();
    }

    protected void volleyGetRequestMethod(String url, final String REQUEST_TAG) {
        mQueue = CustomVolleyRequestQueue.getInstance(this.getActivity())
                .getRequestQueue();

        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET, url, new JSONObject(), this, this);

        jsonRequest.setTag(REQUEST_TAG);

        jsonRequest.setRetryPolicy(new DefaultRetryPolicy(5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        mQueue.add(jsonRequest);
    }


    protected void cancelQueueMethod(final String REQUEST_TAG) {
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onResponse(Object response) {
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }

    protected void showDialogMethod(String msg) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                getActivity());
        alertDialogBuilder.setTitle(msg);
        alertDialogBuilder
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}

