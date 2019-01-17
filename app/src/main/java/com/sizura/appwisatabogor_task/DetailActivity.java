package com.sizura.appwisatabogor_task;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

public class DetailActivity extends AppCompatActivity {

    CoordinatorLayout coordinatorLayout;
    String txtPoster,txtJudul,txtDate,txtDesc;
    TextView tvJudul,tvDesc,tvTgl;
    ImageView imgPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        imgPoster = findViewById(R.id.img_posterDetail);
        tvJudul = findViewById(R.id.tv_judul);
        tvDesc = findViewById(R.id.tv_desc);
        tvTgl = findViewById(R.id.tv_tgl);

        txtPoster = getIntent().getStringExtra("gambar_wisata");
        txtJudul = getIntent().getStringExtra("nama_wisata");
        txtDesc = getIntent().getStringExtra("deskripsi_wisata");

        Glide.with(getApplicationContext()).load("http://codinate.hol.es/wisata_bogor/img/wisata/"+txtPoster)
                .apply(new RequestOptions().placeholder(R.drawable.ic_menu_camera)).into(imgPoster);
        tvJudul.setText(txtJudul);
//        tvTgl.setText(txtDate);
        tvDesc.setText(txtDesc);
    }

    public void OnBackPressed (){
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }
}
