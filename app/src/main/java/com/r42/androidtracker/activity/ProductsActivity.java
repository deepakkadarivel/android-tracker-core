package com.r42.androidtracker.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.r42.androidtracker.R;
import com.r42.androidtracker.adapter.ProductsAdapter;
import com.r42.androidtracker.model.Product;

import java.util.ArrayList;

public class ProductsActivity extends AppCompatActivity {

    ArrayList<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        products = Product.createProductList();

        RecyclerView rvProducts = (RecyclerView) findViewById(R.id.products_recycler_view);
        ProductsAdapter adapter = new ProductsAdapter(products);
        rvProducts.setHasFixedSize(true);
        rvProducts.setAdapter(adapter);
        rvProducts.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
    }
}