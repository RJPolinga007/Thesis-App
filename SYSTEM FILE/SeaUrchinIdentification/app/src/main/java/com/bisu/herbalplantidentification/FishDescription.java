package com.bisu.herbalplantidentification;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FishDescription extends AppCompatActivity {

   /* ImageView fishImages;
    ImageButton bac;
    TextView fishNames,fishDescription;
    private OkHttpClient client;
    private Response response;
    private RequestBody requestBody;
    private Request request;
    private String strJson, apiUrl = "http://192.168.254.101/fishcheck/v1/fetch_fish1.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_description);

        fishImages = (ImageView) findViewById(R.id.fishImages1);
        fishNames = (TextView) findViewById(R.id.fishNames1);
        bac = (ImageButton) findViewById(R.id.bac);
        fishDescription = (TextView) findViewById(R.id.fishDescriptions1);
        client = new OkHttpClient();
        new GetUserDataRequest().execute();

        bac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FishDescription.this,descriptionList.class);
                startActivity(intent);
            }
        });
        //  Intent intent = getIntent();
       // if (intent != null) {
        //    String itemName = intent.getStringExtra("NAME");
        //    String itemDescription = intent.getStringExtra("DESCRIPTION");
        //    int imageResource = intent.getIntExtra("IMAGE", 0);

            // Set the data to the TextViews and ImageView
        //    fishNames.setText(itemName);
        //    fishDescription.setText(itemDescription);
      //      fishImages.setImageResource(imageResource);

     //   }
    }

    public class GetUserDataRequest extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... voids) {
            request = new Request.Builder().url(apiUrl).build();
            try {
                response = client.newCall(request).execute();
                if (response.isSuccessful()) {
                    InputStream inputStream = response.body().byteStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder stringBuilder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        stringBuilder.append(line);
                    }
                    inputStream.close();
                    return stringBuilder.toString();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String strJson) {
            super.onPostExecute(strJson);
            if (strJson != null) {
                updateUserData(strJson);
            } else {
                // Handle the case where strJson is null (e.g., network error)
            }
        }

        private void updateUserData(String strJson) {
            try {
                JSONArray parent = new JSONArray(strJson);
                JSONObject child = parent.getJSONObject(0);
                String fish = child.getString("fishnames");
                String desc = child.getString("descriptions");
                fishNames.setText(fish);
                fishDescription.setText(desc);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
*/
}