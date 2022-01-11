package com.quiroga.sports_shop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductoRecyclerVA extends RecyclerView.Adapter<ProductoViewHolder> {
    private List<Producto> productoList;
    private ImageRequester imageRequester;

    ProductoRecyclerVA(List<Producto> productoList) {
        this.productoList = productoList;
        imageRequester = ImageRequester.getInstance();
    }

    @NonNull
    @Override
    public ProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType ){
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.productocardview, parent, false);
        return new ProductoViewHolder(layoutView);
    }

    @Override
    public  void onBindViewHolder(@NonNull ProductoViewHolder holder, int position) {
        if(productoList != null & position < productoList.size()) {
            Producto producto = productoList.get(position);
            holder.nombreProducto.setText(producto.nombreProducto);
            holder.marcaProducto.setText(producto.marcaProducto);
            imageRequester.setImageFromUrl(holder.productoImagen, producto.url);
        }
    }
    @Override
    public  int getItemCount() {
        return productoList.size();
    }
}
