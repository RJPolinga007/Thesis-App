package com.bisu.herbalplantidentification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
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

public class Log_in extends AppCompatActivity {

    /*EditText accountEmail;
    ImageButton cancel1,cancel2;
    CheckBox showpassword;
    EditText accountPassword;

    Button Signup,ok_btn1,ok_btn2;
   // registration_database rd;
    ProgressBar progress1;
    TextView sign;
    ProgressDialog progressDialog;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        accountEmail = (EditText) findViewById(R.id.accountEmail);
        accountPassword = (EditText) findViewById(R.id.accountPassword);
        Signup = (Button) findViewById(R.id.Signup);
        showpassword = (CheckBox) findViewById(R.id.showpassword);
        progress1 = (ProgressBar) findViewById(R.id.progress1);
        sign = (TextView) findViewById(R.id.reg);
      //  rd = new registration_database(this);

        View alertCustomDialog = LayoutInflater.from(Log_in.this).inflate(R.layout.login_successful, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Log_in.this);

        View loginFailedDialog = LayoutInflater.from(Log_in.this).inflate(R.layout.login_failed, null);
        AlertDialog.Builder alertDialogfailed = new AlertDialog.Builder(Log_in.this);

        alertDialog.setView(alertCustomDialog);
        alertDialogfailed.setView(loginFailedDialog);

        cancel1 = (ImageButton) alertCustomDialog.findViewById(R.id.cancel1);
        ok_btn1 = (Button) alertCustomDialog.findViewById(R.id.ok_btn1);
        cancel2 = (ImageButton) loginFailedDialog.findViewById(R.id.cancel2);
        ok_btn2 = (Button) loginFailedDialog.findViewById(R.id.ok_btn2);

        progressDialog = new ProgressDialog(Log_in.this);
        progressDialog.setMessage("Logging in...");

        final AlertDialog dialog = alertDialog.create();
        final AlertDialog dialog2 = alertDialogfailed.create();

        if (SharedPrefManager.getInstance(this).isLoggedIn()){
            finish();
            startActivity(new Intent(getApplicationContext(), FishDashboard.class));
            return;
        } //check if user is logged in, if yes GO AUTOMATICALLY ON MAIN ACTIVITY

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog.cancel();
                accountEmail.setText("");
                accountPassword.setText("");
                Intent a = new Intent(Log_in.this, FishDashboard.class);
                startActivity(a);
            }
        });
        ok_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog.cancel();
                Intent a = new Intent(Log_in.this, FishDashboard.class);
                a.putExtra("email",accountEmail.getText().toString());
                accountEmail.setText("");
                accountPassword.setText("");
                startActivity(a);
            }
        });

        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent d = new Intent(Log_in.this,Sign_up.class);
                startActivity(d);
            }
        });

        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog2.cancel();
                Toast.makeText(Log_in.this, "Invalid Account Details! New User? please Register!!!", Toast.LENGTH_LONG).show();
                accountEmail.setText("");
                accountPassword.setText("");
            }
        });
        ok_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog2.cancel();
                Toast.makeText(Log_in.this, "Invalid Account Details! New User? please Register!!!", Toast.LENGTH_LONG).show();
                accountEmail.setText("");
                accountPassword.setText("");
            }
        });

        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // Show password
                    progress1.setVisibility(View.GONE);
                    accountPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    // Hide password
                    progress1.setVisibility(View.GONE);
                    accountPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });



        -----------------------------
                */

//        Signup.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userAuth();
        /*        progress1.setVisibility(View.VISIBLE);
                String mail = accountEmail.getText().toString();
                String pass = accountPassword.getText().toString();

                if (mail.isEmpty()) {
                    accountEmail.setError("Email is required");
                    progress1.setVisibility(View.GONE);
                } else if (!mail.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
                    accountEmail.setError("Invalid Email");
                    progress1.setVisibility(View.GONE);
                } else if (pass.isEmpty()) {
                    accountPassword.setError("Password is required");
                    progress1.setVisibility(View.GONE);
                } else {
                    //Start ProgressBar first (Set visibility VISIBLE)
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Starting Write and Read data with URL
                            //Creating array for parameters
                            String[] field = new String[2];
                            field[0] = "accountEmail";
                            field[1] = "accountPassword";
                            //Creating array for data
                            String[] data = new String[2];
                            data[0] = mail;
                            data[1] = pass;
                            PutData putData = new PutData("http://192.168.137.192/fishcheck/login.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    String result = putData.getResult();
                                    if(result.equals("Login Success")){
                                        progress1.setVisibility(View.GONE);
                                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        dialog.show();
                                        //             Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();

                                        //            finish();
                                    }else {
                                        progress1.setVisibility(View.GONE);
                                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                        dialog2.show();
                                        Toast.makeText(getApplicationContext(),result,Toast.LENGTH_LONG).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                    /*boolean checkEmailPassword = rd.checkUserPassword(email,pass);
                    if(checkEmailPassword == true){
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog.show();

                        login_email.setText("");
                        login_password.setText("");
                    }else {
                        dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        dialog2.show();

                        login_email.setText("");
                        login_password.setText("");

                    }
                }*/


//    ------------------------------------
          /*  }
        });
    }

    private void userAuth() {
        View alertCustomDialog = LayoutInflater.from(Log_in.this).inflate(R.layout.login_successful, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(Log_in.this);

        View loginFailedDialog = LayoutInflater.from(Log_in.this).inflate(R.layout.login_failed, null);
        AlertDialog.Builder alertDialogfailed = new AlertDialog.Builder(Log_in.this);

        alertDialog.setView(alertCustomDialog);
        alertDialogfailed.setView(loginFailedDialog);
        final AlertDialog dialog = alertDialog.create();
        final AlertDialog dialog2 = alertDialogfailed.create();
        cancel1 = (ImageButton) alertCustomDialog.findViewById(R.id.cancel1);
        ok_btn1 = (Button) alertCustomDialog.findViewById(R.id.ok_btn1);
        cancel2 = (ImageButton) loginFailedDialog.findViewById(R.id.cancel2);
        ok_btn2 = (Button) loginFailedDialog.findViewById(R.id.ok_btn2);
        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog.cancel();
                accountEmail.setText("");
                accountPassword.setText("");
                startActivity(new Intent(getApplicationContext(), FishDashboard.class));
             //   Intent a = new Intent(Log_in.this, FishDashboard.class);
             //   startActivity(a);
            }
        });
        ok_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog.cancel();
            //    Intent a = new Intent(Log_in.this, FishDashboard.class);
           //     a.putExtra("email",accountEmail.getText().toString());
                accountEmail.setText("");
                accountPassword.setText("");
            //    startActivity(a);
                startActivity(new Intent(getApplicationContext(), loginsplash.class));
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog2.cancel();
                Toast.makeText(Log_in.this, "Invalid Account Details! New User? please Register!!!", Toast.LENGTH_LONG).show();
                accountEmail.setText("");
                accountPassword.setText("");
            }
        });
        ok_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress1.setVisibility(View.GONE);
                dialog2.cancel();
                Toast.makeText(Log_in.this, "Invalid Account Details! New User? please Register!!!", Toast.LENGTH_LONG).show();
                accountEmail.setText("");
                accountPassword.setText("");
            }
        });
        final String email = accountEmail.getText().toString().trim();
        final String password = accountPassword.getText().toString().trim();
        if (email.isEmpty()) {
            accountEmail.setError("Email is required");
            progress1.setVisibility(View.GONE);
        } else if (!email.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
            accountEmail.setError("Invalid Email");
            progress1.setVisibility(View.GONE);
        } else if (password.isEmpty()) {
            accountPassword.setError("Password is required");
            progress1.setVisibility(View.GONE);
        } else {
            progressDialog.show();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_LOGIN,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            progressDialog.dismiss();
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("error")) {
                                    Toast.makeText(Log_in.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    SharedPrefManager.getInstance(getApplicationContext()).userLogin(
                                            jsonObject.getInt("id"),
                                            jsonObject.getString("accountUsername"),
                                            jsonObject.getString("accountEmail"),
                                            jsonObject.getString("accountPassword")

                                    );
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog.show();
                              //      startActivity(new Intent(getApplicationContext(), FishDashboard.class));
                                } else {
                                    Toast.makeText(Log_in.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog2.show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();

                                Toast.makeText(Log_in.this, "Some error occurred", Toast.LENGTH_SHORT).show();

                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(Log_in.this, "Error occurred: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("accountEmail", email);
                    params.put("accountPassword", password);
                    return params;
                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }



    @SuppressLint("MissingSuperCall")
    public void onBackPressed() {
        // Create the alert dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?");

        // Set the positive button (OK button) and its click listener
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Finish the activity
                finish();
                // Add any additional transitions or logic here
            }
        });

        // Set the negative button (Cancel button) and its click listener
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Dismiss the dialog and stay in the activity
                dialog.dismiss();
            }
        });

        // Create the alert dialog
        AlertDialog alertDialog = builder.create();

        // Show the alert dialog
        alertDialog.show();
    }
    */

}