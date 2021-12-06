package com.example.lat8;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class TechFragment extends Fragment {
    String api_key = "d5ce7624d5894cb382812f3bf6bd59fd";
    ArrayList<ModelClass> modelClassArrayList;
    Adapter adapter;
    String country="id";
    private RecyclerView recyclerViewtech;
    private String category="technology";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle
                                     savedInstanceState) {
        View v =
                inflater.inflate(R.layout.technologyfragment,null);
        recyclerViewtech=v.findViewById(R.id.recyclertech);
        modelClassArrayList = new ArrayList<>();
        recyclerViewtech.setLayoutManager(new
                LinearLayoutManager(getContext()));
        adapter = new
                Adapter(getContext(),modelClassArrayList);
        recyclerViewtech.setAdapter(adapter);
        findNews();
        return v;
    }
    private void findNews() {

        ApiUtilities.getApiInterface().getCategoryNews(country,category,100,api_key).enqueue(new Callback<mainNews>() {
            @Override
            public void onResponse(Call<mainNews> call,
                                   Response<mainNews> response) {
                if(response.isSuccessful())
                {

                    modelClassArrayList.addAll(response.body().getArticles());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<mainNews> call,
                                  Throwable t) {
            }
        });
    }
}

