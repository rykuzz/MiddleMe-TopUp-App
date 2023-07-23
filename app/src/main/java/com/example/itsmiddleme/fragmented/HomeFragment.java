package com.example.itsmiddleme.fragmented;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.itsmiddleme.R;
import com.example.itsmiddleme.activity.AllProduct;
import com.example.itsmiddleme.adapter.CategoryAdapter;
import com.example.itsmiddleme.adapter.HotProductAdapter;
import com.example.itsmiddleme.adapter.PopularAdapter;
import com.example.itsmiddleme.model.CategoryModel;
import com.example.itsmiddleme.model.HotProductModels;
import com.example.itsmiddleme.model.PopularModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    TextView catallprod,popularallprod, hotprodall;

    RecyclerView recyclerView,hotProductsRecycler,popularRecycler;

    CategoryAdapter categoryAdapter;
    List<CategoryModel> categoryModelList;


    HotProductAdapter hotProductAdapter;
    List<HotProductModels> hotProductModelsList;

    PopularAdapter popularAdapter;
    List<PopularModel> popularModelList;

    FirebaseFirestore db;


    public HomeFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        db = FirebaseFirestore.getInstance();
        // Inflate the layout for this fragment
        View root =  inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = root.findViewById(R.id.rec_category);
        hotProductsRecycler = root.findViewById(R.id.new_product_rec);
        popularRecycler = root.findViewById(R.id.popular_rec);
        catallprod = root.findViewById(R.id.category_see_all);
        popularallprod = root.findViewById(R.id.popular_see_all);
        hotprodall = root.findViewById(R.id.newProducts_see_all);

        catallprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllProduct.class);
                startActivity(intent);
            }
        });

        hotprodall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllProduct.class);
                startActivity(intent);
            }
        });

        popularallprod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllProduct.class);
                startActivity(intent);
            }
        });


        ImageSlider imageSlider = root.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.banner1,"Top Up Diamond Mobile Legend Termurah", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner2,"Promo Diskon untuk pertama kali Top Up Valorant", ScaleTypes.CENTER_CROP));
        slideModels.add(new SlideModel(R.drawable.banner3,"Raih kemenanganmu dengan menggunakan skin langka PUBG", ScaleTypes.CENTER_CROP));

        imageSlider.setImageList(slideModels);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        categoryModelList = new ArrayList<>();
        categoryAdapter = new CategoryAdapter(getContext(),categoryModelList);
        recyclerView.setAdapter(categoryAdapter);

        db.collection("Category")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                CategoryModel categoryModel = document.toObject(CategoryModel.class);
                                categoryModelList.add(categoryModel);
                                categoryAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        hotProductsRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
        hotProductModelsList = new ArrayList<>();
        hotProductAdapter = new HotProductAdapter(getContext(),hotProductModelsList);
        hotProductsRecycler.setAdapter(hotProductAdapter);

        db.collection("HotProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                HotProductModels hotProductModels = document.toObject(HotProductModels.class);
                                hotProductModelsList.add(hotProductModels);
                                hotProductAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

        popularRecycler.setLayoutManager(new GridLayoutManager(getActivity(),2));
        popularModelList = new ArrayList<>();
        popularAdapter = new PopularAdapter(getContext(),popularModelList);
        popularRecycler.setAdapter(popularAdapter);

        db.collection("AllProducts")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                PopularModel popularModel = document.toObject(PopularModel.class);
                                popularModelList.add(popularModel);
                                popularAdapter.notifyDataSetChanged();
                            }
                        } else {

                            Toast.makeText(getActivity(), ""+task.getException(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });


        return root;
    }
}