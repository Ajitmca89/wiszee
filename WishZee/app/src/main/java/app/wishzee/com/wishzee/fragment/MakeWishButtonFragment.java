package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import app.wishzee.com.wishzee.R;

/**
 * Created by Ajit Gupta on 5/10/2016.
 */
public class MakeWishButtonFragment extends BaseFragment implements View.OnClickListener {

    private Boolean isFabOpen = false;
    private FloatingActionButton fab,fab7,fab8,fab9;
    private Animation fab_open,fab_close,rotate_forward,rotate_backward;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(),container,false);
        fab = (FloatingActionButton)view.findViewById(R.id.fab6);
        fab7 = (FloatingActionButton)view.findViewById(R.id.fab7);
        fab8 = (FloatingActionButton)view.findViewById(R.id.fab8);
        fab9 = (FloatingActionButton)view.findViewById(R.id.fab9);
        fab_open = AnimationUtils.loadAnimation(getActivity(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getActivity(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getActivity(),R.anim.rotate_backward);
        fab.setOnClickListener(this);
        fab7.setOnClickListener(this);
        fab8.setOnClickListener(this);
        fab9.setOnClickListener(this);
        return view;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.make_wish_layout;
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
        int id = v.getId();
        switch (id){
            case R.id.fab6:
                animateFAB();
                break;
            case R.id.fab7:

                Log.d("Raj", "Fab 7");
                break;
            case R.id.fab8:

                Log.d("Raj", "Fab 8");
                break;
            case R.id.fab9:

                Log.d("Raj", "Fab 9");
                break;
        }
    }

    public void animateFAB(){

        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab7.startAnimation(fab_close);
            fab8.startAnimation(fab_close);
            fab9.startAnimation(fab_close);
            fab7.setClickable(false);
            fab8.setClickable(false);
            fab9.setClickable(false);
            isFabOpen = false;
            Log.d("Raj", "close");

        } else {

            fab.startAnimation(rotate_forward);
            fab7.startAnimation(fab_open);
            fab8.startAnimation(fab_open);
            fab9.startAnimation(fab_open);
            fab7.setClickable(true);
            fab8.setClickable(true);
            fab9.setClickable(true);
            isFabOpen = true;
            Log.d("Raj","open");

        }
    }
}
