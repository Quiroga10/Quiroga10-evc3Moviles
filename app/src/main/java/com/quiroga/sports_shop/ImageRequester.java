package com.quiroga.sports_shop;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.LruCache;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class ImageRequester {
    private static ImageRequester instance = null;
    private final Context context;
    private final RequestQueue requestQueue;
    private final ImageLoader imageLoader;
    private final int maxByteSize;

    private ImageRequester() {
        context = SportApplication.getAppContext();
        this.requestQueue = Volley.newRequestQueue(context);
        this.requestQueue.start();
        this.maxByteSize = calculateMaxByteSize();
        this.imageLoader =
                new ImageLoader(
                        requestQueue,
                        new ImageLoader.ImageCache() {
                            private final LruCache<String, Bitmap> lruCache =
                                    new LruCache<String, Bitmap>(maxByteSize) {
                                        @Override
                                        protected int sizeOf(String url, Bitmap bitmap) {
                                            return bitmap.getByteCount();
                                        }
                                    };

                            @Override
                            public synchronized Bitmap getBitmap(String url) {
                                return lruCache.get(url);
                            }

                            @Override
                            public synchronized void putBitmap(String url, Bitmap bitmap) {
                                lruCache.put(url, bitmap);
                            }
                        });
    }

    public static ImageRequester getInstance() {
        if (instance == null) {
            instance = new ImageRequester();
        }
        return instance;
    }

    public void setImageFromUrl(ImageView imageView, String url) {

        Glide.with(imageView.getContext())
                .load(url)
                .error(R.mipmap.ic_launcher)
                .apply(RequestOptions.centerInsideTransform())
                .into(imageView);
    }

    private int calculateMaxByteSize() {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        final int screenBytes = displayMetrics.widthPixels * displayMetrics.heightPixels * 4;
        return screenBytes * 3;
    }
}
