package com.r42.androidtracker.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.r42.androidtracker.R;
import com.r42.androidtracker.adapter.ProductsAdapter;
import com.r42.androidtracker.model.Product;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    String url = "https://ajsj4e.deta.dev/products";
    ArrayList<Product> products;
    ProgressBar progressBar;
    RecyclerView rvProducts;
    ProductsAdapter adapter;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        products = new ArrayList<>();

        rvProducts = (RecyclerView) findViewById(R.id.products_recycler_view);
        progressBar = findViewById(R.id.products_progress_bar);
        swipeRefreshLayout = findViewById(R.id.products_swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        buildRecyclerView();

        swipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                if (swipeRefreshLayout != null) {
                    swipeRefreshLayout.setRefreshing(true);
                }
                getProducts();
            }
        });
    }

    private void getProducts() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                progressBar.setVisibility(View.GONE);
                rvProducts.setVisibility(View.VISIBLE);
                adapter.clear();
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject responseObj = response.getJSONObject(i);
                        int id = responseObj.getInt("id");
                        String name = responseObj.getString("name");
                        String description = responseObj.getString("description");
                        String image = responseObj.getString("image");
                        float price = responseObj.getLong("price");
                        boolean available = responseObj.getBoolean("available");

                        JSONArray images = responseObj.getJSONArray("alternativeImages");
                        ArrayList<String> alternativeImages = new ArrayList<>();
                        for (int j = 0; j < images.length(); j++) {
                            alternativeImages.add(images.getString(j));
                        }
                        products.add(new Product(id, name, description, price, image, new ArrayList<>(), available));
//                        buildRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    swipeRefreshLayout.setRefreshing(false);
                }
//                adapter.addAll(products);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProductsActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        queue.add(jsonArrayRequest);
    }

    private void buildRecyclerView() {
        adapter = new ProductsAdapter(products);
        rvProducts.setHasFixedSize(true);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }

    @Override
    public void onRefresh() {
        getProducts();
    }
}