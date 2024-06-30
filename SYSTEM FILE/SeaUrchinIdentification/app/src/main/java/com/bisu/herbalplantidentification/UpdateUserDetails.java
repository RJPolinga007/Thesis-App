package com.bisu.herbalplantidentification;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateUserDetails extends AppCompatActivity {
   /* private EditText editTextNewUsername, editTextNewEmail, editTextNewPassword;
    TextView editTextUserId;
    ImageView backtomenu;
    private Button buttonUpdate;
    ImageButton cancel2, cancel1;
    Button ok_btn2,ok_btn1;
    ProgressBar progress;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user_details);

        editTextUserId = (TextView)findViewById(R.id.id);
        editTextNewUsername = (EditText) findViewById(R.id.user);
        backtomenu = (ImageView) findViewById(R.id.backTomenu);
        editTextNewEmail = (EditText)findViewById(R.id.email);
        editTextNewPassword = (EditText)findViewById(R.id.pass);
        buttonUpdate = (Button) findViewById(R.id.buttonGenerateOTP);
        if (!SharedPrefManager.getInstance(this).isLoggedIn()) {
            startActivity(new Intent(getApplicationContext(), Log_in.class));
            finish();
        }

        int userId = SharedPrefManager.getInstance(this).getId();
        String userEmail = SharedPrefManager.getInstance(getApplicationContext()).getEmail();
        String userIdString = String.valueOf(userId);
        editTextUserId.setText(userIdString);
        editTextNewUsername.setText(SharedPrefManager.getInstance(this).getUsername());
        editTextNewEmail.setText(SharedPrefManager.getInstance(this).getPass());

        backtomenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(UpdateUserDetails.this,aboutapp.class);
                startActivity(a);
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateInfo();
            }
        });

    }

    private void updateInfo() {
        View alertCustomDialog = LayoutInflater.from(UpdateUserDetails.this).inflate(R.layout.updatedsuccessfully, null);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(UpdateUserDetails.this);

        View updateFailedDialog = LayoutInflater.from(UpdateUserDetails.this).inflate(R.layout.updatedfailed, null);
        AlertDialog.Builder alertDialogfailed = new AlertDialog.Builder(UpdateUserDetails.this);

        alertDialog.setView(alertCustomDialog);
        alertDialogfailed.setView(updateFailedDialog);

        cancel1 = (ImageButton) alertCustomDialog.findViewById(R.id.cancel1);
        ok_btn1 = (Button) alertCustomDialog.findViewById(R.id.ok_btn1);
        cancel2 = (ImageButton) updateFailedDialog.findViewById(R.id.cancel2);
        ok_btn2 = (Button) updateFailedDialog.findViewById(R.id.ok_btn2);

        final AlertDialog dialog = alertDialog.create();
        final AlertDialog dialog2 = alertDialogfailed.create();

        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        ok_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
                Toast.makeText(UpdateUserDetails.this, "Account Updated Successfully!", Toast.LENGTH_LONG).show();
                Intent a = new Intent(UpdateUserDetails.this, aboutapp.class);
                startActivity(a);
            }
        });
        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.cancel();
            }
        });
        ok_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.cancel();
                Toast.makeText(UpdateUserDetails.this, "Invalid Account Details!!", Toast.LENGTH_LONG).show();
            }
        });

        int id = SharedPrefManager.getInstance(this).getId();
        String username = editTextNewUsername.getText().toString().trim();
        String email = editTextNewEmail.getText().toString().trim();
        String password = editTextNewPassword.getText().toString().trim();

        editTextNewUsername.setError(null);
        editTextNewEmail.setError(null);
        editTextNewPassword.setError(null);

        if (username.isEmpty()) {
            editTextNewUsername.setError("Email is required");
        } else if (!email.matches("[a-zA-Z0-9._%+-]+@gmail\\.com")) {
            editTextNewEmail.setError("Invalid Email");
        } else if (password.isEmpty()) {
            editTextNewPassword.setError("Password is required");
        } else {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_UPDATE_INFO_USER,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if (!jsonObject.getBoolean("error")) {
                                    //success
                                    //display success message
                                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog.show();
                                    Toast.makeText(UpdateUserDetails.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                } else {
                                    //error
                                    //display error message
                                    dialog2.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                    dialog2.show();
                                    Toast.makeText(UpdateUserDetails.this, jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(UpdateUserDetails.this, "Connection Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();

                    params.put("id", String.valueOf(id));
                    params.put("username", username);
                    params.put("email", email);
                    params.put("password", password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }
    }

    */
}
