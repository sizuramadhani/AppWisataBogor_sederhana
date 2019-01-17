package com.sizura.appwisatabogor_task;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.sizura.appwisatabogor_task.model.WisataItem;

import java.util.List;

public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.ViewHolder> {

    private Context context;
    private List<WisataItem>listnya;

    public AdapterActivity(Context context, List<WisataItem> listnya) {
        this.context = context;
        this.listnya = listnya;
    }

    @NonNull
    @Override
    public AdapterActivity.ViewHolder onCreateViewHolder(ViewGroup parent, int view) {

        //set layout inflater
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_wisata,null,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup
                .LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);

        v.setLayoutParams(layoutParams);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(AdapterActivity.ViewHolder holder, int position) {

       final WisataItem wisataItem = listnya.get(position);

        Glide.with(context).load("http://codinate.hol.es/wisata_bogor/img/wisata/"+wisataItem.getGambarWisata())
                .apply(new RequestOptions()
                        .placeholder(R.mipmap.ic_launcher)).into(holder.gmb);
        holder.nama.setText(wisataItem.getNamaWisata());
        holder.deskripsi.setText(wisataItem.getDeksripsiWisata());


        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context,DetailActivity.class);
                i.putExtra("nama_wisata",wisataItem.getNamaWisata());
                i.putExtra("deskripsi_wisata",wisataItem.getDeksripsiWisata());
                i.putExtra("gambar_wisata",wisataItem.getGambarWisata());
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return listnya.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private ImageView gmb;
        private TextView nama,deskripsi;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = itemView.findViewById(R.id.cv);
            nama = itemView.findViewById(R.id.tv_name);
            gmb = itemView.findViewById(R.id.imgPoster);
            deskripsi = itemView.findViewById(R.id.tv_desc);
        }
    }
}
