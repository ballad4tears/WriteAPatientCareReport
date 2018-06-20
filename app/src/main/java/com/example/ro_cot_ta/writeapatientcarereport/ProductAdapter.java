package com.example.ro_cot_ta.writeapatientcarereport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.ro_cot_ta.writeapatientcarereport.Product;
import com.example.ro_cot_ta.writeapatientcarereport.R;

import java.util.List;

/**
 * Created by Belal on 10/18/2017.
 */

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {


    private Context mCtx;
    private List<Product> productList;

    public ProductAdapter(Context mCtx, List<Product> productList) {
        this.mCtx = mCtx;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.product_list, null);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        //loading the image
       /* Glide.with(mCtx)
                .load(product.getImage())
                .into(holder.imageView);*/

        holder.Name.setText(product.getName());
        holder.LastName.setText(product.getLastname());
        holder.Address.setText(product.getAddress());
        holder.Allergic.setText(product.getAllergic());
        //holder.textViewRating.setText(String.valueOf(product.getNo()));
        //holder.textViewPrice.setText(String.valueOf(product.getNumber()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder {

        TextView Name,LastName,Address,Allergic;
        //TextView textViewTitle, textViewShortDesc, textViewRating, textViewPrice;
        //ImageView imageView;

        public ProductViewHolder(View itemView) {
            super(itemView);

            /*textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPrice = itemView.findViewById(R.id.textViewPrice);
            imageView = itemView.findViewById(R.id.imageView);*/

            Name = itemView.findViewById(R.id.Name);
            LastName = itemView.findViewById(R.id.LastName);
            Address = itemView.findViewById(R.id.Address);
            Allergic = itemView.findViewById(R.id.Allergic);
        }
    }
}