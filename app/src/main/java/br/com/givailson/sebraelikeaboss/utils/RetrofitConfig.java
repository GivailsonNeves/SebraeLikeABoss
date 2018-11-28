package br.com.givailson.sebraelikeaboss.utils;

import br.com.givailson.sebraelikeaboss.service.RegisterService;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitConfig {
    private final Retrofit retrofit;

    public RetrofitConfig() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl("http://10.61.3.175/sebraelikeabosserver/")
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
    }

    public RegisterService getRegisterService() {
        return this.retrofit.create(RegisterService.class);
    }
}
