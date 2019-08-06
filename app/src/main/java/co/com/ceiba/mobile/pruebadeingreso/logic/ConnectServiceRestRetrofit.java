package co.com.ceiba.mobile.pruebadeingreso.logic;
import android.util.Log;
import co.com.ceiba.mobile.pruebadeingreso.model.IUserRetrofitService;
import co.com.ceiba.mobile.pruebadeingreso.rest.Endpoints;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConnectServiceRestRetrofit{


    public IUserRetrofitService getCone(){

        try {

            /*Se crea y se conecta a la url base y  se retorna la instancia de la conexion*/
            Retrofit retrofit = new Retrofit.Builder().baseUrl(Endpoints.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create()).build();

            IUserRetrofitService iUserRetrofitService = retrofit.create(IUserRetrofitService.class);

            return  iUserRetrofitService;

        }catch (Exception e){
            Log.e("Error",e.getMessage());
            return null;
        }
    }

}
