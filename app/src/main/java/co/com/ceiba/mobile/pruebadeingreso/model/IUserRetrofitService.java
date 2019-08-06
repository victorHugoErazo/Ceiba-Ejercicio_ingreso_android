package co.com.ceiba.mobile.pruebadeingreso.model;

import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.dto.PostDTO;
import co.com.ceiba.mobile.pruebadeingreso.dto.UserDTO;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IUserRetrofitService {


    @GET(Endpoints.GET_USERS)
    Call<List<UserDTO>> getUsers ();

    /*Se busca el parametro de la URL userId y se le envia el respectivo ID*/
    @GET(Endpoints.GET_POST_USER)
    Call<List<PostDTO>> getPost (@Query("userId") String id);
}
