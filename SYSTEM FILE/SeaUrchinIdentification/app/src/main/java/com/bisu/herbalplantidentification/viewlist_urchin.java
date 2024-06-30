package com.bisu.herbalplantidentification;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class viewlist_urchin extends AppCompatActivity {
    private List<HashMap<String, String>> data;
    ListView simpleList;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlist_urchin);

        simpleList = findViewById(R.id.lview);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait.....");
        progressDialog.setCanceledOnTouchOutside(false);

        progressDialog.show();
        data = new ArrayList<>();


        // Fetch data on a background thread
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // Replace with your server URL and script name
                    String url = Constants.URL_GET_URCHIN_LIST;
                    URL requestUrl = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    // Check for successful response
                    if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                        // Read response as JSON
                        InputStream inputStream = connection.getInputStream();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                        StringBuilder response = new StringBuilder();
                        String line;
                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        // Parse JSON response and create HashMaps
                        JSONArray jsonArray = new JSONArray(response.toString());
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            HashMap<String, String> map = new HashMap<>();

                            map.put("id", jsonObject.getString("name_id_urchin"));
                            map.put("name_urchin", jsonObject.getString("name_urchin"));
                            map.put("scientific_name", jsonObject.getString("scientific_name"));
                            map.put("local_name", jsonObject.getString("local_name"));
                            map.put("description_urchin", jsonObject.getString("description_urchin"));
                            map.put("status", jsonObject.getString("status"));
                            data.add(map);
                        }

                        // Update list view on UI thread
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Create adapter and set it to list view
                                SimpleAdapter adapter = new SimpleAdapter(viewlist_urchin.this, data,
                                        R.layout.user_urchin_listview, new String[]{"name_urchin", "scientific_name", "local_name", "description_urchin", "status"},
                                        new int[]{R.id.urchinNameTV, R.id.scientific_nameTV, R.id.local_nameTV, R.id.urchinDescription, R.id.statusTV});
                                simpleList.setAdapter(adapter);
                                progressDialog.hide();

                                // Add click listener to list view items
                                simpleList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        // Get the clicked item
                                        HashMap<String, String> item = (HashMap<String, String>) parent.getItemAtPosition(position);

                                        // Create intent to start ViewUrchinDetailsActivity
                                        Intent intent = new Intent(viewlist_urchin.this, ViewUrchinDetailsActivity.class);

                                        // Pass data to the next activity
                                        intent.putExtra("urchin_id", item.get("id"));
                                        intent.putExtra("name_urchin", item.get("name_urchin"));
                                        intent.putExtra("scientific_name", item.get("scientific_name"));
                                        intent.putExtra("local_name", item.get("local_name"));
                                        intent.putExtra("description_urchin", item.get("description_urchin"));
                                        intent.putExtra("status", item.get("status"));
                                        // Start the activity
                                        startActivity(intent);
                                    }
                                });
                            }
                        });
                    } else {
                        // Handle error
                        Log.e("Error", "Failed to fetch data: " + connection.getResponseCode());
                    }
                } catch (Exception e) {
                    // Handle network and parsing errors
                    Log.e("Error", e.getMessage());
                }
            }
        }).start();
    }


    public void clickToBack(View view) {
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }


}
