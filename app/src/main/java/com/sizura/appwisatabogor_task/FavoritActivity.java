package com.sizura.appwisatabogor_task;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.sizura.appwisatabogor_task.koneksi.ClientService;
import com.sizura.appwisatabogor_task.koneksi.Server;
import com.sizura.appwisatabogor_task.model.Wisata;
import com.sizura.appwisatabogor_task.model.WisataItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FavoritActivity extends AppCompatActivity {

    RecyclerView rvWisataFav;
    private AdapterActivity adapter;
    List<WisataItem> listWisata = new ArrayList<>();

    ProgressDialog loading;
    ClientService client;

    private final String nama_wisata = "nama_wisata";
    private final String deskripsi_wisata = "deskripsi_wisata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);

        rvWisataFav = findViewById(R.id.rv_favorit);
        rvWisataFav.setHasFixedSize(true);
        rvWisataFav.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvWisataFav.setAdapter(adapter);

        client = Server.getClientService();

        adapter = new AdapterActivity(getApplicationContext(), listWisata);
        refresh();
    }

    private void refresh() {
        client.getWisata(nama_wisata,deskripsi_wisata).enqueue(new Callback<Wisata>() {
            @Override
            public void onResponse(Call<Wisata> call, Response<Wisata> response) {
                if (response.isSuccessful()) {
//                    loading.dismiss();
//                    if (response.body() != null) {
                    listWisata = response.body().getWisata();
//                    }
                    rvWisataFav.setAdapter(new AdapterActivity(getApplicationContext(), listWisata));
                    adapter.notifyDataSetChanged();
                } else {
//                    loading.dismiss();
                    Toast.makeText(FavoritActivity.this, "Data gagal ditampilkan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Wisata> call, Throwable t) {
//                loading.dismiss();
                Toast.makeText(FavoritActivity.this, "Koneksi Buruk..", Toast.LENGTH_SHORT).show();
                Log.d("Failure", "onFailure: " + t.getMessage());
            }
        });
    }

}
