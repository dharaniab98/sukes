package com.example.dharani.navigation;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Order_List_Activity extends AppCompatActivity {

    ArrayList<OrderData> dataModels;
    ListView listView;
    private static CustomAdapter adapter;

    String order_id;
    String service_desc;
    String created_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order__list_);
        Toast.makeText(this, "hello", Toast.LENGTH_SHORT).show();

        listView=(ListView)findViewById(R.id.order_list);

        dataModels= new ArrayList();


////
//         SharedPreferences sharedPreferences = getSharedPreferences("OrderData", Context.MODE_PRIVATE);
//
//        String data = sharedPreferences.getString("Orders","");
//
//        Gson gson = new Gson();
//
//        dataModels = gson.fromJson(data,ArrayList.class);

        String url = "http://www.sukes.in/allorders/2";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("network request","working");

                            JSONArray orders = response.getJSONArray("ordersData");
                            for(int i=0;i<orders.length();i++)
                            {
                                JSONObject jsonObject=(JSONObject)orders.get(i);
                                String service_name = jsonObject.getString("servicename");
                                String service_type = jsonObject.getString("servicetype");
                                String order_id = jsonObject.getString("orderid");
                                String order_status = jsonObject.getString("orderstatus");
                                dataModels.add(new OrderData(service_name,service_type,order_status,order_id));

                            }
                        }catch(Exception e){
                            Log.e("json parse error:","exception:"+e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getBaseContext(),"NO CONNECTION",Toast.LENGTH_LONG).show();
                        Log.e("error","connection error in networking "+error);

                    }
                });

        //Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        adapter= new CustomAdapter(dataModels,getApplicationContext());

                        listView.setAdapter(adapter);

                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                OrderData dataModel= dataModels.get(position);

                                String order_id = dataModel.order_id;

                                String url = "http://www.sukes.in/ordersdet/"+order_id;

                                orderInfo(url, view);


                            }
                        });

                    }
                }, 3000);



    }

    public void orderInfo(String url, View v){

        final View view = v;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("network request","working");

                            JSONObject order_data = response.getJSONObject("loggedInId");
                            order_id = order_data.getString("orderId");
                            service_desc = order_data.getString("serviceDesc");
                            created_on = order_data.getString("created_on");
                        }catch(Exception e){
                            Log.e("json parse error:","exception:"+e);
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error","connection error in networking "+error);

                    }
                });

        //Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {

                        Snackbar snackbar = Snackbar.make(view,"Order id:"+order_id+"\nIssue created on :"+created_on+"\nService Description:"+service_desc,Snackbar.LENGTH_LONG).setAction("NO action",null);
                        View snackbarview = snackbar.getView();
                        TextView textView = (TextView)snackbarview.findViewById(android.support.design.R.id.snackbar_text);
                        textView.setMaxLines(4);
                        snackbar.show();
                    }
                }, 3000);

    }


}

