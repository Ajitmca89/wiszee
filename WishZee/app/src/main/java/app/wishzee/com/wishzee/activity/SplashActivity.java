package app.wishzee.com.wishzee.activity;

import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import java.util.ArrayList;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.apiparsing.GetResponse;
import app.wishzee.com.wishzee.apiparsing.recentapiparsing.WishDetailResponseObject;
import app.wishzee.com.wishzee.volleycustomrequest.CustomJSONObjectRequest;
import app.wishzee.com.wishzee.volleycustomrequest.CustomVolleyRequestQueue;

public class SplashActivity extends BaseActivity implements Response.Listener, Response.ErrorListener {

    private RequestQueue mQueue;
    public static final String REQUEST_TAG = "SplashActivity";
    private RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        WishDetailResponseObject wishDetailResponseObject = new WishDetailResponseObject();

        ArrayList wishListArrayList = wishDetailResponseObject.getWishDetailsArrayList();

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);


    }

    @Override
    protected void onStart() {
        super.onStart();
        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();

        String url = "http://www.intechub.com/webservice/postview.php?uid=153";

        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method
                .GET, url,
                new JSONObject(), this, this);

        jsonRequest.setTag(REQUEST_TAG);

        mQueue.add(jsonRequest);

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
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

        new WishDetailResponseObject().responseParseMethod(response);

    }
}
