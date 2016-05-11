package app.wishzee.com.wishzee.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.constant.Constants;
import app.wishzee.com.wishzee.preference.SharedPreferencesManager;

/**
 * Created by Ajit Gupta on 5/9/2016.
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private RequestQueue requestQueue;
    private EditText etUsername;
    private EditText etPassword;
    private BaseFragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        AppCompatButton buttonLogin = (AppCompatButton) view.findViewById(R.id.btn_login);
        AppCompatButton buttonSignUp = (AppCompatButton) view.findViewById(R.id.btn_sign_up);
        TextView txtForgotPass = (TextView) view.findViewById(R.id.forgot_password);
        buttonLogin.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        txtForgotPass.setOnClickListener(this);
        etUsername = (EditText) view.findViewById(R.id.et_username);
        etPassword = (EditText) view.findViewById(R.id.et_password);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.login_layout;
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

        switch (v.getId()) {
            case R.id.btn_login:
                loginMethod();
                break;

            case R.id.btn_sign_up:
                fragment = new SignUpFragment();
                callFragmentMethod(fragment, Constants.LOGINTAG);
                break;

            case R.id.forgot_password:
                fragment = new ForgotPasswordFragment();
                callFragmentMethod(fragment, Constants.LOGINTAG);
                break;
        }
    }

    private void loginMethod() {
        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

        if (validation(username, password)) {
            showLoader();
            sendDataOnLoginApi(username, password);
        }

    }

    private void sendDataOnLoginApi(final String username, final String password) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        hideLoader();
                        responseImplement(response);
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
                params.put("username", username);
                params.put("password", password);
                return params;
            }

        };
        stringRequest.setTag(Constants.LOGINTAG);
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void responseImplement(String response) {
        JSONObject jsonObject = null;
        String status = null, id = null, socialId = null, msg = null, username = null, phone = null, gender = null, email = null;
        try {
            jsonObject = new JSONObject(response);
            if (jsonObject.has("status"))
                status = jsonObject.optString("status");
            if (jsonObject.has("id"))
                id = jsonObject.optString("id");
            if (jsonObject.has("socialid"))
                socialId = jsonObject.optString("socialid");
            if (jsonObject.has("msg"))
                msg = jsonObject.optString("msg");
            if (jsonObject.has("username"))
                username = jsonObject.optString("username");
            if (jsonObject.has("email"))
                email = jsonObject.optString("email");
            if (jsonObject.has("phone"))
                phone = jsonObject.optString("phone");
            if (jsonObject.has("gender"))
                gender = jsonObject.optString("gender");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (status.equalsIgnoreCase("true")) {
            fragment = new HomeLauncherFragment();
            saveDataOnPreference(username, phone, gender, email, socialId, id);
            callFragmentMethod(fragment, Constants.LOGINTAG);
        } else {
            if (!TextUtils.isEmpty(msg)) {
                showDialogMethod(msg);
            }
        }
    }


    private void saveDataOnPreference(String username, String phone, String gender, String email, String socialId, String id) {
        SharedPreferencesManager.setUsername(getActivity(), username);
        SharedPreferencesManager.setPhone(getActivity(), phone);
        SharedPreferencesManager.setGender(getActivity(), gender);
        SharedPreferencesManager.setEmail(getActivity(), email);
        SharedPreferencesManager.setSocialID(getActivity(), socialId);
        SharedPreferencesManager.setID(getActivity(), id);
    }

    private boolean validation(String username, String password) {
        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Please enter user name..");
            etUsername.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            etPassword.setError("Please enter Password.");
            etPassword.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(Constants.LOGINTAG);
        }
    }
}
