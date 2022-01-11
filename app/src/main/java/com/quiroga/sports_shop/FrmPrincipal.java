package com.quiroga.sports_shop;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FrmPrincipal extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.principalfragment, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ProductoRecyclerVA adapter = new ProductoRecyclerVA(
                Producto.initProductEntryList(getResources()));
        recyclerView.setAdapter(adapter);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.card_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.card_spacing);
        recyclerView.addItemDecoration(new ProductoDecoration(largePadding, smallPadding));


        view.findViewById(R.id.mbButtonPrincipal).setOnClickListener(bt -> {
            ((NavigationHost) getActivity()).navigateTo(new frmPerfil(), false);
        });

        return view;
    }

    private void setUpToolbar(View view) {
        Toolbar toolbar = view.findViewById(R.id.app_bar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        if(activity != null) {
            activity.setSupportActionBar(toolbar);
        }
    }
}
