package co.com.ceiba.mobile.pruebadeingreso.model;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.ResponsePositive;
import co.com.ceiba.mobile.pruebadeingreso.dto.UserDTO;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;

public interface IUserRetrofitService {


    @GET(Endpoints.GET_USERS)
    Call<List<UserDTO>> getUsers ();
}
