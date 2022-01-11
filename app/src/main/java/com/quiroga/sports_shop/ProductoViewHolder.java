package com.quiroga.sports_shop;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.NetworkImageView;

public class ProductoViewHolder extends RecyclerView.ViewHolder{
    public ImageView productoImagen;
    public TextView nombreProducto;
    public TextView marcaProducto;

    public ProductoViewHolder(@NonNull View itemView) {
        super(itemView);
        productoImagen = itemView.findViewById(R.id.producto_imagen);
        nombreProducto = itemView.findViewById(R.id.nombre_producto);
        marcaProducto = itemView.findViewById(R.id.marca_producto);
    }
}
