package com.sizura.appwisatabogor_task.model;

import com.google.gson.annotations.SerializedName;

public class WisataItem{

	@SerializedName("longitude_wisata")
	private String longitudeWisata;

	@SerializedName("id_wisata")
	private String idWisata;

	@SerializedName("jumlah_kunjungan")
	private String jumlahKunjungan;

	@SerializedName("latitude_wisata")
	private String latitudeWisata;

	@SerializedName("nama_wisata")
	private String namaWisata;

	@SerializedName("gambar_wisata")
	private String gambarWisata;

	@SerializedName("deksripsi_wisata")
	private String deksripsiWisata;

	@SerializedName("alamat_wisata")
	private String alamatWisata;

	@SerializedName("event_wisata")
	private String eventWisata;

	public void setLongitudeWisata(String longitudeWisata){
		this.longitudeWisata = longitudeWisata;
	}

	public String getLongitudeWisata(){
		return longitudeWisata;
	}

	public void setIdWisata(String idWisata){
		this.idWisata = idWisata;
	}

	public String getIdWisata(){
		return idWisata;
	}

	public void setJumlahKunjungan(String jumlahKunjungan){
		this.jumlahKunjungan = jumlahKunjungan;
	}

	public String getJumlahKunjungan(){
		return jumlahKunjungan;
	}

	public void setLatitudeWisata(String latitudeWisata){
		this.latitudeWisata = latitudeWisata;
	}

	public String getLatitudeWisata(){
		return latitudeWisata;
	}

	public void setNamaWisata(String namaWisata){
		this.namaWisata = namaWisata;
	}

	public String getNamaWisata(){
		return namaWisata;
	}

	public void setGambarWisata(String gambarWisata){
		this.gambarWisata = gambarWisata;
	}

	public String getGambarWisata(){
		return gambarWisata;
	}

	public void setDeksripsiWisata(String deksripsiWisata){
		this.deksripsiWisata = deksripsiWisata;
	}

	public String getDeksripsiWisata(){
		return deksripsiWisata;
	}

	public void setAlamatWisata(String alamatWisata){
		this.alamatWisata = alamatWisata;
	}

	public String getAlamatWisata(){
		return alamatWisata;
	}

	public void setEventWisata(String eventWisata){
		this.eventWisata = eventWisata;
	}

	public String getEventWisata(){
		return eventWisata;
	}

	@Override
 	public String toString(){
		return 
			"WisataItem{" + 
			"longitude_wisata = '" + longitudeWisata + '\'' + 
			",id_wisata = '" + idWisata + '\'' + 
			",jumlah_kunjungan = '" + jumlahKunjungan + '\'' + 
			",latitude_wisata = '" + latitudeWisata + '\'' + 
			",nama_wisata = '" + namaWisata + '\'' + 
			",gambar_wisata = '" + gambarWisata + '\'' + 
			",deksripsi_wisata = '" + deksripsiWisata + '\'' + 
			",alamat_wisata = '" + alamatWisata + '\'' + 
			",event_wisata = '" + eventWisata + '\'' + 
			"}";
		}
}