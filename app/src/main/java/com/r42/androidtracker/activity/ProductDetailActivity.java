package com.r42.androidtracker.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.r42.androidtracker.R;
import com.r42.androidtracker.model.Product;

public class ProductDetailActivity extends AppCompatActivity {

    private TextView product_detail_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Product product = (Product) getIntent().getSerializableExtra("product");

        product_detail_name = (TextView) findViewById(R.id.product_detail_name);
        product_detail_name.setText(product.getName());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();
        if (id == android.R.id.home)
        {
            onBackPressed();
            return true;
        }
        else
        {
            return super.onOptionsItemSelected(item);
        }
    }
}