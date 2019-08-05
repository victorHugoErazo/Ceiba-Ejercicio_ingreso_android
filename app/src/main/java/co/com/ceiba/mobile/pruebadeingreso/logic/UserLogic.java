package co.com.ceiba.mobile.pruebadeingreso.logic;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import co.com.ceiba.mobile.pruebadeingreso.adapter.UserListAdapter;
import co.com.ceiba.mobile.pruebadeingreso.adapter.UserListVo;
import co.com.ceiba.mobile.pruebadeingreso.dto.UserDTO;
import co.com.ceiba.mobile.pruebadeingreso.model.IUserRetrofitService;
import co.com.ceiba.mobile.pruebadeingreso.model.InterUser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import co.com.ceiba.mobile.pruebadeingreso.R;


public class UserLogic implements InterUser {

    ArrayList<UserListVo> userListVo;
    RecyclerView recyclerViewUser;
//    TextInputEditText editTextSearch;

    @Override
    public void getUser(Context context) throws Exception {

        try {

            final Activity activity = (Activity) context;
            activity.setContentView(R.layout.activity_main);
            userListVo = new ArrayList<>();
            recyclerViewUser = activity.findViewById(R.id.recyclerViewSearchResults);
            recyclerViewUser.setLayoutManager(new LinearLayoutManager(context));

            IUserRetrofitService getCone = new ConnectServiceRestRetrofit().getCone();

            Call<List<UserDTO>> call = getCone.getUsers();

            call.enqueue(new Callback<List<UserDTO>>() {
                @Override
                public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {

                    if(!response.isSuccessful()){

                        Log.i("Conexion","No fallida::"+response.code());
                    }else{

                        List<UserDTO> userDTOList = response.body();

                        for (UserDTO userDTO: userDTOList){

                            userListVo.add(new UserListVo(userDTO.getName(),userDTO.getPhone(),userDTO.getEmail()));
                        }

                        UserListAdapter userListAdapter = new UserListAdapter(userListVo);
                        recyclerViewUser.setAdapter(userListAdapter);

                        Toast toast = Toast.makeText(activity,"Usuarios Cargados",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                    Log.i("Conexion","Mensaje::"+t.getMessage());
                }
            });


        }catch (Exception e){
            Log.e("Error",e.getMessage());
            throw  e;
        }
    }

    @Override
    public void getUserForName(Context context) throws Exception {

        try {

            final Activity activity = (Activity) context;

            IUserRetrofitService getCone = new ConnectServiceRestRetrofit().getCone();

            Call<List<UserDTO>> call = getCone.getUsers();

            call.enqueue(new Callback<List<UserDTO>>() {
                @Override
                public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {

                    if(!response.isSuccessful()){

                        Log.i("Conexion","No fallida::"+response.code());
                    }else{
                        ArrayList<String> mylist = new ArrayList<String>();

                        List<UserDTO> userDTOList = response.body();
                        for (UserDTO userDTO: userDTOList){

                            mylist.add(userDTO.getName());
                        }

                        AutoCompleteTextView autoCompleteTextView  = activity.findViewById(R.id.editTextSearch);

                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                                (activity,android.R.layout.simple_list_item_1, mylist);

                        autoCompleteTextView.setAdapter(arrayAdapter);

                        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Object item = adapterView.getItemAtPosition(i);
                                if (item instanceof String){
                                    String userName = (String) item;
//                                    doSomethingWith(student);
                                    Log.i("Seleccionado","Este::"+userName);
                                    try {
                                        getSelctUser(view.getContext(),userName);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                    }
                }

                @Override
                public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                    Log.i("Conexion","Mensaje::"+t.getMessage());
                }
            });



        }catch (Exception e){
            Log.e("Error",e.getMessage());
            throw  e;
        }
    }

    public void getSelctUser(Context context, final String name) throws Exception {

        try {

            final Activity activity = (Activity) context;
            activity.setContentView(R.layout.activity_main);
            userListVo = new ArrayList<>();
            recyclerViewUser = activity.findViewById(R.id.recyclerViewSearchResults);
            recyclerViewUser.setLayoutManager(new LinearLayoutManager(context));

            IUserRetrofitService getCone = new ConnectServiceRestRetrofit().getCone();

            Call<List<UserDTO>> call = getCone.getUsers();

            call.enqueue(new Callback<List<UserDTO>>() {
                @Override
                public void onResponse(Call<List<UserDTO>> call, Response<List<UserDTO>> response) {

                    if(!response.isSuccessful()){

                        Log.i("Conexion","No fallida::"+response.code());
                    }else{

                        List<UserDTO> userDTOList = response.body();

                        for (UserDTO userDTO: userDTOList){

                            if(userDTO.getName().equals(name) ){
                                userListVo.add(new UserListVo(userDTO.getName(),userDTO.getPhone(),userDTO.getEmail()));
                            }

                        }

                        UserListAdapter userListAdapter = new UserListAdapter(userListVo);
                        recyclerViewUser.setAdapter(userListAdapter);

                        Toast toast = Toast.makeText(activity,"Usuarios Cargados",Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }

                @Override
                public void onFailure(Call<List<UserDTO>> call, Throwable t) {
                    Log.i("Conexion","Mensaje::"+t.getMessage());
                }
            });


        }catch (Exception e){
            Log.e("Error",e.getMessage());
            throw  e;
        }
    }

}
