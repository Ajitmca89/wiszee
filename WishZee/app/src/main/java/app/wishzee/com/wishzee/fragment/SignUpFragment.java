package app.wishzee.com.wishzee.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.wishzee.com.wishzee.R;
import app.wishzee.com.wishzee.constant.Constants;
import app.wishzee.com.wishzee.preference.SharedPreferencesManager;
import app.wishzee.com.wishzee.utility.Utility;

/**
 * Created by Ajit Gupta on 5/10/2016.
 */
public class SignUpFragment extends BaseFragment implements View.OnClickListener {

    private EditText etFullName, etEmail, etPassword, etPhone;
    private String genderData;
    private RequestQueue requestQueue;
    private BaseFragment fragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayout(), container, false);

        initialize(view);

        return view;
    }

    private void initialize(View view) {
        AppCompatButton buttonSignUp = (AppCompatButton) view.findViewById(R.id.btn_sign_up);
        buttonSignUp.setOnClickListener(this);
        etFullName = (EditText) view.findViewById(R.id.et_fullName);
        etEmail = (EditText) view.findViewById(R.id.et_email);
        etPassword = (EditText) view.findViewById(R.id.et_password);
        etPhone = (EditText) view.findViewById(R.id.et_phone);
        Spinner genderSpinner = (Spinner) view.findViewById(R.id.spinnerGender);
        String[] gender = getResources().getStringArray(R.array.gender_list);
        genderSpinnerAdapterMethod(gender, genderSpinner);
    }

    private void genderSpinnerAdapterMethod(String[] gender, final Spinner genderSpinner) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item, gender);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(dataAdapter);
        genderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                genderData = genderSpinner.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do Nothing
            }
        });
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.sign_up_layout;
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
            case R.id.btn_sign_up:
                registrationMethod();
                break;
        }
    }

    private void registrationMethod() {
        String fullName = etFullName.getText().toString().trim();
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();

        if (registerValidation(fullName, email, password, phone)) {
            showLoader();
            sendDataOnRegistrationApi(fullName, email, password, phone, genderData);
        }

    }

    private void sendDataOnRegistrationApi(final String fullName, final String email, final String password, final String phone, final String genderData) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.REGISTRATION_URL,
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
                params.put("username", fullName);
                params.put("email", email);
                params.put("password", password);
                params.put("phone", phone);
                params.put("gender", genderData);
                return params;
            }

        };
        stringRequest.setTag(Constants.REGISTRATION_TAG);
        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);
    }

    private void responseImplement(String response) {
        JSONObject jsonObject = null;
        String status = null, userid = null, msg = null, username = null, phone = null, gender = null, email = null;
        try {
            jsonObject = new JSONObject(response);
            if (jsonObject.has("status"))
                status = jsonObject.optString("status");
            if (jsonObject.has("userid"))
                userid = jsonObject.optString("userid");
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
        if (status.equalsIgnoreCase("true") && !status.isEmpty()) {
            fragment = new HomeLauncherFragment();
            saveDataOnPreference(username, phone, gender, email, userid);
            Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
            callFragmentMethod(fragment, Constants.REGISTRATION_TAG);
        } else {
            if (!TextUtils.isEmpty(msg)) {
                showDialogMethod(msg);
            }
        }
    }

    private void saveDataOnPreference(String username, String phone, String gender, String email, String userId) {
        SharedPreferencesManager.setUsername(getActivity(), username);
        SharedPreferencesManager.setPhone(getActivity(), phone);
        SharedPreferencesManager.setGender(getActivity(), gender);
        SharedPreferencesManager.setEmail(getActivity(), email);
        SharedPreferencesManager.setUserID(getActivity(), userId);
    }

    private boolean registerValidation(String fullName, String email, String password, String phone) {
        if (TextUtils.isEmpty(fullName)) {
            etFullName.setError("Please enter your name");
            etFullName.requestFocus();
            return false;
        } else if (! Utility.isEmailValid(email)) {
            etEmail.setError("Please enter valid Email id");
            etEmail.requestFocus();
            return false;
        } else if (password.length() < 4) {
            etPassword.setError("Please enter password more than 4 character");
            etPassword.requestFocus();
            return false;
        } else if (phone.length() != 10) {
            etPhone.setError("Please enter your 10 digit mobile number");
            etPhone.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (requestQueue != null) {
            requestQueue.cancelAll(Constants.REGISTRATION_TAG);
        }
    }
}
