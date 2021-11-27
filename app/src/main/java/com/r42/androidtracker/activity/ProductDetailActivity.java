package com.r42.androidtracker.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.r42.androidtracker.R;
import com.r42.androidtracker.model.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {

    public CardView productStatusIcon;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Product product = (Product) getIntent().getSerializableExtra("product");
        getSupportActionBar().setTitle(product.getName());

        TextView productName = (TextView) findViewById(R.id.product_detail_name);
        TextView productDescription = (TextView) findViewById(R.id.product_detail_description);
        TextView productPrice = (TextView) findViewById(R.id.product_detail_price);
        TextView productPriceCalculation = (TextView) findViewById(R.id.product_detail_price_calculation);
        Button addToCart = (Button) findViewById(R.id.add_to_cart);
        Button addToWishlist = (Button) findViewById(R.id.add_to_wishlist);
        ImageView productImage = (ImageView) findViewById(R.id.product_detail_image);
        TextView productStatus = (TextView) findViewById(R.id.product_detail_status);
        productStatusIcon = (CardView) findViewById(R.id.product_detail_status_icon);

        int color = ContextCompat.getColor(
                this, product.isAvailable() ? R.color.green : R.color.red);
        float taxValue = (product.getPrice() / 100) * 21;
        float priceWithoutTax = product.getPrice() - taxValue;
        Picasso.get().load(product.getImage()).into(productImage);
        productName.setText(product.getName());
        productDescription.setText(product.getDescription());
        productPrice.setText(getResources().getString(R.string.product_price, product.getPrice()));
        productPriceCalculation.setText(getResources().getString(R.string.product_price_calculation, taxValue, priceWithoutTax));
        addToCart.setText(R.string.add_to_cart);
        addToWishlist.setText(R.string.add_to_wishlist);
        productStatus.setText(product.isAvailable() ? R.string.available : R.string.unavailable);
        productStatus.setTextColor(color);
        productStatusIcon.setCardBackgroundColor(color);

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        addToWishlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}