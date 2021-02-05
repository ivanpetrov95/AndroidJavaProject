package com.example.firstwebapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AllCars extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<CarEntity> carList = new ArrayList<>();//for recyclerView

    public static final String URL = "http://192.168.1.9:8080/FirstProject/cars";
    public static final String deletionURL = "http://192.168.1.9:8080/FirstProject/car/deletion?id=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_cars);

        sendGetRequest();


        recyclerView = findViewById(R.id.carsView);
        recyclerViewLayoutManager = new LinearLayoutManager(this);


    }

    private void sendGetRequest() {
        RequestQueue reqQueue = Volley.newRequestQueue(AllCars.this);
        JsonObjectRequest stringReq = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray results = response.getJSONArray("list");

                    for (int i = 0; i < results.length(); i++)
                    {
                        CarEntity carObject = new CarEntity();
                        JSONObject carDetails = results.getJSONObject(i);
                        carObject.setID(Integer.parseInt(carDetails.getString("ID")));
                        carObject.setCarClass(carDetails.getString("carClass"));
                        carObject.setCarCategory(carDetails.getString("carCategory"));
                        carObject.setCarCharacteristics(carDetails.getString("carCharacteristics"));
                        carObject.setIsSmoker(carDetails.getString("isSmoker"));
                        carList.add(carObject);
                    }
                    recyclerViewAdapter = new RecyclerViewAdapter(carList);

                    recyclerView.setLayoutManager(recyclerViewLayoutManager);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                             String carId = Integer.toString(carList.get(position).getID());
                            Intent redirectToOneCar = new Intent(AllCars.this, SingleCarClickedActivity.class);
                            redirectToOneCar.putExtra("carId", carId);
                            startActivity(redirectToOneCar);
                        }

                        @Override
                        public void onDeleteClick(int position) {
                            int carId = carList.get(position).getID();
                            sendDeleteRequest(carId);
                            carList.remove(position);
                            recyclerViewAdapter.notifyItemRemoved(position);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        reqQueue.add(stringReq);
    }
    private void sendDeleteRequest(int id) {
        RequestQueue reqQueue = Volley.newRequestQueue(AllCars.this);
        String modifiedURL = deletionURL+id;
        JsonObjectRequest stringReq = new JsonObjectRequest(Request.Method.DELETE, modifiedURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                System.out.println("Response:"+response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        reqQueue.add(stringReq);
    }
}
