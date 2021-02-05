package com.example.firstwebapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleCarClickedActivity extends AppCompatActivity {

    public String URL = "http://192.168.1.9:8080/FirstProject/car?id=";
    TextView carIdText;
    TextView carClassText;
    TextView carCategoryText;
    TextView carCharacteristicsText;
    TextView isSmokerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_car_clicked);

        carIdText = findViewById(R.id.carIdText);
        carClassText = findViewById(R.id.carClassText);
        carCategoryText = findViewById(R.id.carCategoryText);
        carCharacteristicsText = findViewById(R.id.carCharacteristicsText);
        isSmokerText = findViewById(R.id.isSmokerText);

        String carId = getIntent().getStringExtra("carId");
        int realCarId = Integer.parseInt(carId);
        System.out.println(realCarId);
        carIdText.setText(carId);

        sendGetRequest(realCarId);
    }

    private void sendGetRequest(int id) {
        RequestQueue reqQueue = Volley.newRequestQueue(SingleCarClickedActivity.this);
        String modifiedURL = URL+id;
        JsonObjectRequest stringReq = new JsonObjectRequest(Request.Method.GET, modifiedURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    carClassText.setText(response.getString("carClass"));
                    carCategoryText.setText(response.getString("carCategory"));
                    carCharacteristicsText.setText(response.getString("carCharacteristics"));
                    isSmokerText.setText(response.getString("isSmoker"));
                }catch (JSONException je)
                {
                    carClassText.setText(je.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                carClassText.setText("Error:"+error);
            }
        });
        reqQueue.add(stringReq);
    }
}

