package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.suitebuilder.TestMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.WishDetailResponseObject;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.WishDetails;
import app.wishzee.com.wishzee.constant.Constants;
import app.wishzee.com.wishzee.preference.SharedPreferencesManager;
import app.wishzee.com.wishzee.recyclerview.RecentWishListAdapter;

/**
 * Created by Ajit Gupta on 5/12/2016.
 */
public class PublicFeedFragment extends BaseFragment {

    private RequestQueue requestQueue;
    private BaseFragment fragment;
    private RecyclerView recyclerView;
    private RecentWishListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(),container,false);
        initializeMethod(view);
        sendRequestOnPublicFeed();
        return view;
    }

    private void initializeMethod(View view) {

        recyclerView = (RecyclerView)view.findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutMangerDestination
                = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutMangerDestination);

        // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.public_feed_layout;
    }

    @Override
    public int getToolbarMenu() {
        return 0;
    }

    @Override
    public void onNetworkConnected() {
    }

    private void sendRequestOnPublicFeed() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.HOME_POST_LIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideLoader();
                        WishDetailResponseObject wishDetailResponseObject = new WishDetailResponseObject();
                        wishDetailResponseObject.responseParseMethod(response);

                        ArrayList list = wishDetailResponseObject.getWishDetailsArrayList();
                        // 3. create an adapter
                        mAdapter = new RecentWishListAdapter(list, getActivity());
                        // 4. set adapter
                        recyclerView.setAdapter(mAdapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        hideLoader();
                        Toast.makeText(getActivity(), error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("uid", SharedPreferencesManager.getUserID(getActivity()));
                params.put("startlimit", "0");
                params.put("countlimit", "10");
                return params;
            }

        };
        stringRequest.setTag(Constants.PUBLIC_FEED);
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }
}
