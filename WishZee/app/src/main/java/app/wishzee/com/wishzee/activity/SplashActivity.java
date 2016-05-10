package app.wishzee.com.wishzee.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import app.wishzee.com.wishzee.R;

public class SplashActivity extends BaseActivity {

    //private RequestQueue mQueue;
    public static final String REQUEST_TAG = "SplashActivity";
   // private RecyclerView recyclerView;
   // RecentWishListAdapter mAdapter;

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(splashIntent);

                finish();
            }
        },SPLASH_TIME_OUT);

       /* recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        LinearLayoutManager layoutMangerDestination
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

       recyclerView.setLayoutManager(layoutMangerDestination);

         // set item animator to DefaultAnimator
        recyclerView.setItemAnimator(new DefaultItemAnimator());*/
    }

    @Override
    protected void onStart() {
        super.onStart();
       // String url = "http://www.intechub.com/webservice/postview.php?uid=153";
       // volleyRequestMethod(url);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_splash;
    }

    /*@Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(SplashActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(Object response) {

      *//*  WishDetailResponseObject wishDetailResponseObject = new WishDetailResponseObject();
        wishDetailResponseObject.responseParseMethod(response);

        ArrayList list = wishDetailResponseObject.getWishDetailsArrayList();

        // 3. create an adapter
        mAdapter = new RecentWishListAdapter(list, this);
        // 4. set adapter
        recyclerView.setAdapter(mAdapter);*//*

    }*/
}
