package com.example.firstwebapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SingleCarActivity extends AppCompatActivity {

    public String URL = "http://192.168.1.9:8080/FirstProject/car?id=";
    TextView carClassField;
    TextView carCategoryField;
    TextView carCharacteristicsField;
    TextView isSmokerField;
    EditText idOfCar;
    Button findCarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_car);

        idOfCar = findViewById(R.id.idOfCar);
        findCarButton = findViewById(R.id.findCarButton);
        carClassField = findViewById(R.id.carClassField);
        carCategoryField = findViewById(R.id.carCategoryField);
        carCharacteristicsField = findViewById(R.id.carCharacteristicsField);
        isSmokerField = findViewById(R.id.isSmokerField);

        findCarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int carIdLen = idOfCar.getText().toString().trim().length();
                if (carIdLen>0)
                {
                    System.out.println(idOfCar.getText().toString());
                    int realCarId = Integer.parseInt(idOfCar.getText().toString());
                    sendGetRequest(realCarId);
                }
            }
        });
    }

    private void sendGetRequest(int id) {
        RequestQueue reqQueue = Volley.newRequestQueue(SingleCarActivity.this);
        String modifiedURL = URL+id;
        JsonObjectRequest stringReq = new JsonObjectRequest(Request.Method.GET, modifiedURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    carClassField.setText(response.getString("carClass"));
                    carCategoryField.setText(response.getString("carCategory"));
                    carCharacteristicsField.setText(response.getString("carCharacteristics"));
                    isSmokerField.setText(response.getString("isSmoker"));
                }catch (JSONException je)
                {
                    carClassField.setText(je.getMessage());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                carClassField.setText("Error:"+error);
            }
        });
        reqQueue.add(stringReq);
    }
}
