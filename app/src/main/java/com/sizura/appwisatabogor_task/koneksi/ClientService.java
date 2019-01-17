package com.sizura.appwisatabogor_task.koneksi;

import com.sizura.appwisatabogor_task.model.Wisata;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ClientService {

    @GET("wisata_bogor/wisata/read_wisata.php")
//    Call<Wisata> getWisata();
    Call<Wisata> getWisata(@Query("nama_wisata")String nama_wisata,
                           @Query("deskripsi_wisata")String deskripsi_wisata);






}
