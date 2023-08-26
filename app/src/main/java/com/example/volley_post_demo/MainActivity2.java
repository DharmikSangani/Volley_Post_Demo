package com.example.volley_post_demo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity {

    RecyclerView recyclerView;

//    ArrayList<Data_Model> dataModalList = new ArrayList<Data_Model>();
//    Data_Model dataModel;
    Product_Data productData;
    List<Product_Data> productdatalist=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.recyclerview);

        RequestQueue queue = Volley.newRequestQueue(MainActivity2.this);
        String urlweb = "https://amiparaandroid.000webhostapp.com/Myapp/viewProduct.php";
        //https://amiparaandroid.000webhostapp.com/Myapp/Register.php
        //https://amiparaandroid.000webhostapp.com/Myapp/login.php
        //https://amiparaandroid.000webhostapp.com/Myapp/addProduct.php

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlweb,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject mainObj=new JSONObject(response);
                            int connection= (int) mainObj.get("connection");
                            int result= (int) mainObj.get("result");
                            if(connection==1 && result==1)
                            {
                                Toast.makeText(MainActivity2.this, "Data Available", Toast.LENGTH_SHORT).show();

                                JSONArray ObjArr=mainObj.getJSONArray("productdata");
                                for (int i=0;i<ObjArr.length();i++) {
                                    JSONObject arrOfObj=ObjArr.getJSONObject(i);
                                    Integer ID = arrOfObj.getInt("ID");
                                    String UID = arrOfObj.getString("UID");
                                    String PRO_NAME = arrOfObj.getString("PRO_NAME");
                                    String PRO_DES = arrOfObj.getString("PRO_DES");
                                    String PRO_PRICE = arrOfObj.getString("PRO_PRICE");
                                    String PRO_IMAGE = arrOfObj.getString("PRO_IMAGE");

                                    productData = new Product_Data(ID, UID, PRO_NAME, PRO_DES, PRO_PRICE, PRO_IMAGE);
                                    productdatalist.add(productData);

                                }
                                Log.d("TTT", "onResponse: productDataList="+productdatalist.toString());
                                RecyclerAdapter adapter = new RecyclerAdapter(MainActivity2.this,productdatalist);
                                recyclerView.setAdapter(adapter);

                                LinearLayoutManager manager=new LinearLayoutManager(getApplicationContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);



                            }
//                            Log.d("LLL", "onResponse:"+mainObj.get("result"));

                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }

//                        Log.d("YYY", "list of data" + dataModalList);

                    }

                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("LLL","onErrorResponse"+error.getLocalizedMessage());
                    }

                })
        {
            @Override
            protected Map<String, String> getParams(){
                HashMap<String, String> params = new HashMap<String, String>();
                params.put("userid","5");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(stringRequest);
    }
}