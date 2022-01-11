package com.quiroga.sports_shop;

import android.content.res.Resources;
import android.net.Uri;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private static final String TAG = Producto.class.getSimpleName();

    public final String nombreProducto;
    public final String marcaProducto;
    public final Uri dynamicUrl;
    public final String url;

    public Producto(String nombreProducto, String marcaProducto, String dynamicUrl, String url) {
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.dynamicUrl = Uri.parse(dynamicUrl);
        this.url = url;
    }

    public static List<Producto> initProductEntryList (Resources resources) {
        InputStream inputStream = resources.openRawResource(R.raw.productos);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            int pointer;
            while ((pointer = reader.read(buffer)) != -1) {
                writer.write(buffer, 0 , pointer);
            }
        } catch (IOException exception){
            Log.e(TAG, "Error JSON", exception);
        } finally {
            try {
                inputStream.close();
            } catch (IOException exception) {
                Log.e(TAG, "Error input stream", exception);
            }
        }

        String jsonProductsString = writer.toString();
        Gson gson = new Gson();
        Type productListType = new TypeToken<ArrayList<Producto>>(){
        }.getType();

        return gson.fromJson(jsonProductsString, productListType);
    }
}
