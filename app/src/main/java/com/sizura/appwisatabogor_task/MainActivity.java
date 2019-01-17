package com.sizura.appwisatabogor_task;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    RecyclerView rvWisata;
    private AdapterActivity adapter;
    List<WisataItem> listWisata = new ArrayList<>();

    ProgressDialog loading;
    ClientService client;

    private final String nama_wisata = "nama_wisata";
    private final String deskripsi_wisata = "deskripsi_wisata";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        rvWisata = findViewById(R.id.rv_wisata);
        rvWisata.setHasFixedSize(true);
        rvWisata.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        rvWisata.setAdapter(adapter);

        client = Server.getClientService();

        adapter = new AdapterActivity(getApplicationContext(), listWisata);
        refresh();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void refresh() {
//        loading = ProgressDialog.show(this,null,"Harap Tunggu...",true,false);
        client.getWisata(nama_wisata,deskripsi_wisata).enqueue(new Callback<Wisata>() {
            @Override
            public void onResponse(Call<Wisata> call, Response<Wisata> response) {
                if (response.isSuccessful()) {
//                    loading.dismiss();
//                    if (response.body() != null) {
                        listWisata = response.body().getWisata();
//                    }
                    rvWisata.setAdapter(new AdapterActivity(getApplicationContext(), listWisata));
                    adapter.notifyDataSetChanged();
                } else {
//                    loading.dismiss();
                    Toast.makeText(MainActivity.this, "Data gagal ditampilkan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Wisata> call, Throwable t) {
//                loading.dismiss();
                Toast.makeText(MainActivity.this, "Koneksi Buruk..", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_favorit) {
            Intent fav = new Intent(getApplicationContext(),FavoritActivity.class);
            startActivity(fav);
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_send) {
            showFeedbackDialog();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void showFeedbackDialog() {

        LayoutInflater layoutInflaterAndroid = LayoutInflater.from(getApplicationContext());
        View view = layoutInflaterAndroid.inflate(R.layout.feedback_dialog, null);

        AlertDialog.Builder alertDialogBuilderUserInput = new AlertDialog.Builder(MainActivity.this);
        alertDialogBuilderUserInput.setView(view);

        final EditText inputFeedback = view.findViewById(R.id.edFeedback);
        TextView dialogTitle = view.findViewById(R.id.dialog_title);
        dialogTitle.setText(getString(R.string.lbl_title));

        alertDialogBuilderUserInput
                .setCancelable(false)
                .setPositiveButton(R.string.send, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialogBox, int id) {

                    }
                })
                .setNegativeButton(R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                dialogBox.cancel();
                            }
                        });

        final AlertDialog alertDialog = alertDialogBuilderUserInput.create();
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Show toast message when no text is entered
                if (TextUtils.isEmpty(inputFeedback.getText().toString())) {
                    Toast.makeText(MainActivity.this, R.string.hint_enter_feedback, Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    alertDialog.dismiss();
                }

                // kirim email menggunakan intent untuk membuka aplikasi email
                Intent intent = new Intent(Intent.ACTION_SEND);
                // buat intent meembawa data pada aplikasi
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"zuhriahmada21@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback My App");
                intent.putExtra(Intent.EXTRA_TEXT, inputFeedback.getText().toString());
                // mengubah tipe dari email
                intent.setType("message/rfc822");
                // mulai mengirim email ke aplikasi gmail
                startActivity(Intent.createChooser(intent, "Pilih Aplikasi"));
            }
        });
    }
}
