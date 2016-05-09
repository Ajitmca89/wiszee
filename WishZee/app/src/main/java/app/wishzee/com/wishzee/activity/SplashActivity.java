package app.wishzee.com.wishzee.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;


import java.util.ArrayList;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.WishDetailResponseObject;
import app.wishzee.com.wishzee.recyclerview.RecentWishListAdapter;

public class SplashActivity extends BaseActivity {

    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "SplashActivity";
    private RecyclerView recyclerView;
    RecentWishListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutMangerDestination
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

       recyclerView.setLayoutManager(layoutMangerDestination);

         // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    protected void onStart() {
        super.onStart();
        String url = "http://www.intechub.com/webservice/postview.php?uid=153";
        volleyRequestMethod(url);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.splash_layout;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {

        WishDetailResponseObject wishDetailResponseObject = new WishDetailResponseObject();
        wishDetailResponseObject.responseParseMethod(response);

        ArrayList list = wishDetailResponseObject.getWishDetailsArrayList();

        // 3. create an adapter
        mAdapter = new RecentWishListAdapter(list, this);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);

    }
}
