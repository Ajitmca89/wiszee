package app.wishzee.com.wishzee.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONObject;

import app.wishzee.com.wishzee.volleycustomrequest.CustomJSONObjectRequest;
import app.wishzee.com.wishzee.volleycustomrequest.CustomVolleyRequestQueue;

/**
 * Created by Ajit Gupta on 5/3/2016.
 */
public abstract class BaseActivity extends AppCompatActivity implements Response.Listener, Response.ErrorListener {

    private RequestQueue mQueue;

    public static final String REQUEST_TAG = "SplashActivity";

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutResourceId());
    }

    protected abstract int getLayoutResourceId();

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mQueue != null) {
            mQueue.cancelAll(REQUEST_TAG);
        }
    }

    protected void volleyRequestMethod(String url) {
        mQueue = CustomVolleyRequestQueue.getInstance(this.getApplicationContext())
                .getRequestQueue();

        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET, url, new JSONObject(), this, this);

        jsonRequest.setTag(REQUEST_TAG);

        mQueue.add(jsonRequest);
    }

    @Override
    public void onResponse(Object response) {
    }

    @Override
    public void onErrorResponse(VolleyError error) {
    }
}
