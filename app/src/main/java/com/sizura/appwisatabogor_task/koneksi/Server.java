package com.sizura.appwisatabogor_task.koneksi;

public class Server {
    public static final String BASE_URL = "http://codinate.hol.es/";
    public static ClientService getClientService(){
        return ConfigRetrofit.getClient(BASE_URL).create(ClientService.class);
    }
}
