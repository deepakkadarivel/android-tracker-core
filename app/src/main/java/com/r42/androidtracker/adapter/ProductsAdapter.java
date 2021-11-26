package com.r42.androidtracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.r42.androidtracker.R;
import com.r42.androidtracker.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductsAdapter extends
        RecyclerView.Adapter<ProductsAdapter.ViewHolder> {
    private final List<Product> products;

    public ProductsAdapter(List<Product> list) {
        products = list;
    }

    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View productView = inflater.inflate(R.layout.product_card, parent, false);

        ViewHolder viewHolder = new ViewHolder(productView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder holder, int position) {
        Product product = products.get(position);
        int color = ContextCompat.getColor(
                holder.productStatus.getContext(), product.isAvailable() ? R.color.green : R.color.red);
        Picasso.get().load(product.getImage()).into(holder.productImage);
        holder.productName.setText(product.getName());
        holder.productDescription.setText(product.getDescription());
        holder.productPrice.setText("$ " + String.format("%.2f", product.getPrice()));
        holder.productStatus.setText(product.isAvailable() ? "Available" : "Unavailable");
        holder.productStatus.setTextColor(color);
        holder.productStatusIcon.setCardBackgroundColor(color);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView productImage;
        public TextView productName, productStatus, productDescription, productPrice;
        public CardView productStatusIcon;

        public ViewHolder(View itemView) {
            super(itemView);

            productImage = itemView.findViewById(R.id.product_image);
            productName = itemView.findViewById(R.id.product_name);
            productStatus = itemView.findViewById(R.id.product_status);
            productDescription = itemView.findViewById(R.id.product_description);
            productPrice = itemView.findViewById(R.id.product_price);
            productStatusIcon = itemView.findViewById(R.id.product_status_icon);
        }
    }
}