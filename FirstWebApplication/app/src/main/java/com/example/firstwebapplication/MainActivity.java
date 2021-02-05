package com.example.firstwebapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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


public class MainActivity extends AppCompatActivity {


    Button buttonGoAllCars;
    Button buttonGoOneCar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonGoAllCars = findViewById(R.id.buttonGoAllCars);
        buttonGoOneCar = findViewById(R.id.buttonGoOneCar);

        buttonGoAllCars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirectToAllCars = new Intent(MainActivity.this, AllCars.class);
                startActivity(redirectToAllCars);
            }
        });

        buttonGoOneCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirectToOneCar = new Intent(MainActivity.this, SingleCarActivity.class);
                startActivity(redirectToOneCar);
            }
        });
    }


}
