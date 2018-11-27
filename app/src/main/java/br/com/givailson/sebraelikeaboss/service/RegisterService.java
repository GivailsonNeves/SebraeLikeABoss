package br.com.givailson.sebraelikeaboss.service;

import br.com.givailson.sebraelikeaboss.models.BaseRequest;
import br.com.givailson.sebraelikeaboss.models.Register;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RegisterService {

    @POST("service.php?type=register")
    Call<BaseRequest> register(@Body Register register);
}
