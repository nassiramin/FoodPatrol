package com.example.anas.foodpatrol.module.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anas.foodpatrol.R;
import com.example.anas.foodpatrol.activity.HomeActivity;
import com.example.anas.foodpatrol.activity.HomesActivity;
import com.example.anas.foodpatrol.activity.Register;

/**
 * Created by Anas on 07/07/2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginView{
//    Intent intent;
//    int success;
//    private String url = Server.URL + "LoginPresenter.php";
//    private static final String TAG = LoginActivity.class.getSimpleName();
//    private static final String TAG_SUCCESS = "success";
//    private static final String TAG_MESSAGE = "message";
//    String tag_json_obj = "json_obj_req";

    public final static String TAG_USERNAME = "username";
    public final static String TAG_ID = "id";

    private ProgressDialog pDialog;
    private Button btnRegister, btnLogin;
    private EditText txtUsername, txtPassword;

    private LoginPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_signin);

        initView();
        initPresenter();
    }

    private void initView(){
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnRegister = (Button) findViewById(R.id.btn_register);
        txtUsername = (EditText) findViewById(R.id.txt_username);
        txtPassword = (EditText) findViewById(R.id.txt_password);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        pDialog.setMessage("Logging in ...");

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();

                mPresenter.login(username, password, LoginActivity.this);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent iRegister = new Intent(LoginActivity.this, Register.class);
                finish();
                startActivity(iRegister);
            }
        });
    }

    private void initPresenter(){
        mPresenter = new LoginPresenter(this);
    }

    @Override
    public void onProgress() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    @Override
    public void onFinished() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onError(String msg) {
        onFinished();
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess() {
        onFinished();

        Intent intent = new Intent(LoginActivity.this, HomesActivity.class);
        intent.putExtra(TAG_ID, 1);
        intent.putExtra(TAG_USERNAME, txtUsername.getText().toString());
        startActivity(intent);
        finish();
    }


    private void checkLogin(final String username, final String password) {

        // Memanggil main activity
//        Intent intent = new Intent(LoginActivity.this,
//                HomesActivity.class);
//        intent.putExtra(TAG_ID, 1);
//        intent.putExtra(TAG_USERNAME, username);
//        finish();
//        startActivity(intent);

//        pDialog = new ProgressDialog(this);
//        pDialog.setCancelable(false);
//        pDialog.setMessage("Logging in ...");
//        showDialog();
//        StringRequest strReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//
//            @Override
//            public void onResponse(String response) {
//                Log.e(TAG, "LoginActivity Response: " + response.toString());
//                hideDialog();
//
//                try {
//                    JSONObject jObj = new JSONObject(response);
//                    success = jObj.getInt(TAG_SUCCESS);
//
//                    // Check for error node in json
//                    if (success == 1) {
//                        String username = jObj.getString(TAG_USERNAME);
//                        String id = jObj.getString(TAG_ID);
//
//                        Log.e("Successfully LoginActivity!", jObj.toString());
//
//                        Toast.makeText(getApplicationContext(),
//                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//
//                        // Memanggil main activity
//                        Intent intent = new Intent(LoginActivity.this,
//                                HomesActivity.class);
//                        intent.putExtra(TAG_ID, id);
//                        intent.putExtra(TAG_USERNAME, username);
//                        finish();
//                        startActivity(intent);
//                    } else {
//                        Toast.makeText(getApplicationContext(),
//                                jObj.getString(TAG_MESSAGE), Toast.LENGTH_LONG).show();
//
//                    }
//                } catch (JSONException e) {
//                    // JSON error
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(TAG, "LoginActivity Error: " + error.getMessage());
//                Toast.makeText(getApplicationContext(),
//                        error.getMessage(), Toast.LENGTH_LONG).show();
//
//                hideDialog();
//
//            }
//        }) {
//
//            @Override
//            protected Map<String, String> getParams() {
//                // Posting parameters to LoginActivity url
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("username", username);
//                params.put("password", password);
//
//                return params;
//            }
//
//        };
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
}

