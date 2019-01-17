package com.sizura.appwisatabogor_task.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Wisata{

	@SerializedName("wisata")
	private List<WisataItem> wisata;

	@SerializedName("success")
	private boolean success;

	@SerializedName("message")
	private String message;

	public void setWisata(List<WisataItem> wisata){
		this.wisata = wisata;
	}

	public List<WisataItem> getWisata(){
		return wisata;
	}

	public void setSuccess(boolean success){
		this.success = success;
	}

	public boolean isSuccess(){
		return success;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	@Override
 	public String toString(){
		return 
			"Wisata{" + 
			"wisata = '" + wisata + '\'' + 
			",success = '" + success + '\'' + 
			",message = '" + message + '\'' + 
			"}";
		}
}