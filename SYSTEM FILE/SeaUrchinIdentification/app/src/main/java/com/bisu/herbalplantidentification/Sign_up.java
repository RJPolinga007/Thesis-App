package com.bisu.herbalplantidentification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Sign_up extends AppCompatActivity {
    EditText accountPassword, accountEmail;
    EditText accountUsername;
    EditText repassword;
    ImageButton cancelId;
    Button ok_btn;
    Button register;
    //  registration_database rd;
    ProgressBar progress;
    TextView log;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        accountUsername = (EditText) findViewById(R.id.accountUsername);
        accountPassword = (EditText) findViewById(R.id.accountPassword);
        accountEmail = (EditText) findViewById(R.id.accountEmail);
        repassword = (EditText) findViewById(R.id.repassword);
        register = (Button) findViewById(R.id.Signup);
        progressDialog = new ProgressDialog(this);
        progress = (ProgressBar) findViewById(R.id.progress);
        log = (TextView) findViewById(R.id.log);
        //     rd = new registration_database(this);

        View alertCustomDialog = LayoutInflater.from(Sign_up.this).inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Sign_up.this);

        alertDialog.setView(alertCustomDialog);
        cancelId = (ImageButton) alertCustomDialog.findViewById(R.id.cancel);
        ok_btn = (Button) alertCustomDialog.findViewById(R.id.ok_btn);

        final AlertDialog dialog = alertDialog.create();

        cancelId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.GONE);
                dialog.cancel();
                Toast.makeText(Sign_up.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                Intent a = new Intent(Sign_up.this, Log_in.class);
                startActivity(a);
                accountUsername.setText("");
                accountEmail.setText("");
                accountPassword.setText("");
                repassword.setText("");
            }
        });

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent e = new Intent(Sign_up.this, Log_in.class);
                startActivity(e);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                registerUser();
        /*        progress.setVisibility(View.VISIBLE);
                String user = accountUsername.getText().toString();
                String mail = accountEmail.getText().toString();
                String pass = accountPassword.getText().toString();
                String repass = repassword.getText().toString();

                accountUsername.setError(null);
                accountEmail.setError(null);
                accountPassword.setError(null);
                repassword.setError(null);

// Check the input and set error messages if necessary
                if (user.isEmpty()) {
                    accountUsername.setError("Username is required");
                    progress.setVisibility(View.GONE);
                } else if (!mail.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
                    accountEmail.setError("Invalid Email. Only Gmail addresses are allowed");
                    progress.setVisibility(View.GONE);
                } else if (pass.isEmpty()) {
                    accountPassword.setError("Password is required");
                    progress.setVisibility(View.GONE);
                } else if (repass.isEmpty()) {
                    repassword.setError("Please re-enter the password");
                    progress.setVisibility(View.GONE);
                } else if (!pass.equals(repass)) {
                    repassword.setError("Passwords do not match");
                    progress.setVisibility(View.GONE);
                } else {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[3];
                            field[0] = "accountUsername";
                            field[1] = "accountEmail";
                            field[2] = "accountPassword";
                            //Creating array for data
                            String[] data = new String[3];
                            data[0] = user;
                            data[1] = mail;
                            data[2] = pass;
                            PutData putData = new PutData("http://192.168.137.192/fishcheck/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Sign Up Success")){
                                        progress.setVisibility(View.GONE);
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        dialog.show();
                                        //             Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                                        //            finish();
                                    }else {
                                        progress.setVisibility(View.GONE);
                                        Toast.makeText(Sign_up.this,"Email Already Exist",Toast.LENGTH_LONG).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                } else {
                                    progress.setVisibility(View.GONE);
                                    Toast.makeText(Sign_up.this,"Failed to Create account",Toast.LENGTH_LONG).show();

                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                    //   if (rd.insertAccount(accountUsername.getText().toString(), accountEmail.getText().toString(), accountPassword.getText().toString())) {
                    //        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    //       dialog.show();
                    //       accountUsername.setText("");
                    //      accountEmail.setText("");
                    //      accountPassword.setText("");
                    //       repassword.setText("");
                    //  } else {
                    //      Toast.makeText(MainActivity2.this, "FAILED TO ADD!!", Toast.LENGTH_LONG).show();
                    //  }
                }*/
            }
        });
    }

    private void registerUser() {
        View alertCustomDialog = LayoutInflater.from(Sign_up.this).inflate(R.layout.custom_dialog, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Sign_up.this);

        alertDialog.setView(alertCustomDialog);
        cancelId = (ImageButton) alertCustomDialog.findViewById(R.id.cancel);
        ok_btn = (Button) alertCustomDialog.findViewById(R.id.ok_btn);

        final AlertDialog dialog = alertDialog.create();

        cancelId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.GONE);
                dialog.cancel();
            }
        });
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.GONE);
                dialog.cancel();
                Toast.makeText(Sign_up.this, "Account Created Successfully!", Toast.LENGTH_LONG).show();
                Intent a = new Intent(Sign_up.this, Log_in.class);
                startActivity(a);
                accountUsername.setText("");
                accountEmail.setText("");
                accountPassword.setText("");
                repassword.setText("");
            }
        });
        final String user = accountUsername.getText().toString().trim();
        final String mail = accountEmail.getText().toString().trim();
        final String pass = accountPassword.getText().toString().trim();
        final String repass = repassword.getText().toString().trim();

        accountUsername.setError(null);
        accountEmail.setError(null);
        accountPassword.setError(null);
        repassword.setError(null);

// Check the input and set error messages if necessary
        if (user.isEmpty()) {
            accountUsername.setError("Username is required");
            progress.setVisibility(View.GONE);
        } else if (!mail.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
            accountEmail.setError("Invalid Email. Only Gmail addresses are allowed");
            progress.setVisibility(View.GONE);
        } else if (pass.isEmpty()) {
            accountPassword.setError("Password is required");
            progress.setVisibility(View.GONE);
        } else if (repass.isEmpty()) {
            repassword.setError("Please re-enter the password");
            progress.setVisibility(View.GONE);
        } else if (!pass.equals(repass)) {
            repassword.setError("Passwords do not match");
            progress.setVisibility(View.GONE);
        } else {
            progressDialog.setMessage("Registering user....");
            progressDialog.show();
            StringRequest stringRequest = new StringRequest(Request.Method.POST,
                    Constants.URL_REGISTER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();

                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                dialog.show();
                                Toast.makeText(getApplicationContext(),jsonObject.getString("message"), Toast.LENGTH_LONG).show();

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }){

                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("accountUsername",user);
                    params.put("accountEmail",mail);
                    params.put("accountPassword",pass);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }
}